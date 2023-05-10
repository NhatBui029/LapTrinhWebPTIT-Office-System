package tacos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.entity.Admin;
import tacos.entity.Contract;
import tacos.repository.AdminRepository;
import tacos.repository.ContractRepository;

@Service
public class ContractService  implements IContractService{
	@Autowired
	private ContractRepository ctrReps;

	@Override
	public void saveContract(Contract contract) {
		ctrReps.save(contract);	
	}

	@Override
	public List<Contract> getAllContract() {
		return (List<Contract>) ctrReps.findAll();
	}
	
	
}
