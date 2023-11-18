package Models;

import java.sql.Date;
import java.time.LocalDate;

public class Match {
	private String matchID;
	private String MatchStatus;
	private LocalDate daymatch;
	private String userID1;
	private String userID2;
	public Match(String matchID, String matchStatus, LocalDate daymatch, String userID1, String userID2) {
		super();
		this.matchID = matchID;
		MatchStatus = matchStatus;
		this.daymatch = daymatch;
		this.userID1 = userID1;
		this.userID2 = userID2;
	}
	public Match() {
		super();
	}
	public Match( LocalDate daymatch, String userID1, String userID2) {
		super();
		this.daymatch = daymatch;
		this.userID1 = userID1;
		this.userID2 = userID2;
	}
	public String getMatchID() {
		return matchID;
	}
	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}
	public String getMatchStatus() {
		return MatchStatus;
	}
	public void setMatchStatus(String matchStatus) {
		MatchStatus = matchStatus;
	}
	public LocalDate getDaymatch() {
		return daymatch;
	}
	public void setDaymatch(LocalDate daymatch) {
		this.daymatch = daymatch;
	}
	public String getUserID1() {
		return userID1;
	}
	public void setUserID1(String userID1) {
		this.userID1 = userID1;
	}
	public String getUserID2() {
		return userID2;
	}
	public void setUserID2(String userID2) {
		this.userID2 = userID2;
	}

}
