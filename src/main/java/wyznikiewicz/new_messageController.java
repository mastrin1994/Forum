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
public class new_messageController
{
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/new_message")
    public String getName(HttpServletRequest req, @RequestParam("id") String topicId,
    		@CookieValue(value = "userId", defaultValue = "-1") String userId, Model model)
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
                    Message m = new Message();
                    m.setTopicId(topic.getId());
                    model.addAttribute("message", m);
                    model.addAttribute("topic", topic.getId());
                    return "new_message";
        		}
        		
        		return "topic";
            }
        }
    
    @PostMapping("/new_message/{topic_id}")
    public String MessageSubmit(@CookieValue(value = "userId", defaultValue = "-1") String userId,
    @ModelAttribute Message message,  Model model, @PathVariable String topic_id)
    {
    	System.out.println(message.getContent());
    	System.out.println(Integer.parseInt(userId));
    	System.out.println(topic_id);
    	
    	Timestamp t = new Timestamp(new Date().getTime());
    	message.setDate(t);
    	message.setUserId(Integer.parseInt(userId));
    	message.setTopicId(Integer.parseInt(topic_id));
    	System.out.println(message.getContent());
    	
    	messageRepository.save(message);
    
    	model.addAttribute("message", new Message());
    		
    	return "topic";
    }

    }
