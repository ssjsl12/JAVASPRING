package home.my.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maple.mapper.ItemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DBTester {

	@Autowired
	private ItemInfoMapper mapper;
	
	@Test
	public void test()
	{
		
	    
	    System.out.println(mapper.getList());
	}

}
