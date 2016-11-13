package com.twitter.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.twitter.bean.TopicDetails;
import com.twitter.bean.TweetDetails;
import com.twitter.bean.TweetTopicDetails;

public class TweetDao {
	private static SessionFactory factory;

	// Returns session factory object
	private static SessionFactory getSessionFactory() {
		try {
			if (factory == null) {
				System.out.println("Hibernate Annotation Configuration loaded");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
				System.out.println("Hibernate Annotation serviceRegistry created");
				factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			}
			return factory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static List<TopicDetails> getTopicsDiscussed(Date date) {
		factory = getSessionFactory();
		Session session = factory.openSession();
		List<TopicDetails> topics = session.createQuery("FROM TopicDetails where date = '" + date + "'").getResultList();
		session.close();
		return topics;
	}
	
	public static void storeTweetsAndTopics(TweetDetails td, Set<TopicDetails> topics) {
		factory = getSessionFactory();
		Session session = factory.openSession();
		
		Transaction tr = session.beginTransaction();
		for(TopicDetails topic : topics) {
			TweetTopicDetails ttd = new TweetTopicDetails();
			List<TopicDetails> dbTopics = session.createQuery("from TopicDetails where topicName = '" + topic.getTopicName() + "' and date = '"  + new Date(System.currentTimeMillis()) + "'").getResultList();
			if(dbTopics != null && !dbTopics.isEmpty()) {
				dbTopics.get(0).setFrequency(dbTopics.get(0).getFrequency() + 1);
				session.update(dbTopics.get(0));
				ttd.setTopic(dbTopics.get(0));
			} else {
				topic.setFrequency(1);
				session.save(topic);
				ttd.setTopic(topic);
			}
			ttd.setTweetDetails(td);
			td.getTweetTopics().add(ttd);
		}
		session.save(td);
		tr.commit();
		session.close();
	}
}
