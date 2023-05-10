package tacos.service;

import java.util.List;

import tacos.entity.Admin;
import tacos.entity.Contract;

public interface IContractService {
	void saveContract(Contract contract);
	List<Contract> getAllContract();
}
