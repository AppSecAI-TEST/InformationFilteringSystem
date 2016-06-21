package infofilter;

import java.io.Serializable;

/**
 * The <code>Reliability</code> class defines fields that allow
 * us to calculate reliability of each client agent (agent that
 * sends articles) with respect to server agent (agent that
 * receives articles).
 * 
 * @author Tran Xuan Hoang
 */
public class Reliability implements Serializable {
	/** Serial version. */
	private static final long serialVersionUID = 1L;

	/** Name of the agent. */
	private String agentName;

	/** Number of sening session so far. */
	private int session;

	/** Reliability of agent (scale: 100%). */
	private double reliability;

	/** Number of articles selected by server agent (or ranking agent). */
	private int numOfSelectedArticles;

	transient public ReliabilityGUI gui;

	public Reliability(String agentName) {
		this.setAgentName(agentName);
		this.setSession(0);
		this.setReliability(0);
		this.setNumOfSelectedArticles(0);

		gui = new ReliabilityGUI(agentName);
	}

	public void updateReliability(int selected, int eliminated) {
		// update session (number of sending session)
		session++;

		// update reliability
		if (numOfSelectedArticles == 0) {
			reliability = (double) selected / (selected + eliminated);
		} else {
			reliability = (numOfSelectedArticles + selected) * reliability /
					(numOfSelectedArticles + (selected + eliminated) * reliability);
		}

		//reliability *= 100;

		// update the cumulative number of selected article
		// from session 0 to current session
		numOfSelectedArticles += selected;

		// update the GUI showing reliability information
		gui.updateReliabilityGUI(this);
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public double getReliability() {
		return reliability;
	}

	public void setReliability(double reliability) {
		this.reliability = reliability;
	}

	public int getNumOfSelectedArticles() {
		return numOfSelectedArticles;
	}

	public void setNumOfSelectedArticles(int numOfSelectedArticles) {
		this.numOfSelectedArticles = numOfSelectedArticles;
	}

	public String toString() {
		return agentName + ": session = " + session +
				", reliability = " + reliability +
				", selected articles = " + numOfSelectedArticles;
	}
} // end class Reliability