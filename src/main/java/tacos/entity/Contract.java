package tacos.entity;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "contracts")
public class Contract {
	@Id
	private String id;

	private String idOffice;

	private String idCustomer;

	private int deposits;

	private String dateSign;

	private String dateStart;

	private String dateEnd;

	private String payment;

	private String term;

	public Contract(String id, String idOffice, String idCustomer, int deposits, String dateSign, String dateStart,
			String dateEnd, String payment,String term) {
		super();
		this.id = id;
		this.idOffice = idOffice;
		this.idCustomer = idCustomer;
		this.deposits = deposits;
		this.dateSign = dateSign;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.payment = payment;
		this.term = term;
	}

	public Contract() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdOffice() {
		return idOffice;
	}

	public void setIdOffice(String idOffice) {
		this.idOffice = idOffice;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getDeposits() {
		return deposits;
	}

	public void setDeposits(int deposits) {
		this.deposits = deposits;
	}

	public String getDateSign() {
		return dateSign;
	}

	public void setDateSign(String dateSign) {
		this.dateSign = dateSign;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", idOffice=" + idOffice + ", idCustomer=" + idCustomer + ", deposits=" + deposits
				+ ", dateSign=" + dateSign + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", payment="
				+ payment + ", term=" + term + "]";
	}

}
