package com.clone.digg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GenericServiceResponse;
import com.clone.digg.model.GetTopicsResponse;
import com.clone.digg.service.TopicService;

@RestController
public class TopicController {

	@Autowired
	TopicService topicService;

	@RequestMapping(value = "/topics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericServiceResponse<GetTopicsResponse> getPopulorTopics() throws DiggCloneServiceException {
		GenericServiceResponse<GetTopicsResponse> genericServiceResponse = new GenericServiceResponse<GetTopicsResponse>();
		GetTopicsResponse getTopicsResponse = null;
		genericServiceResponse.setPayload(getTopicsResponse);
		genericServiceResponse.setStatus("SUCCESS");
		return genericServiceResponse;
	}

}
