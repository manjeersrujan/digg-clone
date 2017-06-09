package com.clone.digg.service.impl;

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
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public PostTopicResponse postTopic(String userId, Topic topic) throws DiggCloneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteTopicResponse voteTopic(String topicId, VoteType voteType) throws DiggCloneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetPopulorTopicsResponse getPopulorTopics() throws DiggCloneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
