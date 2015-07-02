package psyco.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "favor")
public class Favor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@ManyToOne
	@JoinColumn(name = "antiqueID")
	Antique antique;
	@ManyToOne
	@JoinColumn(name = "userID")
	User user;

	@Basic
	Date time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Antique getAntique() {
		return antique;
	}

	public void setAntique(Antique antique) {
		this.antique = antique;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Favor [id=" + id + ", antique=" + antique + ", user=" + user.name + ", time=" + time + "]";
	}
}
