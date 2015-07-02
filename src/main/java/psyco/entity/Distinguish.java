package psyco.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Distinguish")
public class Distinguish {
	public static int STATUS_New = 0;
	public static int STATUS_Processing = 1;
	public static int STATUS_Finished = 2;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@ManyToOne
	@JoinColumn(name = "itemID")
	Antique item = null;
	@ManyToOne
	@JoinColumn(name = "userID")
	User user;
	@Basic
	int status;
	@Basic
	int type;
	@Basic
	int level;

	@Basic
	String discription;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date createTime;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date updateTime;

	@PrePersist
	protected void onCreate() {
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime = new Date();
		this.updateTime = new Date(System.currentTimeMillis());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Antique getItem() {
		return item;
	}

	public void setItem(Antique item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Distinguish [id=" + id + ", item=" + item + ", user=" + user + ", status=" + status + ", discription=" + discription + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
