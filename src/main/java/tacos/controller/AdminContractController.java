package tacos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tacos.entity.Customer;
import tacos.entity.Office;
import tacos.service.IContractService;
import tacos.service.ICustomerService;
import tacos.service.IOfficeService;

@Controller
@RequestMapping("/admin/contract")
public class AdminContractController {
	@Autowired
	private IOfficeService iOffSer;
	@Autowired
	private ICustomerService iCusSer;
	@Autowired 
	IContractService iCtrSer;

	@GetMapping("")
	public String homeOffice(Model model) {
		System.out.println(iCtrSer.getAllContract());
		model.addAttribute("contracts",iCtrSer.getAllContract());
		return "admin/contract";
	}

	

}
