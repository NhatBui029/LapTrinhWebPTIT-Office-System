package tacos.service;

import tacos.entity.Admin;

public interface IAdminService {
	Admin getAdminByUser(String user);
	Admin getAdminByUserAndPassword(String user,String password);
}
