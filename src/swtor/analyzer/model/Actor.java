package swtor.analyzer.model;

import java.util.Map;

public class Actor extends CombatMetricEntity {

	private final String name;
	private final long id;

	private Map<String, Ability> sourceOfAbilities;
	private Map<String, Ability> targetOfAbilities;

	public Actor(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
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

	public Map<String, Ability> getSourceOfAbilities() {
		return sourceOfAbilities;
	}

	public void setSourceOfAbilities(Map<String, Ability> sourceOfAbilities) {
		this.sourceOfAbilities = sourceOfAbilities;
	}

	public Map<String, Ability> getTargetOfAbilities() {
		return targetOfAbilities;
	}

	public void setTargetOfAbilities(Map<String, Ability> targetOfAbilities) {
		this.targetOfAbilities = targetOfAbilities;
	}

	public Ability getSourceOfAbility(Ability ability) {
		Ability found = sourceOfAbilities.get(ability.getName());
		return found != null ? found : ability;
	}

	public Ability getTargetOfAbility(Ability ability) {
		Ability found = targetOfAbilities.get(ability.getName());
		return found != null ? found : ability;
	}

}
