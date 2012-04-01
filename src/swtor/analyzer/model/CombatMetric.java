package swtor.analyzer.model;

public class CombatMetric {

	private long total;
	private double perSecond;
	private double average;
	private double median;
	private long min;
	private long max;

	public double getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public double getPerSecond() {
		return perSecond;
	}

	public void setPerSecond(double perSecond) {
		this.perSecond = perSecond;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public void addToTotal(long value) {
		total += value;
	}

	public void trySetMin(long value) {
		min = (value < min && value > 0) ? value : min;
	}

	public void trySetMax(long value) {
		max = (value > max) ? value : max;
	}

	public void trySetMinMax(long value) {
		trySetMin(value);
		trySetMax(value);
	}

	public void computeValuesOverTime(long duration) {
		if (duration > 0)
			perSecond = ((double) total  * 1000D) / duration;
	}

	public String toString() {
		return String.format("total:%s, dps:%s, avg:%s, median:%s, min:%s, max:%s", total, perSecond, average, median,
				min, max);
	}
}
