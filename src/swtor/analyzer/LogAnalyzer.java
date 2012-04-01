package swtor.analyzer;

import java.util.ArrayList;
import java.util.List;

import swtor.analyzer.model.Result;
import swtor.parser.model.LogEntry;

public class LogAnalyzer {

	private List<LogEntry> log;
	private Analyzer analyzer = new Analyzer();
	private List<Result> results = new ArrayList<>();

	public LogAnalyzer(List<LogEntry> log) {
		this.log = log;
	}

	public Result getLastResult() {
		if (!results.isEmpty()) {
			return results.get(results.size() - 1);
		} else {
			return new Result();
		}
	}

	public void process() {
		analyzer.process(log);
		results = analyzer.getResults();
	}

}
