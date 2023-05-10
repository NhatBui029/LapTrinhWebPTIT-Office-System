package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.entity.Admin;
@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("admin", new Admin());
		return "authentication/login";
	}
}
