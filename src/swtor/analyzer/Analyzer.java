package swtor.analyzer;

import java.util.List;

import swtor.analyzer.model.Ability;
import swtor.analyzer.model.Actor;
import swtor.analyzer.model.ModelFactory;
import swtor.analyzer.model.Result;
import swtor.parser.model.LogEntry;
import swtor.parser.model.LogEntry.LogEntryType;

public class Analyzer {
	
	private Result result;
	private List<LogEntry> log;
	
	
	public Result getResult() {
		return result;
	}

	public void process(List<LogEntry> log) {
		result = new Result();
		this.log = log;
		for (LogEntry entry : log) {
			process(entry);
		}
	}
	
	private void process(LogEntry entry) {
		Actor source = result.getActor(ModelFactory.getActor(entry.getSource()));
		Actor target = result.getActor(ModelFactory.getActor(entry.getTarget()));
		Ability sourceAbility = source.getSourceOfAbility(ModelFactory.getAbility(entry.getAbility()));
		Ability targetAbility = source.getTargetOfAbility(ModelFactory.getAbility(entry.getAbility()));
		
		// damage and healing done/received
		LogEntryType type = entry.getType();
		if (type == LogEntryType.DAMAGE) {
			long value = entry.getResult().getValue();
			result.addDamageDone(value);
			result.addDamageReceived(value);
			source.addDamageDone(value);
			target.addDamageReceived(value);
			sourceAbility.addDamageDone(value);
			targetAbility.addDamageReceived(value);
		} else if (type == LogEntryType.HEALING) {
			long value = entry.getResult().getValue();
			result.addHealingDone(value);
			result.addHealingReceived(value);
			source.addHealingDone(value);
			target.addHealingReceived(value);
			sourceAbility.addHealingDone(value);
			targetAbility.addHealingReceived(value);
		}
		
		// critical hits
		if (entry.getResult().isCritical()) {
			result.addCrit();
			source.addCrit();
			sourceAbility.addCrit();
			targetAbility.addCrit();
		}
		
		// mitigation
		if (entry.getResult().isMitigate()) {
			
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
