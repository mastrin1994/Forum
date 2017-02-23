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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class new_messageController
{
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/new_message")
    public String getName(HttpServletRequest req, @RequestParam("id") String topicId, @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model)
        {
        	if(userId.equals("-1"))
        	{
                model.addAttribute("user", new User());
                return "login";           
            } 
        	else 
        	{
        		if(topicId != null)
        		{                 
        			Topic topic = topicRepository.findByIdIn(Integer.parseInt(topicId));
                    model.addAttribute("topicName", topic.getName());
                    model.addAttribute("topicDate", topic.getDate());
                    model.addAttribute("topicId", topic.getId());
                    return "new_message";
        		}
        		
        		return "topic";
            }
        }
    
    @PostMapping("/new_message")
    public String MessageSubmit(HttpServletResponse response, @CookieValue(value = "userId", defaultValue = "-1") String userId,
    @ModelAttribute Message message, @RequestParam("id") String topicId,  Model model)
    {
    	Timestamp t = new Timestamp(new Date().getTime());
    	message.setDate(t);
    	message.setUserId(Integer.parseInt(userId));
    	message.setTopicId(Integer.parseInt(topicId));
    	messageRepository.save(message);
    	
    	return "topic";
    }
}
