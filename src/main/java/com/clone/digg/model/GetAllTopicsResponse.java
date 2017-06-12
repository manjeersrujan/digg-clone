/**
 * 
 */
package com.clone.digg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 */
public class GetAllTopicsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6329795025986514569L;
	List<Topic> topics = new ArrayList<>();
	/**
	 * @return the topics
	 */
	public List<Topic> getTopics() {
		return topics;
	}
	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}



}
