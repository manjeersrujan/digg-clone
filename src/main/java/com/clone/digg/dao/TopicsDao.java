package com.clone.digg.dao;

import java.util.ArrayList;
import java.util.Comparator;
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

@Component
@EnableAsync
public class TopicsDao {

	private static final int NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED = 5;
	PriorityQueue<Topic> populorTopics = new PriorityQueue<>(new Comparator<Topic>() {

		@Override
		public int compare(Topic o1, Topic o2) {
			if ((o1 == null || o1.getVoteCount() == null || o1.getVoteCount().get(VoteType.UPVOTE) == null)
					&& (o2 == null || o2.getVoteCount() == null || o2.getVoteCount().get(VoteType.UPVOTE) == null)) {
				return 0;
			} else if (o1 == null || o1.getVoteCount() == null || o1.getVoteCount().get(VoteType.UPVOTE) == null) {
				return -1;
			} else if (o2 == null || o2.getVoteCount() == null || o2.getVoteCount().get(VoteType.UPVOTE) == null) {
				return 1;
			} else {
				return o1.getVoteCount().get(VoteType.UPVOTE) - o2.getVoteCount().get(VoteType.UPVOTE);
			}
		}
	});
	Map<String, Topic> allTopics = new HashMap<>();
	int topicIdCounter = 0;
	int minVotesToBePopulor = 1;

	public Topic getTopicById(String topicId) {
		synchronized (allTopics) {
			return allTopics.get(topicId);
		}
	}

	public String postTopic(String userId, Topic topic) {
		topic.setCreatedUserId(userId);
		synchronized (allTopics) {
			topicIdCounter++;
			String newTopicId = Integer.toString(topicIdCounter);
			allTopics.put(newTopicId, topic);
			return newTopicId;
		}
	}

	public Map<VoteType, Integer> voteTopic(String topicId, VoteType voteType) throws DiggCloneServiceException {

		if (voteType == null) {
			throw new DiggCloneServiceException("VOTE_TYPE_IS_REQUIRED");
		}

		if (StringUtils.isEmpty(topicId)) {
			throw new DiggCloneServiceException("TOPIC_ID_REQUIRED");
		}
		Topic topic = null;

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

	public List<Topic> getPopulorTopics() {
		List<Topic> populorTopicsList = new ArrayList<>();
		populorTopicsList.addAll(populorTopics);
		return populorTopicsList;
	}

	@Async
	public void updatePopulorTopicsWithNewVote(Topic topic, VoteType voteType) throws DiggCloneServiceException {
		if (topic == null) {
			throw new DiggCloneServiceException("TOPIC_REQUIRED_TO_UPDATE_POPULOR_TOPICS");
		}

		if (voteType == null) {
			throw new DiggCloneServiceException("VOTE_TYPE_REQUIRED_TO_UPDATE_POPULOR_TOPICS");
		}

		synchronized (populorTopics) {
			if (!populorTopics.contains(topic)) {
				Integer count = topic.getVoteCount().get(voteType);
				if (populorTopics.size() < NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED
						|| (count != null && count >= minVotesToBePopulor)) {
					populorTopics.add(topic);
					while (populorTopics.size() > NUMBER_OF_POPULOR_TOPICS_TO_BE_MAINTAINED) {
						populorTopics.remove();
					}
				}
			}
			minVotesToBePopulor = populorTopics.peek().getVoteCount().get(voteType);
		}
	}

}
