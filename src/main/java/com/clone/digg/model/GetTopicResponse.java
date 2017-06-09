package com.clone.digg.model;

import java.io.Serializable;


/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 */
public class GetTopicResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183746073572590058L;
	
	Topic topic;

	public GetTopicResponse() {
		super();
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
