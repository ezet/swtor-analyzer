package swtor.analyzer.model;

public class ModelFactory {

	public static Ability getAbility(swtor.parser.model.Ability ability) {

		Ability n = new Ability(ability.getName(), ability.getGameId());
		return n;
	}

	public static Actor getActor(swtor.parser.model.Actor actor) {
		Actor n = new Actor(actor.getName(), actor.getGameId());
		return n;
	}

}
