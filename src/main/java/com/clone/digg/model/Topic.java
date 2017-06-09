/**
 * 
 */
package com.clone.digg.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 */
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7173421005510475236L;
	String title;
	String content;
	Map<VoteType, Integer> voteCount = new HashMap<>();
	String createdUserId;

	/**
	 * 
	 */
	public Topic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the voteCount
	 */
	public Map<VoteType, Integer> getVoteCount() {
		return voteCount;
	}

	/**
	 * @param voteCount
	 *            the voteCount to set
	 */
	public void setVoteCount(Map<VoteType, Integer> voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the createdUserId
	 */
	public String getCreatedUserId() {
		return createdUserId;
	}

	/**
	 * @param createdUserId
	 *            the createdUserId to set
	 */
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	/**
	 * @param voteType
	 */
	public void vote(VoteType voteType) {
		if (voteType != null) {
			synchronized (this) {
				Integer count = voteCount.get(voteType);
				if (count == null) {
					voteCount.put(voteType, 1);
				} else {
					voteCount.put(voteType, count + 1);
				}
			}
		}
	}

}
