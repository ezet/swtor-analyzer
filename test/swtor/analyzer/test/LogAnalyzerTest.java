package swtor.analyzer.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import swtor.analyzer.LogAnalyzer;
import swtor.analyzer.model.Result;
import swtor.parser.LogParser;
import swtor.parser.utility.Logger;

public class LogAnalyzerTest {

	@Test
	public void test() {
		File file = new File("D:/Dev/Projects/SwtorParser/SampleLogs/combat_2012-03-17_10_39_06_966767.txt");
		LogParser parser = new LogParser(file);
		try {
			parser.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogAnalyzer a = new LogAnalyzer(parser.getLog());
		a.process();
		for (Result r : a.getCombatResults()) {
			Logger.log(r);
			Logger.log(r.getHitCount());
			Logger.log(r.getDamageDone());
			Logger.log(r.getThreatGenerated());
		}
		
	}
}
