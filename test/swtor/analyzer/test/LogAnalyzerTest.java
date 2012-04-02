package swtor.analyzer.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
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
		Path path;
		path = Paths.get("D:/Dev/Projects/SwtorParser/SampleLogs/");
		try (DirectoryStream<Path> dir = Files.newDirectoryStream(path);) {
			for (Path p : dir) {
				Logger.log(p.toString());
				LogParser parser = new LogParser(p);
				parser.parse();
				LogAnalyzer a = new LogAnalyzer(parser.getLog());
				a.process();
				Result res = a.getLastResult(); 
				Logger.log(res);
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("IO error");
		}
	}

}
