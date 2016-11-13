package com.twitter.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.twitter.bean.TopicDetails;
import com.twitter.bean.TweetDetails;
import com.twitter.dao.TweetDao;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class TweetParser {

	static Set<String> nounPhrases = new HashSet<>();

	
	public static void parseAndStoreTweets(TweetDetails td) {
		InputStream modelInParse = null;
		try {
			// load chunking model
			modelInParse = new FileInputStream("en-parser-chunking.bin");
			
			ParserModel model = new ParserModel(modelInParse);
			// create parse tree
			Parser parser = ParserFactory.create(model);
			Parse topParses[] = ParserTool.parseLine(td.getTweet(), parser, 1);
			Set<String> nounPhrases = new HashSet<>();
			// call subroutine to extract noun phrases
			for (Parse p : topParses)
				getNounPhrases(p, nounPhrases);
			Set<TopicDetails> topics = new HashSet<>();
			// print noun phrases
			for (String s : nounPhrases) {
				s = s.replaceAll("'", "''");
				System.out.println(s);
				TopicDetails topic = new TopicDetails();
				topic.setDate(new Date(System.currentTimeMillis()));
				topic.setTopicName(s);
				
				topics.add(topic);
			}
				
			
			TweetDao.storeTweetsAndTopics(td, topics);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (modelInParse != null) {
				try {
					modelInParse.close();
				} catch (IOException e) {
				}
			}
		}
	}

	// recursively loop through tree, extracting noun phrases
	public static void getNounPhrases(Parse p, Set<String> nounPhrases) {

		if (p.getType().equals("NP")) { // NP=noun phrase
			nounPhrases.add(p.getCoveredText());
		}
		for (Parse child : p.getChildren())
			getNounPhrases(child, nounPhrases);
	}
}
