package com.twitter.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TweetTopicId implements Serializable {

	private TopicDetails topic;
	private TweetDetails tweetDetails;
	
	@ManyToOne
	@JoinColumn(name="TOPIC_ID", referencedColumnName="TOPIC_ID", insertable=false, updatable=false)
	public TopicDetails getTopic() {
		return topic;
	}
	
	public void setTopic(TopicDetails topic) {
		this.topic = topic;
	}
	
	@ManyToOne
	@JoinColumn(name="TWEET_ID", referencedColumnName="ID", insertable=false, updatable=false)
	public TweetDetails getTweetDetails() {
		return tweetDetails;
	}
	
	public void setTweetDetails(TweetDetails tweetDetails) {
		this.tweetDetails = tweetDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + ((tweetDetails == null) ? 0 : tweetDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweetTopicId other = (TweetTopicId) obj;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (tweetDetails == null) {
			if (other.tweetDetails != null)
				return false;
		} else if (!tweetDetails.equals(other.tweetDetails))
			return false;
		return true;
	}
	
}
