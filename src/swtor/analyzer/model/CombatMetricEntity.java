package swtor.analyzer.model;

public abstract class CombatMetricEntity {

	protected CombatMetric damageDone = new CombatMetric();
	protected CombatMetric damageReceived = new CombatMetric();
	protected CombatMetric healingDone = new CombatMetric();
	protected CombatMetric healingReceived = new CombatMetric();
	protected CombatMetric threatGenerated = new CombatMetric();
	protected CombatMetric threatDumped = new CombatMetric();
	
	protected long hitCount;
	protected long critCount;
	
	protected long missCount;
	protected long glanceCount;
	protected long deflectCount;
	protected long immuneCount;
	protected long parryCount;
	
	protected long absorbCount;
	protected long absorbTotal;

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
	}
	
	public void addGlance() {
		++deflectCount;
	}
	
	public void addImmune() {
		++immuneCount;
	}
	
	public void addParry() {
		++parryCount;
	}
	
	public void addAbsorb(long value) {
		++absorbCount;
		absorbTotal += value;
	}

}
