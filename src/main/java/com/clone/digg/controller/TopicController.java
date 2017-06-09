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

@RestController
public class TopicController {

	@Autowired
	TopicService topicService;

	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetTopicResponse> getTopic(
			@RequestParam(name = "topicId", required = true) String topicId) throws DiggCloneServiceException {
		GenericServiceResponse<GetTopicResponse> genericServiceResponse = new GenericServiceResponse<GetTopicResponse>();
		GetTopicResponse getTopicsResponse = topicService.getTopic(topicId);
		genericServiceResponse.setPayload(getTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	@RequestMapping(value = "/topic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<PostTopicResponse> postTopic(
			@RequestParam(name = "userId") String userId, @RequestBody Topic topic)
			throws DiggCloneServiceException {
		GenericServiceResponse<PostTopicResponse> genericServiceResponse = new GenericServiceResponse<PostTopicResponse>();
		PostTopicResponse postTopicResponse = topicService.postTopic(userId, topic);
		genericServiceResponse.setPayload(postTopicResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	@RequestMapping(value = "/vote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<VoteTopicResponse> voteTopic(
			@RequestParam(name = "topicId", required = true) String topicId,
			@RequestParam(name = "voteType", required = true) VoteType voteType) throws DiggCloneServiceException {
		GenericServiceResponse<VoteTopicResponse> genericServiceResponse = new GenericServiceResponse<VoteTopicResponse>();
		VoteTopicResponse voteTopicResponse = topicService.voteTopic(topicId, voteType);
		genericServiceResponse.setPayload(voteTopicResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

	@RequestMapping(value = "/popularTopics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetPopulorTopicsResponse> getPopulorTopics() throws DiggCloneServiceException {
		GenericServiceResponse<GetPopulorTopicsResponse> genericServiceResponse = new GenericServiceResponse<GetPopulorTopicsResponse>();
		GetPopulorTopicsResponse getPopulorTopicsResponse = topicService.getPopulorTopics();
		genericServiceResponse.setPayload(getPopulorTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

}
