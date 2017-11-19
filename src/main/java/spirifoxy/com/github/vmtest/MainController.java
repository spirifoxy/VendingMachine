package spirifoxy.com.github.vmtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import spirifoxy.com.github.vmtest.Model.Server;
import spirifoxy.com.github.vmtest.Model.User;


@Controller
@SessionAttributes("user")
public class MainController {

	@ModelAttribute("user") 
	public User getUser() {
		return new User();
	}
	
	@RequestMapping("/")
	public ModelAndView index(@ModelAttribute("user") User user, ModelAndView mav) {
		
		mav.setViewName("index");
		
		Server server = Server.getInstance();
		
		mav.addObject("userCoins", user.getWallet().getCoins());
		
		mav.addObject("products", server.getVM().getProducts());
		mav.addObject("vmCoins", server.getVM().getWallet().getCoins());
		mav.addObject("currentPaidAmount", server.getVM().getCurrentPaidAmount());
		
		return mav;
	}
}
