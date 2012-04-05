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
import swtor.parser.model.LogEntry;
import swtor.parser.util.Logger;

public class Analyzer {

	private List<Result> combatResults;
	private List<Result> nonCombatResults;
	private Result result;

	public List<Result> getCombatResults() {
		return combatResults;
	}

	public List<Result> getNonCombatresults() {
		return nonCombatResults;
	}

	public void process(List<LogEntry> log) {
		int id = 0;
		combatResults = new ArrayList<Result>();
		nonCombatResults = new ArrayList<Result>();
		result = new Result(++id, 0);
		LogEntry previous = null;
		for (LogEntry entry : log) {
			if (result.getStart() == null) {
				result.setStart(entry.getTime());
			}

			if (entry.getType() == EntryType.ENTER_COMBAT) {
				if (result.getLogEntryCount() > 0) {
					result.setEnd(previous.getTime());
					result.setLineEnd(previous.getLineNumber());
					finalizeResult();
					result = new Result(++id, entry.getLineNumber());
				}
				result.setType(ResultType.COMBAT);
			} else if (entry.getType() == EntryType.EXIT_COMBAT && result.getLogEntryCount() > 0) {
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
		result.setEnd(previous.getTime());
		result.setLineEnd(previous.getLineNumber());
		finalizeResult();
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
//			Logger.log("setting hostile: " + actor.getName());
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
		result.increaseLineCount();

		// get a new or existing model from the result, and add it to the result
		// if new
		Actor source = result.getActor(ModelFactory.getActor(entry.getSource()));
		Actor target = result.getActor(ModelFactory.getActor(entry.getTarget()));
		Ability sourceAbility = source.getSourceOfAbility(ModelFactory.getAbility(entry.getAbility()));
		Ability targetAbility = source.getTargetOfAbility(ModelFactory.getAbility(entry.getAbility()));
		Ability ability = result.getAbility(ModelFactory.getAbility(entry.getAbility()));

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
			long value = entry.getResult().getValue();
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
			long value = entry.getResult().getValue();
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

		if (entry.getResult().isMitigate()) {
			// mitigation
			MitigationType type = entry.getResult().getMitigationType();
			result.addMitigation(type);
			ability.addMitigation(type);
			source.addMitigation(type);
			sourceAbility.addMitigation(type);
			targetAbility.addMitigation(type);
			target.addTargetOfMitigate(type);
		} else if (entry.getResult().isCritical()) {
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
		if (entry.getResult().isAbsorb()) {
			long value = entry.getResult().getAbsorbValue();
			result.addAbsorb(value);
			ability.addAbsorb(value);
			source.addAbsorb(value);
			sourceAbility.addAbsorb(value);
			target.addTargetOfAbsorb(value);
		}

		// threat
		long dThreat = entry.getResult().getThreatDelta();
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
