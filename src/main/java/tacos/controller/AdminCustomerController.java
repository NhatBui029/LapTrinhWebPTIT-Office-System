package tacos.controller;

import java.util.Arrays;
import java.util.List;

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
import tacos.service.ICustomerService;
import tacos.service.IOfficeService;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {

	@Autowired
	private ICustomerService iCusSer;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("countDeleted", iCusSer.getAllCustomerDeleted().size());
		model.addAttribute("customers", iCusSer.getAllCustomer());
		return "admin/customer";
	}

	@GetMapping("/trash")
	public String getTrashCustomer(Model model) {
		model.addAttribute("count", iCusSer.getAllCustomer().size());
		model.addAttribute("customers", iCusSer.getAllCustomerDeleted());
		return "admin/customerTrash";
	}

	@PostMapping("/delete/{id}")
	public String deleteCustomer(Model model, @PathVariable String id) {
		Customer cus = iCusSer.getCustomerByUser(id);
		cus.setDeleted("true");
		iCusSer.saveCustomer(cus);
		return "redirect:/admin/customer";
	}

	@PostMapping("/destroy/{id}")
	public String destroyCustomer(Model model, @PathVariable String id) {
		iCusSer.deleteCustomerByUser(id);
		return "redirect:/admin/customer/trash";
	}


	@GetMapping("/restore/{user}")
	public String restoreCustomer(Model model, @PathVariable String user) {
		Customer cus = iCusSer.getCustomerByUser(user);
		cus.setDeleted("false");
		iCusSer.saveCustomer(cus);
		return "redirect:/admin/customer/trash";
	}

	@PostMapping("/action")
	public String actionCustomer(@RequestParam(value = "ids") String[] ids,
			@RequestParam(value = "action") String action) {
		switch (action) {
		case "restore":
			for(String user : ids) {
				Customer cus = iCusSer.getCustomerByUser(user);
				cus.setDeleted("false");
				iCusSer.saveCustomer(cus);
			}
			break;
		case "destroy":
			for(String user : ids) {
				iCusSer.deleteCustomerByUser(user);
			}
			break;
		default:
			 break;
		}
			 

		return "redirect:/admin/customer/trash";
	}

}
