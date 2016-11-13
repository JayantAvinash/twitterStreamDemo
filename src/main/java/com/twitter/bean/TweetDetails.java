package com.twitter.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TWEET_DETAILS")
public class TweetDetails implements Serializable{

	private long id;
	private String tweet;
	private Date tweetedAt;
	private long userId;
	private String userName;
	private Set<TweetTopicDetails> tweetTopics = new HashSet<>(0);

	public TweetDetails() {

	}

	public TweetDetails(String tweet, Date tweetedAt, long userId, String userName) {
		super();
		this.tweet = tweet;
		this.tweetedAt = tweetedAt;
		this.userId = userId;
		this.userName = userName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "TWEET", nullable = false)
	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@Column(name = "TWEET_DATE", nullable = false)
	public Date getTweetedAt() {
		return tweetedAt;
	}

	public void setTweetedAt(Date tweetedAt) {
		this.tweetedAt = tweetedAt;
	}

	@Column(name = "USER_ID", nullable = false)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tweetTopicId.tweetDetails", cascade = CascadeType.ALL)
	public Set<TweetTopicDetails> getTweetTopics() {
		return tweetTopics;
	}

	public void setTweetTopics(Set<TweetTopicDetails> tweetTopics) {
		this.tweetTopics = tweetTopics;
	}
}
