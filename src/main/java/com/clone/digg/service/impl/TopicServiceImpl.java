package com.clone.digg.service.impl;

import org.springframework.stereotype.Component;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GetTopicsResponse;
import com.clone.digg.service.TopicService;

@Component
public class TopicServiceImpl implements TopicService {

	@Override
	public GetTopicsResponse getTopics() throws DiggCloneServiceException {
		GetTopicsResponse getTopicsResponse = new GetTopicsResponse();
		return getTopicsResponse;
	}

}
