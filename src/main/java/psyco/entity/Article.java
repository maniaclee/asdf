package psyco.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

	private static final long serialVersionUID = -8992457659477887934L;
	@Id
	@GeneratedValue
	int id;
	@Basic
	String title;
	@Basic
	String url;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date create;

	@PrePersist
	protected void onCreate() {
		this.create = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", url=" + url + ", create=" + create + "]";
	}


}
