package swtor.analyzer.model;

import java.util.HashMap;
import java.util.Map;

import swtor.parser.constant.MitigationType;

public class Actor extends CombatMetricEntity {

	private final String name;
	private final long id;

	private long targetOfHitCount;
	private long targetOfCritCount;
	private long targetOfMitigateCount;
	private long targetOfMissCount;
	private long targetOfGlanceCount;
	private long targetOfDeflectCount;
	private long targetOfImmuneCount;
	private long targetOfParryCount;
	private long targetOfDodgeCount;
	private long targetOfResistCount;

	protected long targetOfAbsorbCount;
	protected long targetOfAbsorbTotal;

	private Map<String, Ability> sourceOfAbilities = new HashMap<>();
	private Map<String, Ability> targetOfAbilities = new HashMap<>();

	public Actor(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public long getTargetOfHitCount() {
		return targetOfHitCount;
	}

	public void setTargetOfHitCount(long targetOfHitCount) {
		this.targetOfHitCount = targetOfHitCount;
	}

	public long getTargetOfCritCount() {
		return targetOfCritCount;
	}

	public void setTargetOfCritCount(long targetOfCritCount) {
		this.targetOfCritCount = targetOfCritCount;
	}

	public long getTargetOfMitigateCount() {
		return targetOfMitigateCount;
	}

	public void setTargetOfMitigateCount(long targetOfMitigateCount) {
		this.targetOfMitigateCount = targetOfMitigateCount;
	}

	public long getTargetOfMissCount() {
		return targetOfMissCount;
	}

	public void setTargetOfMissCount(long targetOfMissCount) {
		this.targetOfMissCount = targetOfMissCount;
	}

	public long getTargetOfGlanceCount() {
		return targetOfGlanceCount;
	}

	public void setTargetOfGlanceCount(long targetOfGlanceCount) {
		this.targetOfGlanceCount = targetOfGlanceCount;
	}

	public long getTargetOfDeflectCount() {
		return targetOfDeflectCount;
	}

	public void setTargetOfDeflectCount(long targetOfDeflectCount) {
		this.targetOfDeflectCount = targetOfDeflectCount;
	}

	public long getTargetOfImmuneCount() {
		return targetOfImmuneCount;
	}

	public void setTargetOfImmuneCount(long targetOfImmuneCount) {
		this.targetOfImmuneCount = targetOfImmuneCount;
	}

	public long getTargetOfParryCount() {
		return targetOfParryCount;
	}

	public void setTargetOfParryCount(long targetOfParryCount) {
		this.targetOfParryCount = targetOfParryCount;
	}

	public long getTargetOfDodgeCount() {
		return targetOfDodgeCount;
	}

	public void setTargetOfDodgeCount(long targetOfDodgeCount) {
		this.targetOfDodgeCount = targetOfDodgeCount;
	}

	public long getTargetOfAbsorbCount() {
		return targetOfAbsorbCount;
	}

	public void setTargetOfAbsorbCount(long targetOfAbsorbCount) {
		this.targetOfAbsorbCount = targetOfAbsorbCount;
	}

	public long getTargetOfAbsorbTotal() {
		return targetOfAbsorbTotal;
	}

	public void setTargetOfAbsorbTotal(long targetOfabsorbTotal) {
		this.targetOfAbsorbTotal = targetOfabsorbTotal;
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
		++targetOfHitCount;
	}

	public void addTargetOfCrit() {
		addHit();
		++targetOfCritCount;
	}

	public void addTargetOfMiss() {
		++targetOfMissCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfGlance() {
		++targetOfDeflectCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfImmune() {
		++targetOfImmuneCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfParry() {
		++targetOfParryCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfDodge() {
		++targetOfDodgeCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfDeflect() {
		++targetOfDeflectCount;
		++targetOfMitigateCount;
	}
	
	public void addTargetOfResist() {
		++targetOfResistCount;
		++targetOfMitigateCount;
	}

	public void addTargetOfAbsorb(long value) {
		targetOfAbsorbTotal += value;
		++targetOfAbsorbCount;
	}

	public void addTargetOfMitigate(MitigationType type) {
		switch (type) {
		case MISS:
			addTargetOfMiss();
			break;
		case GLANCE:
			addTargetOfGlance();
			break;
		case PARRY:
			addTargetOfParry();
			break;
		case DODGE:
			addTargetOfDodge();
			break;
		case IMMUNE:
			addTargetOfImmune();
			break;
		case DEFLECT:
			addTargetOfDeflect();
			break;
		case RESIST:
			addTargetOfResist();
			break;
		}
	}

	public String toString() {
		return String.format("name:%s", name);
	}

}
