package spirifoxy.com.github.vmtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import spirifoxy.com.github.vmtest.Model.Server;
import spirifoxy.com.github.vmtest.Model.User;
import spirifoxy.com.github.vmtest.Model.Wallet;


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
	
	@RequestMapping(value = "/spendCoin", method = RequestMethod.POST)
	public @ResponseBody String spendCoin(@ModelAttribute("user") User user, 
			@RequestParam(value = "denom", required = true)Integer denom){
		
		Server server = Server.getInstance();
		
		try {
			user.spendCoin(denom);
		} catch (Exception e) {
			return "{ \"error\": \"" + e.getMessage() + "\"}";
		}
		
		server.getVM().addCoin(denom);
		
		return "{\"userCoins\": " + user.getCoinsAmount(denom) + " ,\"vmCoins\": " + server.getVM().getCoinsAmount(denom) + "}";
    }
}
