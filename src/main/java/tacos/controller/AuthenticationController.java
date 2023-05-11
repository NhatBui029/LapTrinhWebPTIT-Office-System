package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import tacos.entity.Admin;
import tacos.entity.Customer;
import tacos.service.IAdminService;
import tacos.service.ICustomerService;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private IAdminService iAdSer;
	@Autowired
	private ICustomerService iCusSer;
	
//	@GetMapping("/login")
//	public String getlogin(Model model) {
//		model.addAttribute("admin", new Admin());
//		return "login";
//	}
	
	@PostMapping("/login")
	public String login(Admin admin,Model model, HttpServletResponse response) {
		
		if(iAdSer.getAdminByUserAndPassword(admin.getUser(), admin.getPassword())!=null) {
			ResponseCookie resCookie = ResponseCookie.from("setUser", admin.getUser())
		            .httpOnly(true)
		            .sameSite("None")
		            .secure(true)
		            .path("/")
		            .maxAge(10000)
		            .build();
		    response.addHeader("Set-Cookie", resCookie.toString());
			return "redirect:/admin/customer";
		}
		
		Customer cus = iCusSer.getCustomerByUser(admin.getUser());
		if(cus==null || cus.getDeleted().compareToIgnoreCase("true")==0) {
			model.addAttribute("errorUser", "Tài khoản không tồn tại !");
			return "authentication/login";
		}else {
			if(cus.getPassword().equals(admin.getPassword())==false) {
				model.addAttribute("errorPassword", "Mật khẩu không chính xác !");
				return "authentication/login";
			}
		}

		
		 ResponseCookie resCookie = ResponseCookie.from("setUser", admin.getUser())
		            .httpOnly(true)
		            .sameSite("None")
		            .secure(true)
		            .path("/")
		            .maxAge(10000)
		            .build();
		    response.addHeader("Set-Cookie", resCookie.toString());
		    
		return "redirect:/client/home";
	}
	
	@GetMapping("/logout")
	public String logout(Model model,HttpServletResponse response) {
		 ResponseCookie resCookie = ResponseCookie.from("setUser", "")
		            .httpOnly(true)
		            .sameSite("None")
		            .secure(true)
		            .path("/")
		            .maxAge(0)
		            .build();
		    response.addHeader("Set-Cookie", resCookie.toString());
		    model.addAttribute("admin", new Admin());
		return "authentication/login";
	}
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("customer", new Customer());
		return "authentication/register";
	}
	
	@PostMapping("/register")
	public String register(Customer customer,Model model) {
		System.out.println(customer);
		Customer cus = iCusSer.getCustomerByUser(customer.getUser());
		if(cus != null) {
			model.addAttribute("error", "Tài khoản đã tồn tại !");
			return "authentication/register";
		}
		iCusSer.saveCustomer(customer);
		return "redirect:/client/home";
	}
	
}
