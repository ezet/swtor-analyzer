package swtor.analyzer;

import java.util.ArrayList;
import java.util.List;

import swtor.analyzer.model.Ability;
import swtor.analyzer.model.Actor;
import swtor.analyzer.model.ModelFactory;
import swtor.analyzer.model.Result;
import swtor.parser.constant.EntryType;
import swtor.parser.constant.MitigationType;
import swtor.parser.model.LogEntry;
import swtor.parser.utility.Logger;

public class Analyzer {

	private List<Result> results;
	private Result result;

	public List<Result> getResults() {
		return results;
	}

	public void process(List<LogEntry> log) {
		results = new ArrayList<>();
		result = new Result();
		for (LogEntry entry : log) {
			Logger.debug(entry);
			if (entry.getType() == EntryType.ENTER_COMBAT) {
				processResult();
				results.add(result);
				// result = new Result();
			}

			processEntry(entry);

			if (entry.getType() == EntryType.EXIT_COMBAT) {
				processResult();
				results.add(result);
				// result = new Result();

			}
		}
		processResult();
		results.add(result);
	}

	private void processResult() {
		result.computeValuesOverTime(result.getDuration());
		// TODO implement calculation of DPS values eg.
	}
	

	private void processEntry(LogEntry entry) {
		Actor source = result.getActor(ModelFactory.getActor(entry.getSource()));
		Actor target = result.getActor(ModelFactory.getActor(entry.getTarget()));
		Ability sourceAbility = source.getSourceOfAbility(ModelFactory.getAbility(entry.getAbility()));
		Ability targetAbility = source.getTargetOfAbility(ModelFactory.getAbility(entry.getAbility()));

		// set the owner of the result
		if (result.getOwner() == null && source == target) {
			Logger.debug("setting owner: " + source.getName());
			result.setOwner(source.getName());
		}

		// set damage and healing done/received
		if (entry.getType() == EntryType.DAMAGE) {
			long value = entry.getResult().getValue();
			result.addDamageDone(value);
			result.addDamageReceived(value);
			result.trySetMinMaxDamageDone(value);
			result.trySetMinMaxDamageReceived(value);
			source.addDamageDone(value);
			source.trySetMinMaxDamageDone(value);
			target.addDamageReceived(value);
			target.trySetMinMaxDamageReceived(value);
			sourceAbility.addDamageDone(value);
			sourceAbility.trySetMinMaxDamageDone(value);
			targetAbility.addDamageReceived(value);
			targetAbility.trySetMinMaxDamageReceived(value);
		} else if (entry.getType() == EntryType.HEAL) {
			long value = entry.getResult().getValue();
			result.addHealingDone(value);
			result.addHealingReceived(value);
			result.trySetMinMaxHealingDone(value);
			result.trySetMinMaxHealingReceived(value);
			source.addHealingDone(value);
			source.trySetMinMaxHealingDone(value);
			target.addHealingReceived(value);
			target.trySetMinMaxHealingReceived(value);
			sourceAbility.addHealingDone(value);
			sourceAbility.trySetMinMaxHealingDone(value);
			targetAbility.addHealingReceived(value);
			targetAbility.trySetMinMaxHealingReceived(value);
		}

		if (entry.getResult().isMitigate()) {
			// mitigation
			MitigationType type = entry.getResult().getMitigationType();
			result.addMitigation(type);
			source.addMitigation(type);
			sourceAbility.addMitigation(type);
			targetAbility.addMitigation(type);
			target.addTargetOfMitigate(type);
		} else if (entry.getResult().isCritical()) {
			// critical hits
			result.addCrit();
			source.addCrit();
			sourceAbility.addCrit();
			targetAbility.addCrit();
			target.addTargetOfCrit();
		} else {
			// regular hits
			result.addHit();
			source.addHit();
			sourceAbility.addHit();
			targetAbility.addHit();
			target.addTargetOfHit();
		}

		// absorbs
		if (entry.getResult().isAbsorb()) {
			long value = entry.getResult().getAbsorbValue();
			result.addAbsorb(value);
			source.addAbsorb(value);
			sourceAbility.addAbsorb(value);
			target.addTargetOfAbsorb(value);
		}

		// threat
		long dThreat = entry.getResult().getThreatDelta();
		if (dThreat > 0) {
			result.addThreatGenerated(dThreat);
			source.addThreatGenerated(dThreat);
			sourceAbility.addThreatGenerated(dThreat);
		} else if (dThreat < 0) {
			result.addThreatDumped(dThreat);
			source.addThreatDumped(dThreat);
			sourceAbility.addThreatDumped(dThreat);
		}
	}

}
