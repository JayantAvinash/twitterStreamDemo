package com.twitter.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "TOPIC_DETAILS",
uniqueConstraints = { @UniqueConstraint( columnNames = { "TOPIC_NAME", "DATE" } ) } )
public class TopicDetails implements Serializable {

	private long topicId;
	private int frequency;
	private Set<TweetTopicDetails> tweetTopics = new HashSet<>(0);
	private String topicName;
	private Date date;
	
	@Column(name = "TOPIC_NAME", updatable = false, nullable = false)
	public String getTopicName() {
		return topicName;
	}
	
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	@Column(name = "date", updatable = false, nullable = false)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	@Column(name = "TOPIC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	
	@Column(name = "frequency", nullable = false)
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tweetTopicId.topic")
	public Set<TweetTopicDetails> getTweetTopics() {
		return tweetTopics;
	}

	public void setTweetTopics(Set<TweetTopicDetails> tweetTopics) {
		this.tweetTopics = tweetTopics;
	}
	
}
