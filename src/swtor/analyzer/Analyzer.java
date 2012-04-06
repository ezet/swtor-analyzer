package swtor.analyzer;

import java.util.ArrayList;
import java.util.List;

import swtor.analyzer.model.Ability;
import swtor.analyzer.model.Actor;
import swtor.analyzer.model.ModelFactory;
import swtor.analyzer.model.Result;
import swtor.analyzer.model.Result.ResultType;
import swtor.parser.constant.EntryType;
import swtor.parser.constant.MitigationType;
import swtor.parser.filter.OutputFilter;
import swtor.parser.model.LogEntry;

public class Analyzer implements OutputFilter {

	private List<Result> combatResults;
	private List<Result> nonCombatResults;
	private Result result;
	private LogEntry previous;
	private int id;

	public List<Result> getCombatResults() {
		return combatResults;
	}

	public List<Result> getNonCombatresults() {
		return nonCombatResults;
	}

	private void reset() {
		combatResults = new ArrayList<Result>();
		nonCombatResults = new ArrayList<Result>();
		result = new Result(0, 0);
		previous = null;
		id = 0;
	}

	@Override
	public void onLogStart() {
		reset();
	}
	
	@Override
	public void onLogEnd() {
		result.setEnd(previous.getTime());
		result.setLineEnd(previous.getLineNumber());
		finalizeResult();
	}
	
	public void process(List<LogEntry> log) {
		onLogStart();
		for (LogEntry entry : log) {
			process(entry);
		}
		onLogEnd();
	}

	@Override
	public void process(LogEntry entry) {
		if (result.getStart() == null) {
			result.setStart(entry.getTime());
		}
		if (entry.getType() == EntryType.ENTER_COMBAT) {
			if (result.getLineCount() > 0) {
				result.setEnd(previous.getTime());
				result.setLineEnd(previous.getLineNumber());
				finalizeResult();
				result = new Result(++id, entry.getLineNumber());
			}
			result.setType(ResultType.COMBAT);
		} else if (entry.getType() == EntryType.EXIT_COMBAT && result.getLineCount() > 0) {
			result.setType(ResultType.COMBAT);
			result.setEnd(entry.getTime());
			result.setLineEnd(entry.getLineNumber());
			finalizeResult();
			result = new Result(++id, entry.getLineNumber());
		} else {
			processEntry(entry);
		}
		previous = entry;
	}

	private void finalizeResult() {
		result.setDuration(Math.abs(result.getStart().getTimeInMillis() - result.getEnd().getTimeInMillis()));
		result.computeValuesOverTime(result.getDuration());
		if (result.getType() == ResultType.COMBAT) {
			combatResults.add(result);
		} else {
			nonCombatResults.add(result);
		}
	}

	private void setHostility(Actor actor) {
		if (!actor.isHostile() && !actor.isPlayer() && !actor.isCompanion()) {
			// Logger.log("setting hostile: " + actor.getName());
			actor.setHostile(true);
			// Set the name of the result to the first hostile we encounter
			if (result.getName() == null || result.getName().isEmpty()) {
				result.setName(actor.getName());
			}
		}
	}

	private void processDamage() {

	}

	private void processEntry(LogEntry entry) {
		// get a new or existing model from the result, and add it to the result
		// if new
		Actor source = result.addActor(ModelFactory.getSource(entry));
		Actor target = result.addActor(ModelFactory.getTarget(entry));
		Ability sourceAbility = source.getSourceOfAbility(ModelFactory.getAbility(entry));
		Ability targetAbility = source.getTargetOfAbility(ModelFactory.getAbility(entry));
		Ability ability = result.addAbility(ModelFactory.getAbility(entry));

		// set the owner of the result
		if ((result.getOwner() == null || result.getOwner().isEmpty()) && source.getName().equals(target.getName())
				&& !source.isCompanion()) {
			result.setOwner(source.getName());
		}

		// set damage and healing done/received
		if (entry.getType() == EntryType.DAMAGE) {
			processDamage();
			setHostility(source);
			setHostility(target);
			long value = entry.getValue();
			result.addDamageDone(value);
			result.addDamageReceived(value);
			result.trySetMinMaxDamageDone(value);
			result.trySetMinMaxDamageReceived(value);
			ability.addDamageDone(value);
			ability.addDamageReceived(value);
			ability.trySetMinMaxDamageDone(value);
			ability.trySetMinMaxDamageReceived(value);
			source.addDamageDone(value);
			source.trySetMinMaxDamageDone(value);
			target.addDamageReceived(value);
			target.trySetMinMaxDamageReceived(value);
			sourceAbility.addDamageDone(value);
			sourceAbility.trySetMinMaxDamageDone(value);
			targetAbility.addDamageReceived(value);
			targetAbility.trySetMinMaxDamageReceived(value);
		} else if (entry.getType() == EntryType.HEAL) {
			long value = entry.getValue();
			result.addHealingDone(value);
			result.addHealingReceived(value);
			result.trySetMinMaxHealingDone(value);
			result.trySetMinMaxHealingReceived(value);
			ability.addHealingDone(value);
			ability.addHealingReceived(value);
			ability.trySetMinMaxHealingDone(value);
			ability.trySetMinMaxHealingReceived(value);
			source.addHealingDone(value);
			source.trySetMinMaxHealingDone(value);
			target.addHealingReceived(value);
			target.trySetMinMaxHealingReceived(value);
			sourceAbility.addHealingDone(value);
			sourceAbility.trySetMinMaxHealingDone(value);
			targetAbility.addHealingReceived(value);
			targetAbility.trySetMinMaxHealingReceived(value);
		}

		if (entry.isMitigate()) {
			// mitigation
			MitigationType type = entry.getMitigationType();
			result.addMitigation(type);
			ability.addMitigation(type);
			source.addMitigation(type);
			sourceAbility.addMitigation(type);
			targetAbility.addMitigation(type);
			target.addTargetOfMitigate(type);
		} else if (entry.isCritical()) {
			// critical hits
			result.addCrit();
			ability.addCrit();
			source.addCrit();
			sourceAbility.addCrit();
			targetAbility.addCrit();
			target.addTargetOfCrit();
		} else {
			// regular hits
			result.addHit();
			ability.addHit();
			source.addHit();
			sourceAbility.addHit();
			targetAbility.addHit();
			target.addTargetOfHit();
		}

		// absorbs
		if (entry.isAbsorb()) {
			long value = entry.getAbsorbValue();
			result.addAbsorb(value);
			ability.addAbsorb(value);
			source.addAbsorb(value);
			sourceAbility.addAbsorb(value);
			target.addTargetOfAbsorb(value);
		}

		// threat
		long dThreat = entry.getThreatDelta();
		if (dThreat > 0) {
			result.addThreatGenerated(dThreat);
			ability.addThreatGenerated(dThreat);
			source.addThreatGenerated(dThreat);
			sourceAbility.addThreatGenerated(dThreat);
		} else if (dThreat < 0) {
			result.addThreatDumped(dThreat);
			ability.addThreatDumped(dThreat);
			source.addThreatDumped(dThreat);
			sourceAbility.addThreatDumped(dThreat);
		}
	}

}
