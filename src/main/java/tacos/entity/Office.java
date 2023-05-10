package tacos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="offices")
public class Office {
	@Id
	private String idOffice;
	
	private String location;
	
	private String address;
	
	private double area;
	
	private double height;
	
	private String timeWork;
	
	private String priceOffice;
	
	private double priceOfficeMin;
	
	private double priceOfficeMax;
	
	private double priceService;
	
	private double priceCar;
	
	private double priceMotobike;
	
	private String level;
	
	private String about;
	
	private String image;
	
	private String status;
	
	private String deleted;
	
	public Office() {
		this.status = "still";
		this.deleted = "false";
		this.priceOffice=" ";
	}
	
	

	public Office(String idOffice, String location, String address, double area, double height, String timeWork,
			double priceOfficeMin, double priceOfficeMax, double priceService, double priceCar,
			double priceMotobike, String level, String about, String image) {
		super();
		this.idOffice = idOffice;
		this.location = location;
		this.address = address;
		this.area = area;
		this.height = height;
		this.timeWork = timeWork;
		this.priceOfficeMin = priceOfficeMin;
		this.priceOfficeMax = priceOfficeMax;
		this.priceService = priceService;
		this.priceCar = priceCar;
		this.priceMotobike = priceMotobike;
		this.level = level;
		this.about = about;
		this.image = image;
		this.status = "still";
	}

	
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getIdOffice() {
		return idOffice;
	}

	public void setIdOffice(String idOffice) {
		this.idOffice = idOffice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getTimeWork() {
		return timeWork;
	}

	public void setTimeWork(String timeWork) {
		this.timeWork = timeWork;
	}

	public String getPriceOffice() {
		return Double.toString(priceOfficeMin).replace(".0", "") + "-" + Double.toString(priceOfficeMax).replace(".0", "");
	}

	public void setPriceOffice(String priceOffice) {
		this.priceOffice = priceOffice;
	}

	public double getPriceOfficeMin() {
		return priceOfficeMin;
	}

	public void setPriceOfficeMin(double priceOfficeMin) {
		this.priceOfficeMin = priceOfficeMin;
	}

	public double getPriceOfficeMax() {
		return priceOfficeMax;
	}

	public void setPriceOfficeMax(double priceOfficeMax) {
		this.priceOfficeMax = priceOfficeMax;
	}

	public double getPriceService() {
		return priceService;
	}

	public void setPriceService(double priceService) {
		this.priceService = priceService;
	}

	public double getPriceCar() {
		return priceCar;
	}

	public void setPriceCar(double priceCar) {
		this.priceCar = priceCar;
	}

	public double getPriceMotobike() {
		return priceMotobike;
	}

	public void setPriceMotobike(double priceMotobike) {
		this.priceMotobike = priceMotobike;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "Office [idOffice=" + idOffice + ", location=" + location + ", address=" + address + ", area=" + area
				+ ", height=" + height + ", timeWork=" + timeWork + ", priceOffice=" + priceOffice + ", priceOfficeMin="
				+ priceOfficeMin + ", priceOfficeMax=" + priceOfficeMax + ", priceService=" + priceService
				+ ", priceCar=" + priceCar + ", priceMotobike=" + priceMotobike + ", level=" + level + ", about="
				+ about + ", image=" + image + ", status=" + status + ", deleted=" + deleted + "]";
	}

	
	
	

}