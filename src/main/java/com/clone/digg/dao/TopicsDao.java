package com.clone.digg.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.Topic;
import com.clone.digg.model.VoteType;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 */
@Component
@EnableAsync
public class TopicsDao {

	private static final int NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED = 20;

	/**
	 * Storage of all topics
	 */
	Map<String, Topic> allTopics = new HashMap<>();
	/*
	 * A counter to generate IDs for each topic
	 */
	int topicIdCounter = 0;
	int minVotesToBePopulor = 1;

	/**
	 * @param topicId
	 * @return
	 */
	public Topic getTopicById(String topicId) {
		/*
		 * Synchronizing map during get also. Because, get may return wrong
		 * value during re-hash of map when size increased.
		 */
		synchronized (allTopics) {
			return allTopics.get(topicId);
		}
	}

	/**
	 * @param userId
	 * @param topic
	 * @return
	 */
	public String postTopic(Topic topic) {
		/*
		 * This synchronization prevernts, different topics to have same ID.
		 */
		synchronized (allTopics) {
			topicIdCounter++;
			String newTopicId = Integer.toString(topicIdCounter);
			topic.setId(newTopicId);
			allTopics.put(newTopicId, topic);
			return newTopicId;
		}
	}

	/**
	 * @param topicId
	 * @param voteType
	 * @return
	 * @throws DiggCloneServiceException
	 */
	public Map<VoteType, Integer> voteTopic(String topicId, VoteType voteType) throws DiggCloneServiceException {

		if (voteType == null) {
			throw new DiggCloneServiceException("VOTE_TYPE_IS_REQUIRED");
		}

		if (StringUtils.isEmpty(topicId)) {
			throw new DiggCloneServiceException("TOPIC_ID_REQUIRED");
		}
		Topic topic = null;

		/*
		 * Synchronizing map during get also. Because, get may return wrong
		 * value during re-hash of map when size increased.
		 */
		synchronized (allTopics) {
			topic = allTopics.get(topicId);
		}
		if (topic == null) {
			throw new DiggCloneServiceException("TOPIC_NOT_FOUND");
		}
		topic.vote(voteType);
		updatePopulorTopicsWithNewVote(topic, voteType);
		return topic.getVoteCount();
	}

	/**
	 * @param voteType
	 * @return
	 */
	public List<Topic> getPopulorTopics(VoteType voteType) {
		if (voteType == null) {
			voteType = VoteType.UPVOTE;
		}
		List<Topic> populorTopicsList = new ArrayList<>();
		populorTopicsList.addAll(voteType.getPopulorTopics());
		Collections.sort(populorTopicsList, voteType.getComparator());

		return populorTopicsList;
	}

	/**
	 * @param topic
	 * @param voteType
	 * @throws DiggCloneServiceException
	 */
	@Async
	public void updatePopulorTopicsWithNewVote(Topic topic, VoteType voteType) throws DiggCloneServiceException {
		if (topic == null) {
			throw new DiggCloneServiceException("TOPIC_REQUIRED_TO_UPDATE_POPULOR_TOPICS");
		}

		if (voteType == null) {
			throw new DiggCloneServiceException("VOTE_TYPE_REQUIRED_TO_UPDATE_POPULOR_TOPICS");
		}

		PriorityQueue<Topic> populorTopics = voteType.getPopulorTopics();
		/*
		 * Synchronizing to make sure popular topics map is in consistent state.
		 */
		synchronized (populorTopics) {
			populorTopics.remove(topic);
			Integer count = topic.getVoteCount().get(voteType);
			if ((count != null)) {
				Topic peekTopic = populorTopics.peek();
				if (peekTopic != null) {
					Integer peekVoteCount = peekTopic.getVoteCount().get(voteType);
					if (peekVoteCount == null || count > peekVoteCount
							|| populorTopics.size() < NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED) {
						populorTopics.offer(topic);
					}
				} else {
					populorTopics.offer(topic);
				}
			}
			/*
			 * Removing topics which are not in top list
			 */
			while (populorTopics.size() > NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED) {
				populorTopics.remove();
			}

		}
	}

	/**
	 * @return
	 */
	public List<Topic> getAllTopics() {
		synchronized (allTopics) {
			return new ArrayList<Topic>(allTopics.values());
		}

	}

}
