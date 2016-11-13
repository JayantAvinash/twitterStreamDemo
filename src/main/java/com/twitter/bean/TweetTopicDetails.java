package com.twitter.bean;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TWEET_TOPIC_DETAILS")
public class TweetTopicDetails implements Serializable {
	
	private TweetTopicId tweetTopicId = new TweetTopicId();
	
	@EmbeddedId
	public TweetTopicId getTweetTopicId() {
		return tweetTopicId;
	}
	
	public void setTweetTopicId(TweetTopicId tweetTopicId) {
		this.tweetTopicId = tweetTopicId;
	}
	
	@Transient
	public TopicDetails getTopic() {
		return getTweetTopicId().getTopic();
	}
	
	public void setTopic(TopicDetails topic) {
		getTweetTopicId().setTopic(topic);
	}
	
	@Transient
	public TweetDetails getTweetDetails() {
		return getTweetTopicId().getTweetDetails();
	}
	
	public void setTweetDetails(TweetDetails tweetDetails) {
		getTweetTopicId().setTweetDetails(tweetDetails);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tweetTopicId == null) ? 0 : tweetTopicId.hashCode());
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
		TweetTopicDetails other = (TweetTopicDetails) obj;
		if (tweetTopicId == null) {
			if (other.tweetTopicId != null)
				return false;
		} else if (!tweetTopicId.equals(other.tweetTopicId))
			return false;
		return true;
	}
	
}
