package swtor.analyzer;

import java.util.List;

import swtor.analyzer.model.LogResult;
import swtor.parser.model.CombatLog;
import swtor.parser.model.LogEntry;


// TODO clean up the API
public class LogAnalyzer {

	private Analyzer analyzer = new Analyzer();
	private CombatLog log;
	private LogResult result;

	public LogAnalyzer(List<LogEntry> entries) {
		this.log = new CombatLog(entries, "unnamed file");
	}
	
	public LogAnalyzer(CombatLog log) {
		this.log = log;
	}
	
	public LogResult getResult() {
		return result;
	}
	
	public void process() {
		if (!log.getEntries().isEmpty()) {
			analyzer.process(log.getEntries());
			result = new LogResult(analyzer.getCombatResults(), analyzer.getNonCombatresults(), log.getFileName(), log.getDate(), log.getFileName());
		} else {
			System.out.println("log empty");
		}
	}

}
