package me.hagen.ssh.dao;

import me.hagen.ssh.util.SpringTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RunOrderDao extends SpringTestBase{
	@Autowired
	public RunoffOrderDao rooDao;
	@Autowired
	public WorkerRunDao workRunDao;
	
	@Test
	public void testDeletePartTimeOrder(){
		
		workRunDao.ShowAll(39);
	}
}
