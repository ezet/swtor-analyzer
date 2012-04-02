package swtor.analyzer.model;

import java.util.HashMap;
import java.util.Map;

import swtor.parser.constant.MitigationType;

public class Actor extends GenericMetricEntity {

	private final String name;
	private final long id;

	private final AbilityUsageMetric targetOfMetrics = new AbilityUsageMetric();

	private final Map<String, Ability> sourceOfAbilities = new HashMap<>();
	private final Map<String, Ability> targetOfAbilities = new HashMap<>();

	public Actor(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public long getTargetOfHitCount() {
		return targetOfMetrics.getHitCount();
	}

	public long getTargetOfCritCount() {
		return targetOfMetrics.getCritCount();
	}

	public long getTargetOfMitigateCount() {
		return targetOfMetrics.getMitigateCount();
	}

	public long getTargetOfAbsorbCount() {
		return targetOfMetrics.getAbsorbCount();
	}

	public long getTargetOfAbsorbTotal() {
		return targetOfMetrics.getAbsorbTotal();
	}

	public Map<String, Ability> getSourceOfAbilities() {
		return sourceOfAbilities;
	}

	public Map<String, Ability> getTargetOfAbilities() {
		return targetOfAbilities;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public Ability getSourceOfAbility(Ability ability) {
		Ability found = sourceOfAbilities.get(ability.getName());
		if (found == null) {
			sourceOfAbilities.put(ability.getName(), ability);
			found = ability;
		}
		return found;
	}

	public Ability getTargetOfAbility(Ability ability) {
		Ability found = targetOfAbilities.get(ability.getName());
		if (found == null) {
			targetOfAbilities.put(ability.getName(), ability);
			found = ability;
		}
		return found;
	}

	public void addTargetOfHit() {
		targetOfMetrics.addHit();
	}

	public void addTargetOfCrit() {
		targetOfMetrics.addCrit();
	}

	public void addTargetOfMitigate(MitigationType type) {
		targetOfMetrics.addMitigation(type);
	}

	public void addTargetOfAbsorb(long value) {
		targetOfMetrics.addAbsorb(value);
	}

	public String toString() {
		return String.format("name:%s", name);
	}

}
