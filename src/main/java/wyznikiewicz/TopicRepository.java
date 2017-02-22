package wyznikiewicz;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

@Transactional
public interface TopicRepository extends Repository<Topic, Integer>
{
	public Topic save(Topic topic);
	public void delete(Topic topic);
	public long count();
	public Topic findByIdIn(Integer id);
	public List<Topic> findAll();
	public Topic findTopByNameIn(String name);
}