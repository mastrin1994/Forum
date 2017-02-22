package wyznikiewicz;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

@Transactional
public interface MessageRepository extends Repository<Message, Integer>
{
	public Message save(Message message);
	public void delete(Message message);
	public long count();
	public Message findByIdIn(Integer id);
	public List<Message> findAll();
	public List<Message> findAllByTopicIdIn(Integer topicId);
}