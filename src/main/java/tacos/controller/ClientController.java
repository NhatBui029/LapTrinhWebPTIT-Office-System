package tacos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tacos.entity.Office;
import tacos.service.IOfficeService;
import tacos.entity.Contract;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private IOfficeService iOffSer;

	@GetMapping("/home")
	public String home(Model model, HttpServletRequest request) {
		List<Office> list = iOffSer.getAllOfficeClient().subList(0, 6);
		model.addAttribute("offices", list);

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getValue());
			}
		}

		return "client/home";
	}

	@GetMapping("/office")
	public String viewOffice(Model model,HttpServletResponse response) {
		model.addAttribute("offices", iOffSer.getAllOfficeClient());
		return "client/office";
	}

	@PostMapping("/search")
	public String searchOffice(Model model, HttpServletRequest request) {
		String input = request.getParameter("input");
		List<Office> search = iOffSer.getOfficeBySearchClient(input);
		List<Office> consist = iOffSer.getAllOfficeClient();
		search.retainAll(consist);
		model.addAttribute("offices", search);
		return "client/office";
	}

	@PostMapping("/filter")
	public String filterOffice(Model model, HttpServletRequest request) {
		String level = request.getParameter("level");
		String price = request.getParameter("price");
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
		switch(area) {
		case 0:
			officeMin=0;
			officeMax=99999999;
			break;
		case 200:
			officeMin=0;
			officeMax=200;
			break;
		case 500:
			officeMin=200;
			officeMax=500;
			break;
		case 1000:
			officeMin=500;
			officeMax=1000;
			break;
		case 2000:
			officeMin=1000;
			officeMax=2000;
			break;
		default:
			officeMin=2000;
			officeMax=99999999;
		}

		model.addAttribute("offices", iOffSer.getOfficeByFilterClient(level, priceMin, priceMax,officeMin,officeMax));
		return "client/office";
	}

	@GetMapping("/office/detail/{id}")
	public String deltailOffice(@PathVariable String id,Model model) {
		model.addAttribute("office", iOffSer.getOfficeById(id));
		return "client/detail";
	}
	
	@GetMapping("/require/{id}")
	public String getRequire(@PathVariable String id,Model model) {
		model.addAttribute("require", "require");
		model.addAttribute("office", iOffSer.getOfficeById(id));
		return "client/detail";
	}
	@PostMapping("/require/{id}")
	public String require(@PathVariable String id,Model model,HttpServletRequest request) {
		String  payment = request.getParameter("payment");
		int deposits = Integer.parseInt(request.getParameter("deposits"));
		String dateStart = request.getParameter("start-date");
		String dateEnd = request.getParameter("end-date");
		String idCus="";
		Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("setUser")) {
                    idCus = cookie.getValue();
                    break;
                }
            }
        }
        
        String idContract = id+"-"+idCus;
        
        LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateSign = currentDate.format(formatter);

		String term = "Các điều khoản cụ thể có thể thay đổi tùy thuộc "
				+ "vào thỏa thuận giữa hai bên và quy định pháp luật địa phương";
		
		Contract contract = new Contract(idContract, id, idCus, deposits, dateSign, dateStart, dateEnd, payment, term);
		System.out.println(contract);
		
		
		model.addAttribute("office", iOffSer.getOfficeById(id));
		return "client/detail";
		
	}
}
