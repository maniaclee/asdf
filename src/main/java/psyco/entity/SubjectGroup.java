package psyco.entity;

import javax.persistence.*;

/**
 * Created by lipeng on 15/6/24.
 */
@Entity
@Table(name = "SubjectGroup")
public class SubjectGroup implements  Comparable<SubjectGroup>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Basic
    int num;
    @Basic
    int prioriy;
    @Basic
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrioriy() {
        return prioriy;
    }

    public void setPrioriy(int prioriy) {
        this.prioriy = prioriy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubjectGroup{" +
                "id=" + id +
                ", num=" + num +
                ", prioriy=" + prioriy +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(SubjectGroup o) {
        return this.getPrioriy()-o.getPrioriy();
    }
}
