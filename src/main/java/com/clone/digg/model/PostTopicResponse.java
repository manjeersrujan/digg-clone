/**
 * 
 */
package com.clone.digg.model;

import java.io.Serializable;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 */
public class PostTopicResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2819875166484899616L;

	String topicId;

	public PostTopicResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId
	 *            the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}
