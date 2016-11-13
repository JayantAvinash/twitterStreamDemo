package com.twitter;

import com.twitter.stream.TweetHandler;

import twitter4j.TwitterException;

public class TwitterDataProcessor {
	
	public static final String handleName = "CNN";
	
	public static void main(String[] args) throws TwitterException {
		TweetHandler t = new TweetHandler();
		t.extractAndStoreTweetDetails(handleName);
	}

}
