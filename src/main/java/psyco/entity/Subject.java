package psyco.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lipeng on 15/6/24.
 */
@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Basic
    int code;
    @Basic
    int level;
    @Basic
    String name;
    @Basic
    Integer group;
    @ManyToOne
    @JoinColumn(name = "parentID", nullable = true)
    /*if database doesn't has the foreign key,this will work
    @JoinColumn(name = "id",insertable = false,updatable = false,nullable = true)*/
     Subject parent;

    @Transient
    //@Transient let hibernate ignore this property,this is used by JSON format
    /*@OneToMany
    @JoinColumn(name = "parentID", nullable = true)*/
    List<Subject> children;

    public Subject() {
    }

    public Subject(int code, int level, String name) {
        this.code = code;
        this.level = level;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Subject getParent() {
        return parent;
    }

    public void setParent(Subject parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public List<Subject> getChildren() {
        return children;
    }

    public void setChildren(List<Subject> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", code=" + code +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", parent=" + (parent == null ? "null" : parent.getName()) +
                '}';
    }
}
