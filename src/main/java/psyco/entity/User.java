package psyco.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import psyco.util.CommonUtil;

@Entity
@Table(name = "User")
public class User {
	@Id
	long id;
	@Basic
	String name;
	@Basic
	int sex;
	@Basic
	String password;
	@Basic
	String email;
	@Basic
	String phone;
	@Basic
	int level;
	@Basic
	String imageUrl = "img/user-anonymous.jpg";
	@Basic
	String imageThumbUrl;
	@ManyToOne
	@JoinColumn(name = "city")
	City city;

	@ManyToOne
	@JoinColumn(name = "location")
	Address location;
	@Basic
	String role = "ROLE_USER";
	@Basic
	boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageThumbUrl() {
		return imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		// return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", password=" + password + ", email=" + email + ", phone=" + phone +
		// ", userLevel=" + userLevel + ", imageUrl=" + imageUrl + ", imageThumbUrl=" + imageThumbUrl + ", city=" + city + ", location=" + location +
		// ", role=" + role
		// + ", enabled=" + enabled + "]";
		return CommonUtil.toJSON(this);
	}

}
