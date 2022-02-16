package pti.sb_squash_MVC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "useridplayerone")
	private int useridPlayerOne;
	@Column(name = "pointsplayerone")
	private int pointsPlayerOne;
	@Column(name = "useridplayertwo")
	private int useridPlayerTwo;
	@Column(name = "pointsplayertwo")
	private int pointsPlayerTwo;
	@Column(name = "locationid")
	private int locationid;
	@Column(name = "date")
	private String date;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUseridPlayerOne() {
		return useridPlayerOne;
	}
	public void setUseridPlayerOne(int useridPlayerOne) {
		this.useridPlayerOne = useridPlayerOne;
	}
	public int getPointsPlayerOne() {
		return pointsPlayerOne;
	}
	public void setPointsPlayerOne(int pointsPlayerOne) {
		this.pointsPlayerOne = pointsPlayerOne;
	}
	public int getUseridPlayerTwo() {
		return useridPlayerTwo;
	}
	public void setUseridPlayerTwo(int useridPlayerTwo) {
		this.useridPlayerTwo = useridPlayerTwo;
	}
	public int getPointsPlayerTwo() {
		return pointsPlayerTwo;
	}
	public void setPointsPlayerTwo(int pointsPlayerTwo) {
		this.pointsPlayerTwo = pointsPlayerTwo;
	}
	public int getLocationid() {
		return locationid;
	}
	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Match [id=" + id + ", useridPlayerOne=" + useridPlayerOne + ", pointsPlayerOne=" + pointsPlayerOne
				+ ", useridPlayerTwo=" + useridPlayerTwo + ", pointsPlayerTwo=" + pointsPlayerTwo + ", locationid="
				+ locationid + ", date=" + date + "]";
	}
	
	
	
}
