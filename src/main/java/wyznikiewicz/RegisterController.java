package wyznikiewicz;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/register")
    public String getLogin(HttpServletRequest req, 
        @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model){
    	if(userId.equals("-1")){
            model.addAttribute("user", new User());
            return "register";           
        } else {
        	//User user = userRepository.findByIdIn(Integer.parseInt(userId));
            //model.addAttribute("user", user);
            return "forum";
        }
    }
    
    @PostMapping("/register")
    public String loginSubmit(HttpServletResponse response,
    @ModelAttribute User user, Model model) {
    	
    	// Sprawdz w bazie danych, czy user z takim loginem i haslem istnieje
    	User userInDb = userRepository.findTopByLoginIn(user.getLogin());
    	if(userInDb != null){
    		model.addAttribute("error", "Uzytkownik z podanum loginem juz istnieje");            		
            
            return "register";
    	}
    	
    	Timestamp t = new Timestamp(new Date().getTime());
    	user.setDate(t);
    	userRepository.save(user);
    	return "login";
    }
}
