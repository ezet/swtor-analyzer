package swtor.analyzer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import swtor.analyzer.exception.LogAnalyzerException;
import swtor.analyzer.model.LogResult;
import swtor.parser.LogParser;
import swtor.parser.exception.LogParserException;
import swtor.parser.model.CombatLog;
import swtor.parser.model.LogEntry;

// TODO clean up the API
public class LogAnalyzer {

	private Analyzer analyzer = new Analyzer();
	private LogParser parser = new LogParser();
	private CombatLog log;
	private LogResult result;
	private int mode = 0;
	
	{
		parser.addOutputFilter(analyzer);
	}

	public LogAnalyzer(File file) {
		setLog(file);
	}

	public LogAnalyzer(CombatLog log) {
		setLog(log);
	}

	public LogAnalyzer(List<LogEntry> entries) {
		setLog(entries);
	}

	public void setLog(File file) {
		mode = 1;
		parser.setSource(file);
	}

	public void setLog(URI uri) {
		mode = 1;
		parser.setSource(uri);

	}

	public void setLog(CombatLog log) {
		mode = 0;
		this.log = log;
	}

	public void setLog(List<LogEntry> entries) {
		mode = 0;
		this.log = new CombatLog(entries, "unnamed file");
	}

	public LogResult getResult() {
		return result;
	}

	public void process() throws LogAnalyzerException {
		if (mode == 0) {
			if (!log.getEntries().isEmpty()) {
				analyzer.process(log.getEntries());
			} else {
				System.out.println("log empty");
			}
		} else if (mode == 1) {
			try {
				parser.parse();
				log = parser.getCombatLog();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LogParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = new LogResult(analyzer.getCombatResults(), analyzer.getNonCombatresults(), log.getFileName(),
					log.getDate(), log.getFileName());
		}
	}

}
