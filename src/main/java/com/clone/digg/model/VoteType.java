/**
 * 
 */
package com.clone.digg.model;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Manjeer
 *
 *         Created on Jun 10, 2017
 *         
 *         
 */
public enum VoteType {
	UPVOTE, DOWNVOTE;
	
	/**
	 * PQ to maintain top topics for each VoteType
	 */
	private PriorityQueue<Topic> populorTopics;
	private Comparator<Topic> comparator = null;

	VoteType() {
		/**
		 * @author Manjeer
		 *
		 * Created on Jun 10, 2017
		 * 
		 * Comparator to sort topics based on Vote count of each VoteType
		 */
		class TopicComparator implements Comparator<Topic> {
			VoteType currentVoteType = null;

			TopicComparator(VoteType voteType) {
				this.currentVoteType = voteType;
			}

			@Override
			public int compare(Topic o1, Topic o2) {
				if ((o1 == null || o1.getVoteCount() == null || o1.getVoteCount().get(this.currentVoteType) == null
						|| o1.getVoteCount().get(this.currentVoteType) == 0)
						&& (o2 == null || o2.getVoteCount() == null
								|| o2.getVoteCount().get(this.currentVoteType) == null
								|| o2.getVoteCount().get(this.currentVoteType) == 0)) {
					return 0;
				} else if (o1 == null || o1.getVoteCount() == null
						|| o1.getVoteCount().get(this.currentVoteType) == null
						|| o1.getVoteCount().get(this.currentVoteType) == 0) {
					return -1;
				} else if (o2 == null || o2.getVoteCount() == null
						|| o2.getVoteCount().get(this.currentVoteType) == null
						|| o2.getVoteCount().get(this.currentVoteType) == 0) {
					return 1;
				} else {
					return o1.getVoteCount().get(this.currentVoteType) - o2.getVoteCount().get(this.currentVoteType);
				}
			}

		}
		this.comparator = new TopicComparator(this);
		this.populorTopics = new PriorityQueue<>(comparator);

	}

	/**
	 * @return the populorTopics
	 */
	public PriorityQueue<Topic> getPopulorTopics() {
		return populorTopics;
	}

	/**
	 * @return the comparator
	 */
	public Comparator<Topic> getComparator() {
		return comparator;
	}
}
