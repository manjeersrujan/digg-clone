package com.clone.digg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.clone.digg.dao.TopicsDao;
import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GetPopulorTopicsResponse;
import com.clone.digg.model.GetTopicResponse;
import com.clone.digg.model.PostTopicResponse;
import com.clone.digg.model.Topic;
import com.clone.digg.model.VoteTopicResponse;
import com.clone.digg.model.VoteType;
import com.clone.digg.service.TopicService;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 */
@Component
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicsDao topicsDao;

	@Override
	public GetTopicResponse getTopic(String topicId) throws DiggCloneServiceException {
		if (StringUtils.isEmpty(topicId)) {
			throw new DiggCloneServiceException("TOPIC_ID_REQUIRED");
		}

		Topic topic = topicsDao.getTopicById(topicId);
		if (topic == null) {
			throw new DiggCloneServiceException("TOPIC_NOT_FOUND");
		}
		GetTopicResponse getTopicResponse = new GetTopicResponse();
		getTopicResponse.setTopic(topic);
		return getTopicResponse;
	}

	@Override
	public PostTopicResponse postTopic(String userId, Topic topic) throws DiggCloneServiceException {
		if (StringUtils.isEmpty(userId)) {
			userId = "anonymous";
		}
		validateTopic(topic);

		String topicId = topicsDao.postTopic(userId, topic);
		PostTopicResponse postTopicResponse = new PostTopicResponse();
		postTopicResponse.setTopicId(topicId);

		return postTopicResponse;
	}

	private void validateTopic(Topic topic) throws DiggCloneServiceException {
		if (topic == null || StringUtils.isEmpty(topic.getTitle()) || StringUtils.isEmpty(topic.getContent())) {
			throw new DiggCloneServiceException("TOPIC_IS_EMPTY");
		}

		if (topic.getTitle().length() > 50) {
			throw new DiggCloneServiceException("TITLE_OF_TOPIC_TOO_LONG");
		}

		if (topic.getContent().length() > 255) {
			throw new DiggCloneServiceException("CONTENT_OF_TOPIC_TOO_LONG");
		}

	}

	@Override
	public VoteTopicResponse voteTopic(String topicId, VoteType voteType) throws DiggCloneServiceException {
		if (StringUtils.isEmpty(topicId)) {
			throw new DiggCloneServiceException("TOPIC_ID_REQUIRED");
		}

		if (voteType == null) {
			throw new DiggCloneServiceException("VOTE_TYPE_IS_REQUIRED");
		}

		Map<VoteType, Integer> voteCount = topicsDao.voteTopic(topicId, voteType);
		VoteTopicResponse voteTopicResponse = new VoteTopicResponse();
		voteTopicResponse.setVoteCount(voteCount);

		return voteTopicResponse;
	}

	@Override
	public GetPopulorTopicsResponse getPopulorTopics() throws DiggCloneServiceException {
		List<Topic> populorTopics = topicsDao.getPopulorTopics();
		GetPopulorTopicsResponse getPopulorTopicsResponse = new GetPopulorTopicsResponse();
		getPopulorTopicsResponse.setPopulorTopics(populorTopics);
		return getPopulorTopicsResponse;
	}

}
