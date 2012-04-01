package swtor.analyzer.model;

public class CombatMetric {

	private long total;
	private double perSecond;
	private double average;
	private double median;

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

}
