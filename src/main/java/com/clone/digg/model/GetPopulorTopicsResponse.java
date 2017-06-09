/**
 * 
 */
package com.clone.digg.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 */
public class GetPopulorTopicsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2582668594950742430L;
	List<Topic> populorTopics = null;
	/**
	 * 
	 */
	public GetPopulorTopicsResponse() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the populorTopics
	 */
	public List<Topic> getPopulorTopics() {
		return populorTopics;
	}
	/**
	 * @param populorTopics the populorTopics to set
	 */
	public void setPopulorTopics(List<Topic> populorTopics) {
		this.populorTopics = populorTopics;
	}

}
