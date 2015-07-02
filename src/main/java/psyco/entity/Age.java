package psyco.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Age")
public class Age implements Comparable<Age> ,Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Basic
	String name;
	@Basic
	int age;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Age [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Age o) {
		return this.age - o.age;
	}
}
