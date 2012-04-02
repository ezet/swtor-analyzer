package swtor.analyzer.model;

import java.util.HashMap;
import java.util.Map;

import swtor.parser.constant.MitigationType;

public class AbilityUsageMetric {

	private long hitCount;
	private long critCount;
	private long mitigateCount;
	private long absorbCount;
	private long absorbTotal;
	private final Map<MitigationType, Long> mitigationTypes = new HashMap<>();

	public long getHitCount() {
		return hitCount;
	}

	public void setHitCount(long hitCount) {
		this.hitCount = hitCount;
	}

	public long getCritCount() {
		return critCount;
	}

	public void setCritCount(long critCount) {
		this.critCount = critCount;
	}

	public long getMitigateCount() {
		return mitigateCount;
	}

	public void setMitigateCount(long mitigateCount) {
		this.mitigateCount = mitigateCount;
	}

	public long getAbsorbCount() {
		return absorbCount;
	}

	public void setAbsorbCount(long absorbCount) {
		this.absorbCount = absorbCount;
	}

	public long getAbsorbTotal() {
		return absorbTotal;
	}

	public void setAbsorbTotal(long absorbTotal) {
		this.absorbTotal = absorbTotal;
	}

	public Map<MitigationType, Long> getMitigationTypes() {
		return mitigationTypes;
	}

	public void addHit() {
		++hitCount;
	}

	public void addCrit() {
		addHit();
		++critCount;
	}

	public void addMitigation(MitigationType type) {
		Long value = mitigationTypes.get(type);
		if (value == null) {
			value = 0L;
			mitigationTypes.put(type, value);
		}
		++value;
		++mitigateCount;
	}

	public void addAbsorb(long value) {
		++absorbCount;
		absorbTotal += value;
	}

}
