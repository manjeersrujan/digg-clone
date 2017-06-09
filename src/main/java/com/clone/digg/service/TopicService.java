package com.clone.digg.service;

import com.clone.digg.exception.DiggCloneServiceException;
import com.clone.digg.model.GetTopicsResponse;

public interface TopicService {

	GetTopicsResponse getTopics() throws DiggCloneServiceException;

}