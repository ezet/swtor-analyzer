package swtor.analyzer.model;

import java.util.HashMap;
import java.util.Map;

public class Result extends CombatMetricEntity {
	
	private long duration;
	
	private Map<String, Actor> actors = new HashMap<>();

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public CombatMetric getDamageDone() {
		return damageDone;
	}

	public void setDamageDone(CombatMetric damageDone) {
		this.damageDone = damageDone;
	}

	public CombatMetric getDamageReceived() {
		return damageReceived;
	}

	public void setDamageReceived(CombatMetric damageReceived) {
		this.damageReceived = damageReceived;
	}

	public CombatMetric getHealingDone() {
		return healingDone;
	}

	public void setHealingDone(CombatMetric healingDone) {
		this.healingDone = healingDone;
	}

	public CombatMetric getHealingReceived() {
		return healingReceived;
	}

	public void setHealingReceived(CombatMetric healingReceived) {
		this.healingReceived = healingReceived;
	}

	public Map<String, Actor> getActors() {
		return actors;
	}

	public void setActors(Map<String, Actor> actors) {
		this.actors = actors;
	}
	
	public Actor getActor(Actor actor) {
		Actor found = actors.get(actor.getName());
		return (found != null) ? found: actor;
	}
	
}
