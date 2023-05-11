
package tacos.controller;

import java.util.ArrayList;
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
import tacos.entity.DanhSach;
import tacos.entity.Office;
import tacos.entity.Contract;
import tacos.service.IContractService;
import tacos.service.ICustomerService;
import tacos.service.IDanhSachService;
import tacos.service.IOfficeService;

@Controller
@RequestMapping("/admin/office")
public class AdminOfficeController {
	@Autowired
	private IOfficeService iOffSer;
//	@Autowired
//	private ICustomerService iCusSer;

	@Autowired
	private IDanhSachService iDSSer;

	@Autowired
	private IContractService iCtrSer;

	@GetMapping("")
	public String homeOffice(Model model, HttpServletRequest request, HttpServletResponse response) {
		String idCus = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("setUser")) {
					idCus = cookie.getValue();
					break;
				}
			}
		}
		if (idCus.compareTo("") != 0) {

			model.addAttribute("countDeleted", iOffSer.getAllOfficeDeleted().size());
			model.addAttribute("offices", iOffSer.getAllOffice());
			return "admin/office";
		} else {
			model.addAttribute("error", "Bạn chưa đăng nhập !");
			return "authentication/error";
		}

	}

	@GetMapping("/trash")
	public String getTrashOffice(Model model) {
		model.addAttribute("count", iOffSer.getAllOffice().size());
		model.addAttribute("offices", iOffSer.getAllOfficeDeleted());
		return "admin/officeTrash";
	}

	@PostMapping("/delete/{id}")
	public String deleteOffice(Model model, @PathVariable String id) {
		Office office = iOffSer.getOfficeById(id);
		office.setDeleted("true");
		iOffSer.saveOffice(office);
		return "redirect:/admin/office";
	}

	@PostMapping("/destroy/{id}")
	public String destroyOffice(Model model, @PathVariable String id) {
		iOffSer.deleteOfficeById(id);
		return "redirect:/admin/office/trash";
	}

	@GetMapping("/restore/{id}")
	public String restoreOffice(Model model, @PathVariable String id) {
		Office office = iOffSer.getOfficeById(id);
		office.setDeleted("false");
		iOffSer.saveOffice(office);
		return "redirect:/admin/office/trash";
	}

	@GetMapping("/add")
	public String getAddOffice(Model model) {
		model.addAttribute("office", new Office());
		return "admin/addOffice";
	}

	@PostMapping("/add")
	public String addOffice(Office office, Model model) {
		Office off = iOffSer.getOfficeById(office.getIdOffice());
		if (off.getIdOffice() != null) {
			model.addAttribute("error", "Mã văn phòng đã tồn tại");
			model.addAttribute("office", office);
			return "admin/addOffice";
		}
		iOffSer.saveOffice(office);
		return "redirect:/admin/office";
	}

	@GetMapping("/edit/{id}")
	public String editOffice(Model model, @PathVariable String id) {
		model.addAttribute("office", iOffSer.getOfficeById(id));
		return "admin/editOffice";
	}

	@PostMapping("/edit")
	public String editOffice(Office office, Model model) {
		iOffSer.saveOffice(office);
		return "redirect:/admin/office";
	}

	@PostMapping("/action")
	public String actionCustomer(@RequestParam(value = "ids") String[] ids,
			@RequestParam(value = "action") String action) {
		switch (action) {
		case "restore":
			for (String id : ids) {
				Office office = iOffSer.getOfficeById(id);
				office.setDeleted("false");
				iOffSer.saveOffice(office);
			}
			break;
		case "destroy":
			for (String id : ids) {
				iOffSer.deleteOfficeById(id);
			}
			break;
		default:
			break;
		}

		return "redirect:/admin/office/trash";
	}

	@PostMapping("/filter")
	public String filterOffice(Model model, HttpServletRequest request) {
		String level = request.getParameter("level");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		int area = Integer.parseInt(request.getParameter("area"));
		double priceMin;
		double priceMax;

		double officeMin;
		double officeMax;

		if (price.compareTo("0") == 0) {
			priceMin = 0;
			priceMax = 1000;
		} else if (price.compareTo("51") == 0) {
			priceMin = 51;
			priceMax = 1000;
		} else {
			priceMin = Double.parseDouble(price) - 10;
			priceMax = Double.parseDouble(price);
		}
		System.out.println(area);
		switch (area) {
		case 0:
			officeMin = 0;
			officeMax = 99999999;
			break;
		case 200:
			officeMin = 0;
			officeMax = 200;
			break;
		case 500:
			officeMin = 200;
			officeMax = 500;
			break;
		case 1000:
			officeMin = 500;
			officeMax = 1000;
			break;
		case 2000:
			officeMin = 1000;
			officeMax = 2000;
			break;
		default:
			officeMin = 2000;
			officeMax = 99999999;
		}
		List<Office> filter = iOffSer.getOfficeByFilterAdmin(level, priceMin, priceMax, officeMin, officeMax);
		filter.retainAll(iOffSer.getAllOfficeByStatus(status));
		model.addAttribute("countDeleted", iOffSer.getAllOfficeDeleted().size());
		model.addAttribute("offices", filter);
		return "admin/office";
	}

	@GetMapping("/check/{id}")
	public String getCheck(@PathVariable String id, Model model) {
		List<Contract> contracts = new ArrayList();
		System.out.println(iDSSer.getDanhSachByOffice(id));
		String[] list = iDSSer.getDanhSachByOffice(id).getList().split("\\|");
		for (String i : list) {
			String[] a = i.split("\\/");
			Contract contract = new Contract(a[0], a[1], a[2], Integer.parseInt(a[3]), a[4], a[5], a[6], a[7], a[8]);
			contracts.add(contract);
		}
		model.addAttribute("contracts", contracts);
		return "admin/checkOffice";
	}

	@GetMapping("/check/confirm/{id}")
	public String Check(@PathVariable String id, Model model) {
		String[] ma = id.split("-");
		DanhSach ctr = iDSSer.getDanhSachByOffice(ma[0]);
		String[] list = ctr.getList().split("\\|");

		for (String i : list) {
			String[] a = i.split("\\/");
			Office off = iOffSer.getOfficeById(a[1]);
			Contract contract = new Contract(a[0], a[1], a[2], Integer.parseInt(a[3]), a[4], a[5], a[6], a[7], a[8]);
			if (a[0].compareTo(id) == 0) {
				iCtrSer.saveContract(contract);
//				iDSSer.deleteDanhSachByOffice("m11");
				off.setStatus("hired");
				iOffSer.saveOffice(off);
				break;
			}
		}

		return "redirect:/admin/contract";
	}

}
