package wyznikiewicz;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController
{
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/topic")
    public String getName(HttpServletRequest req, @RequestParam("id") String topicId,
        @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model)
    {
    	if(userId.equals("-1")){
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
                model.addAttribute("topicContent", topic.getContent());
                model.addAttribute("topicId", topic.getId());
                
                User Topicowner = userRepository.findByIdIn(topic.getUserId());
                model.addAttribute("topicAuthor", Topicowner.getLogin());
    			
                List<Message> messages = messageRepository.findAllByTopicIdIn(topic.getId());
                List<UserRelatedMessage> userMessages = new ArrayList<UserRelatedMessage>();
    			
    			
                for(Message m : messages) {
                	User owner = userRepository.findByIdIn(m.getUserId());
                	userMessages.add(new UserRelatedMessage(m, owner));
                }
                
                model.addAttribute("list", userMessages);
                
                return "topic";
    		}
    		
    		return "forum";
        }
    }
    
    @GetMapping("/delete")
    public String deleteTopic(@CookieValue(value = "userId", defaultValue = "-1") String userId, 
    		@RequestParam("id") String topicId, Model model)
    {
        if(userId.equals("-1"))
        {
            model.addAttribute("user", new User());
            return "login";           
        }
        else
        {
        	Topic topic = topicRepository.findByIdIn(Integer.parseInt(topicId));
            if(topic.getUserId() == Integer.parseInt(userId)) 
            {
            	topicRepository.delete(topic);
            }
            return "redirect:/forum";
        }
        
    }
    
}

	
