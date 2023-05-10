package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Admin;
import tacos.entity.Contract;
import tacos.entity.Office;
@Repository
public interface ContractRepository extends CrudRepository<Contract, String>{

	

}
