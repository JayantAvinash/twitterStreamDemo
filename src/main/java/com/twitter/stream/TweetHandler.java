package com.twitter.stream;

import java.util.Date;

import com.twitter.bean.TweetDetails;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TweetHandler {

	public static final String oauthConsumerKey = "hDUfEsJvJqAIYiihnwle4xyWw";
	public static final String oauthConsumerSecret = "RJSBVPFUvjKGybUqZi7aelQwYGCANnmub0PSZzLKmXGvuOW8be";
	public static final String oauthAccessToken = "99767223-xOxAziWcoGyWefBf7S9rBDpVIklNKPg1qJedjNoUS";
	public static final String oauthAccessTokenSecret = "QOV5nL0PLEGsu5FPrPzxJap3gkEBZVUBepyyYUKyK4mOV";

	

	public long getTwitterHandleIdByName(String name) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(oauthConsumerKey).setOAuthConsumerSecret(oauthConsumerSecret)
				.setOAuthAccessToken(oauthAccessToken).setOAuthAccessTokenSecret(oauthAccessTokenSecret);
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		User user = twitter.showUser(name);
		return user.getId();
	}

	public void extractAndStoreTweetDetails(String handleName) throws TwitterException {
		long userId = getTwitterHandleIdByName(handleName);

		UserStreamListener listener = new UserStreamListener() {

			@Override
			public void onStatus(Status status) {
				// User user = status.getUser();
				// gets Username
				// String username = status.getUser().getScreenName();
				if (status.getUser().getId() == userId) {
					System.out.println("Status posted");
					String tweet = status.getText();
					Date tweetedAt = status.getCreatedAt();
					System.out.println(tweet);
					//System.out.println(TwitterObjectFactory.getRawJSON(status));
					TweetDetails td = new TweetDetails(tweet, tweetedAt, userId, handleName);
					TweetParser.parseAndStoreTweets(td);
					System.out.println("Tweet saved");
				}

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(long directMessageId, long userId) {
				// TODO Auto-generated methodoauthConsumerKey stub

			}

			@Override
			public void onFriendList(long[] friendIds) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFavorite(User source, User target, Status favoritedStatus) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFollow(User source, User followedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUnfollow(User source, User unfollowedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDirectMessage(DirectMessage directMessage) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListCreation(User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListUpdate(User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserListDeletion(User listOwner, UserList list) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserProfileUpdate(User updatedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserSuspension(long suspendedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserDeletion(long deletedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onBlock(User source, User blockedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUnblock(User source, User unblockedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onQuotedTweet(User source, User target, Status quotingTweet) {
				// TODO Auto-generated method stub

			}

		};
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(listener);
		twitterStream.setOAuthConsumer(oauthConsumerKey, oauthConsumerSecret);
		AccessToken token = new AccessToken(oauthAccessToken, oauthAccessTokenSecret);
		twitterStream.setOAuthAccessToken(token);
		FilterQuery query = new FilterQuery();
		query.follow(new long[] { userId });
		twitterStream.filter(query);
	}

}
