package tacos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.entity.Admin;
import tacos.repository.AdminRepository;

@Service
public class AdminService  implements IAdminService{
	@Autowired
	private AdminRepository adReps;
	
	public Admin getAdminByUser(String user) {
//		Optional<Admin> optionalAdmin= adReps.findById(user);
//		Admin admin = optionalAdmin.orElse(new Admin( ));
//		return admin;
		return adReps.findByUser(user);
	}

	@Override
	public Admin getAdminByUserAndPassword(String user, String password) {
		return adReps.findByUserAndPassword(user, password);
	}
}
