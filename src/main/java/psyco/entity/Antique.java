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
@Table(name = "Antique")
public class Antique {
	public static int STATUS_New = 0;
	public static int STATUS_Proccessing = 1;
	public static int STATUS_Finished = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@Basic
	String title;
	@Basic
	String imageUrl;
	@Basic
	String imageThumbUrl;
	@Basic
	String breifInfo;
	@ManyToOne
	@JoinColumn(name = "user")
	User user;
	@ManyToOne
	@JoinColumn(name = "type")
	TypeTag type;
	@Basic
	double price;
	@Basic
	double priceReference;
	@Basic
	String detailPicUrls;
	@ManyToOne
	@JoinColumn(name = "location")
	City location;
	@Basic
	int status;
	@ManyToOne
	@JoinColumn(name = "age")
	Age age;
	@Basic
	String integrity;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date dateUpload;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date dateLastModify;
	@Basic
	double hight;
	@Basic
	double width;
	@Basic
	double length;

	@PrePersist
	protected void onCreate() {
		this.dateUpload = new Date();
		this.dateLastModify = this.dateUpload;
	}

	@PreUpdate
	protected void onUpdate() {
		this.dateLastModify = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getBreifInfo() {
		return breifInfo;
	}

	public void setBreifInfo(String breifInfo) {
		this.breifInfo = breifInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPriceReference() {
		return priceReference;
	}

	public void setPriceReference(double priceReference) {
		this.priceReference = priceReference;
	}

	public City getLocation() {
		return location;
	}

	public void setLocation(City location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIntegrity() {
		return integrity;
	}

	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

	public Date getDateLastModify() {
		return dateLastModify;
	}

	public void setDateLastModify(Date dateLastModify) {
		this.dateLastModify = dateLastModify;
	}

	public double getHight() {
		return hight;
	}

	public void setHight(double hight) {
		this.hight = hight;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public TypeTag getType() {
		return type;
	}

	public void setType(TypeTag type) {
		this.type = type;
	}

	public String getDetailPicUrls() {
		return detailPicUrls;
	}

	public void setDetailPicUrls(String detailPicUrls) {
		this.detailPicUrls = detailPicUrls;
	}

	@Override
	public String toString() {
		return "Antique [id=" + id + ", title=" + title + ", imageUrl=" + imageUrl + ", imageThumbUrl=" + imageThumbUrl + ", breifInfo=" + breifInfo + ", user=" + user + ", price=" + price + ", priceReference=" + priceReference + ", location=" + location + ", status=" + status + ", age=" + age
				+ ", integrity=" + integrity + ", dateUpload=" + dateUpload + ", dateLastModify=" + dateLastModify + ", hight=" + hight + ", width=" + width + ", length=" + length + "]";
	}

}
