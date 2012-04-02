package swtor.analyzer.model;

import swtor.parser.constant.MitigationType;

public abstract class GenericMetricEntity {

	protected final CombatMetric damageDone = new CombatMetric();
	protected final CombatMetric damageReceived = new CombatMetric();
	protected final CombatMetric healingDone = new CombatMetric();
	protected final CombatMetric healingReceived = new CombatMetric();
	protected final CombatMetric threatGenerated = new CombatMetric();
	protected final CombatMetric threatDumped = new CombatMetric();

	protected final AbilityUsageMetric sourceOfMetrics = new AbilityUsageMetric();

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

	public long getHitCount() {
		return sourceOfMetrics.getHitCount();
	}

	public long getCritCount() {
		return sourceOfMetrics.getCritCount();
	}

	public long getMitigateCount() {
		return sourceOfMetrics.getMitigateCount();
	}

	public long getAbsorbCount() {
		return sourceOfMetrics.getAbsorbCount();
	}

	public long getAbsorbTotal() {
		return sourceOfMetrics.getAbsorbTotal();
	}

	public void addHit() {
		sourceOfMetrics.addHit();
	}

	public void addCrit() {
		addHit(); // TODO decide how to count critical hits
		sourceOfMetrics.addCrit();
	}

	public void addMitigation(MitigationType type) {
		sourceOfMetrics.addMitigation(type);
	}

	public void addAbsorb(long value) {
		sourceOfMetrics.addAbsorb(value);
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

	public void computeValuesOverTime(long duration) {
		damageDone.computeValuesOverTime(duration);
	}
}
