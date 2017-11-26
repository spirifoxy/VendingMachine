package spirifoxy.com.github.vmtest;

import java.util.Map;

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
import spirifoxy.com.github.vmtest.Model.VM;
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
		mav.addObject("currentPaidAmount", server.getVM().getCurrentPaidSum());
		
		return mav;
	}
	
	@RequestMapping(value = "/spendCoin", method = RequestMethod.POST)
	public @ResponseBody String spendCoin(@ModelAttribute("user") User user, 
			@RequestParam(value = "denom", required = true)Integer denom){
		
		VM vm = Server.getInstance().getVM();
		
		try {
			user.spendCoin(denom);
		} catch (Exception e) {
			return "{ \"error\": \"" + e.getMessage() + "\"}";
		}
		
		vm.addCoin(denom);
		
		return "{\"userCoins\": " + user.getCoinsAmount(denom)
			+ " ,\"currentPaid\": " + vm.getCurrentPaidSum()
			+ "}";
    }
	
	@RequestMapping(value = "/getChange", method = RequestMethod.POST)
	public @ResponseBody String getChange(@ModelAttribute("user") User user){
		
		VM vm = Server.getInstance().getVM();

		try {
			Wallet change = vm.giveChange(vm.getCurrentPaidSum());
			user.getChange(change);
		} catch (Exception e) {
			return "{ \"error\": \"" + e.getMessage() + "\"}";
		}
		
		return "{ \"message\": \"OK\"}";
    }
	
	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public @ResponseBody String buyProduct(@ModelAttribute("user") User user, 
			@RequestParam(value = "productName", required = true)String productName){
		
		VM vm = Server.getInstance().getVM();

		try {
			vm.sellProduct(productName);
			
		} catch (Exception e) {
			return "{ \"error\": \"" + e.getMessage() + "\"}";
		}
		
		return "{ \"message\": \"Thanks!\"}";
    }
	
}
