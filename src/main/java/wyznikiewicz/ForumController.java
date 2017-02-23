package wyznikiewicz;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForumController
{
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/forum")
    public String getName(HttpServletRequest req, 
        @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model)
    {
    	if(userId.equals("-1"))
    	{
            model.addAttribute("user", new User());
            return "login";           
        } 
    	else 
    	{
        	List<Topic> topics = topicRepository.findAll();
        	List<UserRelatedTopic> userTopics = new ArrayList<UserRelatedTopic>();
        	
        	for(Topic t : topics) 
        	{
        		User owner = userRepository.findByIdIn(t.getUserId());
        		userTopics.add(new UserRelatedTopic(t, owner));
        	}
        	model.addAttribute("list", userTopics);
        	//model.addAttribute("ciacho", userId);
            return "forum";
        }
    }
    
    @PostMapping("http://localhost:8080/")
    public String MessageSubmit(@CookieValue(value = "-1", defaultValue = "-1") String userId)
    {
     		
    	return "/";
    }
    
}
