package swtor.analyzer;

import java.util.List;

import swtor.analyzer.model.Result;
import swtor.parser.model.LogEntry;

public class LogAnalyzer {
	
	private List<LogEntry> log;
	private Analyzer analyzer = new Analyzer();
	private Result result;
	
	
	public LogAnalyzer(List<LogEntry> log) {
		this.log = log;
	}
	
	public void process() {
		analyzer.process(log);
		result = analyzer.getResult();
		
		
	}

}
