package swtor.analyzer.model;

public class Ability extends CombatMetricEntity {

	private final String name;
	private final long id;

	private int count;
	private int criticalCount;

	private double minValue;
	private double maxValue;
	private double avgValue;

	public Ability(String name, long id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCriticalCount() {
		return criticalCount;
	}

	public void setCriticalCount(int criticalCount) {
		this.criticalCount = criticalCount;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(double avgValue) {
		this.avgValue = avgValue;
	}

	public double getMedianValue() {
		return medianValue;
	}

	public void setMedianValue(double medianValue) {
		this.medianValue = medianValue;
	}

	public int getMitigateCount() {
		return mitigateCount;
	}

	public void setMitigateCount(int mitigateCount) {
		this.mitigateCount = mitigateCount;
	}

	public int getMissCount() {
		return missCount;
	}

	public void setMissCount(int missCount) {
		this.missCount = missCount;
	}

	public int getGlanceCount() {
		return glanceCount;
	}

	public void setGlanceCount(int glanceCount) {
		this.glanceCount = glanceCount;
	}

	public int getDeflectCount() {
		return deflectCount;
	}

	public void setDeflectCount(int deflectCount) {
		this.deflectCount = deflectCount;
	}

	public int getParryCount() {
		return parryCount;
	}

	public void setParryCount(int parryCount) {
		this.parryCount = parryCount;
	}

	public int getImmuneCount() {
		return immuneCount;
	}

	public void setImmuneCount(int immuneCount) {
		this.immuneCount = immuneCount;
	}

	public int getAbsorbCount() {
		return absorbCount;
	}

	public void setAbsorbCount(int absorbCount) {
		this.absorbCount = absorbCount;
	}

	public int getAbsorbTotal() {
		return absorbTotal;
	}

	public void setAbsorbTotal(int absorbTotal) {
		this.absorbTotal = absorbTotal;
	}

	private double medianValue;

	private int mitigateCount;
	private int missCount;
	private int glanceCount;
	private int deflectCount;
	private int parryCount;
	private int immuneCount;
	private int absorbCount;
	private int absorbTotal;

}
