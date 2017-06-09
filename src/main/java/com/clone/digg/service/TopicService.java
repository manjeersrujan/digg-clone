package com.clone.digg.service;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GetPopulorTopicsResponse;
import com.clone.digg.model.GetTopicResponse;
import com.clone.digg.model.PostTopicResponse;
import com.clone.digg.model.Topic;
import com.clone.digg.model.VoteTopicResponse;
import com.clone.digg.model.VoteType;

/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 */
public interface TopicService {

	GetTopicResponse getTopic(String topicId) throws DiggCloneServiceException;

	PostTopicResponse postTopic(String userId, Topic topic) throws DiggCloneServiceException;

	VoteTopicResponse voteTopic(String topicId, VoteType voteType) throws DiggCloneServiceException;

	GetPopulorTopicsResponse getPopulorTopics() throws DiggCloneServiceException;

}