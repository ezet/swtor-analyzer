package swtor.analyzer.model;

import java.util.List;

public class LogResult {

	private String name;
	private String date;
	private String fileName;
	private final List<Result> combatResults;
	private final List<Result> nonCombatResults;

	public LogResult(List<Result> results, List<Result> nonCombatResults, String name, String date, String fileName) {
		this.combatResults = results;
		this.nonCombatResults = nonCombatResults;
		this.name = name;
		this.date = date;
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Result> getCombatResults() {
		return combatResults;
	}

	public List<Result> getNonCombatResults() {
		return nonCombatResults;
	}

	public String toString() {
		return name;
	}

}
