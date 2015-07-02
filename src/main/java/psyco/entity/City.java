package psyco.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "City")
public class City implements Serializable {
	private static final long serialVersionUID = -8845176357726427132L;
	@Id
	@GeneratedValue
	int id;
	@Basic
	String name;
	// @Basic
	// int parentID;
	@Basic
	int type;// 0:china;1:province & city; 2:city;3:district

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentID", nullable = true)
	// @JsonIgnore
	City parent;

	// @Basic
	// int parentID;
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "parentID", referencedColumnName = "id")
	// Set<City> children;

	@Transient
	List<City> children;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public City getParent() {
		return parent;
	}

	public void setParent(City parent) {
		this.parent = parent;
	}

	public List<City> getChildren() {
		return children;
	}

	public void setChildren(List<City> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", type=" + type + ", parent=" + (parent == null ? "null" : parent.name) + "]";
	}

}
