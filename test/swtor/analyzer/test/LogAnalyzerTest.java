package swtor.analyzer.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import swtor.analyzer.LogAnalyzer;
import swtor.analyzer.model.Result;
import swtor.parser.LogParser;
import swtor.parser.utility.Logger;

public class LogAnalyzerTest {

	@Test
	public void test() {
		Path path = Paths.get("D:/Dev/Projects/SwtorParser/SampleLogs/sample.txt");
		LogParser p = new LogParser(path);
		try {
			p.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LogAnalyzer a = new LogAnalyzer(p.getLog());
		a.process();
		Result res = a.getLastResult();
		Logger.log(res.toString());
		
		Logger.log(res.getActors().get("@Argorash").getDamageDone().toString());
		Logger.log(res.getHealingDone().toString());
		
		assertTrue(true);
	}

}
