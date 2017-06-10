package com.clone.digg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GenericServiceResponse;
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
@RestController
public class TopicController {

	@Autowired
	TopicService topicService;

	/**
	 * @param topicId
	 * @return
	 * @throws DiggCloneServiceException
	 * 
	 *             Get a single topic
	 */
	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetTopicResponse> getTopic(
			@RequestParam(name = "topicId", required = true) String topicId) throws DiggCloneServiceException {
		GenericServiceResponse<GetTopicResponse> genericServiceResponse = new GenericServiceResponse<GetTopicResponse>();
		GetTopicResponse getTopicsResponse = topicService.getTopic(topicId);
		genericServiceResponse.setPayload(getTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	/**
	 * @return
	 * @throws DiggCloneServiceException
	 * 
	 *             Get all topics
	 */
	@RequestMapping(value = "/allTopics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetAllTopicsResponse> getAllTopics() throws DiggCloneServiceException {
		GenericServiceResponse<GetAllTopicsResponse> genericServiceResponse = new GenericServiceResponse<GetAllTopicsResponse>();
		GetAllTopicsResponse getAllTopicsResponse = topicService.getAllTopics();
		genericServiceResponse.setPayload(getAllTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	/**
	 * @param userId
	 * @param topic
	 * @return
	 * @throws DiggCloneServiceException
	 * 
	 *             Create a topic
	 */
	@RequestMapping(value = "/topic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<PostTopicResponse> postTopic(
			@RequestParam(name = "userId", required = false) String userId, @RequestBody Topic topic)
			throws DiggCloneServiceException {
		GenericServiceResponse<PostTopicResponse> genericServiceResponse = new GenericServiceResponse<PostTopicResponse>();
		PostTopicResponse postTopicResponse = topicService.postTopic(userId, topic);
		genericServiceResponse.setPayload(postTopicResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	/**
	 * @param topicId
	 * @param voteType
	 * @return
	 * @throws DiggCloneServiceException
	 * 
	 *             Vote a topic with with any VoteType
	 */
	@RequestMapping(value = "/vote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<VoteTopicResponse> voteTopic(
			@RequestParam(name = "topicId", required = true) String topicId,
			@RequestParam(name = "voteType", required = false) VoteType voteType) throws DiggCloneServiceException {
		GenericServiceResponse<VoteTopicResponse> genericServiceResponse = new GenericServiceResponse<VoteTopicResponse>();
		VoteTopicResponse voteTopicResponse = topicService.voteTopic(topicId, voteType);
		genericServiceResponse.setPayload(voteTopicResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	/**
	 * @param voteType
	 * @return
	 * @throws DiggCloneServiceException
	 * 
	 *             Get popular topics for based on any VoteType
	 */
	@RequestMapping(value = "/popularTopics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetPopulorTopicsResponse> getPopulorTopics(
			@RequestParam(name = "voteType", required = false) VoteType voteType) throws DiggCloneServiceException {
		GenericServiceResponse<GetPopulorTopicsResponse> genericServiceResponse = new GenericServiceResponse<GetPopulorTopicsResponse>();
		GetPopulorTopicsResponse getPopulorTopicsResponse = topicService.getPopulorTopics(voteType);
		genericServiceResponse.setPayload(getPopulorTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

}
