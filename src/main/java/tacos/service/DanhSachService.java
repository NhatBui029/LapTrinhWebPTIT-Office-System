package tacos.service;

import java.util.Optional;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.entity.Admin;
import tacos.entity.DanhSach;
import tacos.entity.Office;
import tacos.repository.AdminRepository;
import tacos.repository.DanhSachRepository;

@Service
public class DanhSachService  implements IDanhSachService{
	@Autowired
	private DanhSachRepository DsReps;


	@Override
	public DanhSach getDanhSachByOffice(String id) {
		return DsReps.findByOffice(id);
		
		
	}


	@Override
	public void saveDanhSach(DanhSach ds) {
		DsReps.save(ds);
	}


	@Override
	public void deleteDanhSachByOffice(String id) {
		DsReps.deleteByOffice(id);
		
	}


	
	
	

}
