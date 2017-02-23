package wyznikiewicz;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class new_topicController
{
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/new_topic")
    public String getName(HttpServletRequest req, @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model)
        {
        	if(userId.equals("-1"))
        	{
                model.addAttribute("user", new User());
                return "login";           
            } 
        	else 
        	{      
        		Topic topic = new Topic();
        		model.addAttribute("topic", topic);
                return "new_topic";
            }
        }
    
    @PostMapping("/new_topic")
    public String TopicSubmit(@CookieValue(value = "userId", defaultValue = "-1") String userId, @ModelAttribute Topic topic,  Model model)
    {  	
    	System.out.println("topic id = " + topic.getId());
    	System.out.println("user id = " + Integer.parseInt(userId));
    	System.out.println(topic.getName());
    	System.out.println(topic.getContent());
    	
    	Timestamp t = new Timestamp(new Date().getTime());
    	topic.setDate(t);
    	topic.setUserId(Integer.parseInt(userId));
    	
    	model.addAttribute("topic", new Topic());
    	topicRepository.save(topic);
    	return "redirect:/forum";
    }
}
