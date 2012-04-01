package swtor.analyzer.model;

public class Ability extends CombatMetricEntity {

	private final String name;
	private final long id;

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
	
	public String toString() {
		return name + ": "+ super.toString();
	}
	
}
