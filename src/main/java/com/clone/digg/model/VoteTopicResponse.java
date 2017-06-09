/**
 * 
 */
package com.clone.digg.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 */
public class VoteTopicResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7811147658383368425L;
	Map<VoteType, Integer> voteCount = null;
	/**
	 * 
	 */
	public VoteTopicResponse() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the voteCount
	 */
	public Map<VoteType, Integer> getVoteCount() {
		return voteCount;
	}
	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(Map<VoteType, Integer> voteCount) {
		this.voteCount = voteCount;
	}

}
