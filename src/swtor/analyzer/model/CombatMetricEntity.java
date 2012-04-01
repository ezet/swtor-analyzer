package swtor.analyzer.model;

import swtor.parser.constant.MitigationType;

public abstract class CombatMetricEntity {

	protected CombatMetric damageDone = new CombatMetric();
	protected CombatMetric damageReceived = new CombatMetric();
	protected CombatMetric healingDone = new CombatMetric();
	protected CombatMetric healingReceived = new CombatMetric();
	protected CombatMetric threatGenerated = new CombatMetric();
	protected CombatMetric threatDumped = new CombatMetric();

	protected long hitCount;
	protected long critCount;

	protected long mitigateCount;
	protected long missCount;
	protected long glanceCount;
	protected long deflectCount;
	protected long immuneCount;
	protected long parryCount;
	protected long dodgeCount;

	protected long absorbCount;
	protected long absorbTotal;

	public void computeValuesOverTime(long duration) {
		damageDone.computeValuesOverTime(duration);
	}

	public CombatMetric getDamageDone() {
		return damageDone;
	}

	public CombatMetric getDamageReceived() {
		return damageReceived;
	}

	public CombatMetric getHealingDone() {
		return healingDone;
	}

	public CombatMetric getHealingReceived() {
		return healingReceived;
	}

	public CombatMetric getThreatGenerated() {
		return threatGenerated;
	}

	public CombatMetric getThreatDumped() {
		return threatDumped;
	}

	public long getHitCount() {
		return hitCount;
	}

	public long getCritCount() {
		return critCount;
	}

	public long getMitigateCount() {
		return mitigateCount;
	}

	public long getMissCount() {
		return missCount;
	}

	public long getGlanceCount() {
		return glanceCount;
	}

	public long getDeflectCount() {
		return deflectCount;
	}

	public long getImmuneCount() {
		return immuneCount;
	}

	public long getParryCount() {
		return parryCount;
	}

	public long getDodgeCount() {
		return dodgeCount;
	}

	public long getAbsorbCount() {
		return absorbCount;
	}

	public long getAbsorbTotal() {
		return absorbTotal;
	}

	public void addDamageDone(long value) {
		damageDone.addToTotal(value);
	}

	public void addDamageReceived(long value) {
		damageReceived.addToTotal(value);
	}

	public void addHealingDone(long value) {
		healingDone.addToTotal(value);
	}

	public void addHealingReceived(long value) {
		healingReceived.addToTotal(value);
	}

	public void addThreatGenerated(long value) {
		threatGenerated.addToTotal(value);
	}

	public void addThreatDumped(long value) {
		threatDumped.addToTotal(value);
	}

	public void addHit() {
		++hitCount;
	}

	public void addCrit() {
		addHit();
		++critCount;
	}

	public void addMiss() {
		++missCount;
		++mitigateCount;
	}

	public void addGlance() {
		++glanceCount;
		++mitigateCount;
	}

	public void addImmune() {
		++immuneCount;
		++mitigateCount;
	}

	public void addParry() {
		++parryCount;
		++mitigateCount;
	}

	public void addDodge() {
		++dodgeCount;
		++mitigateCount;
	}

	public void addDeflect() {
		++deflectCount;
		++mitigateCount;
	}

	public void addAbsorb(long value) {
		++absorbCount;
		absorbTotal += value;
	}

	public void trySetMinMaxDamageDone(long value) {
		damageDone.trySetMinMax(value);
	}

	public void trySetMinMaxDamageReceived(long value) {
		damageReceived.trySetMinMax(value);
	}

	public void trySetMinMaxHealingDone(long value) {
		healingDone.trySetMinMax(value);
	}

	public void trySetMinMaxHealingReceived(long value) {
		healingReceived.trySetMinMax(value);
	}

	public void addMitigation(MitigationType type) {
		switch (type) {
		case MISS:
			addMiss();
			break;
		case GLANCE:
			addGlance();
			break;
		case PARRY:
			addParry();
			break;
		case DODGE:
			addDodge();
			break;
		case IMMUNE:
			addImmune();
			break;
		case DEFLECT:
			addDeflect();
			break;
		}
	}

	public String toString() {
		return String
				.format("hit#:%s, crit#:%s, miss#:%s, parry#:%s, glance#:%s, dodge#:%s, deflect#:%s, immune#:%s, absorb#:%s, absorbVal:#%s",
						hitCount, critCount, missCount, parryCount, glanceCount, dodgeCount, deflectCount, immuneCount,
						absorbCount, absorbTotal);
	}

}
