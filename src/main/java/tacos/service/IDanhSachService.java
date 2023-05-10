package tacos.service;

import org.aspectj.apache.bcel.classfile.Module.Require;

import tacos.entity.Admin;
import tacos.entity.DanhSach;

public interface IDanhSachService {
	DanhSach getDanhSachByOffice(String id);
	void saveDanhSach(DanhSach ds);
	void deleteDanhSachByOffice(String id);
}
