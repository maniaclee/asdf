package psyco.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TypeTag")
public class TypeTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Basic
	String name;
	@Basic
	Integer parent;
	// @ManyToOne
	// @JoinColumn(name = "parent")
	// TypeTag parent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent", referencedColumnName = "id")
	List<TypeTag> children;

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

	public List<TypeTag> getChildren() {
		return children;
	}

	public void setChildren(List<TypeTag> children) {
		this.children = children;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "TypeTag [id=" + id + ", name=" + name + ", parent=" + parent + "]";
	}

}
