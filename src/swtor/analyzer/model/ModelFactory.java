package swtor.analyzer.model;

public class ModelFactory {

	public static Ability getAbility(swtor.parser.model.LogEntry entry) {

		Ability n = new Ability(entry.getAbility(), entry.getAbilityId());
		return n;
	}
	
	public static Actor getSource(swtor.parser.model.LogEntry entry) {
		Actor n = new Actor(entry.getSource(), entry.getSourceId(), entry.sourceIsPlayer(), entry.sourceIsCompanion());
		return n;
	}

	public static Actor getTarget(swtor.parser.model.LogEntry entry) {
		Actor n = new Actor(entry.getTarget(), entry.getTargetId(), entry.targetIsPlayer(), entry.targetIsCompanion());
		return n;
	}

}
