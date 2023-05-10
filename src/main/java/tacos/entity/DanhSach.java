package tacos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="danhsach")
public class DanhSach {
	@Id
	private String office;
	
	private String list;

	
	public DanhSach() {
		
	}
	

	public DanhSach(String office, String list) {
		super();
		this.office = office;
		this.list = list;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "DanhSach [idOffice=" + office + ", list=" + list + "]";
	}

	
	
	
}
