package swtor.analyzer.model;

import java.util.HashMap;
import java.util.Map;

public class Result extends CombatMetricEntity {
	
	private String owner;
	private String name;
	private String date;
	private long duration;
	
	private Map<String, Actor> actors = new HashMap<>();
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Map<String, Actor> getActors() {
		return actors;
	}

	public void setActors(Map<String, Actor> actors) {
		this.actors = actors;
	}

	public Actor getActor(Actor actor) {
		Actor found = actors.get(actor.getName());
		if (found == null) {
			actors.put(actor.getName(), actor);
			found = actor;
		}
		return found;
	}
	
	public String toString() {
		return super.toString();
	}
}
