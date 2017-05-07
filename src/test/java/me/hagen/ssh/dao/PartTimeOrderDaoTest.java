package me.hagen.ssh.dao;

import static org.junit.Assert.*;
import me.hagen.ssh.service.UserService;
import me.hagen.ssh.util.SpringTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PartTimeOrderDaoTest extends SpringTestBase {
	@Autowired
	public PartTimeOrderDao ptoDao;
	@Autowired
	WorkerOrderDao workerOrderDao;
	@Autowired
	PartTimeCommentDao PartTimeCommentDao;
	@Autowired
	BossOrderDao bossOrderDao;

//	@Test
//	public void testShowDetail() {
//		int id = 5;
//		ptoDao.ShowDetail(id);
//	}
//
//	@Test
//	public void testshowMainOrder() {
//		ptoDao.showMainOrder(0);
//	}
//	
//	@Test
//	public void testDeletePartTimeOrder(){
//		ptoDao.ShowRelative();
//	}
	
	@Test
	public void testDeletePartTimeOrder(){
		ptoDao.DeletePartTimeOrder(200);
	}
	
	
//	
//	@Test
//	public void testShowByDistance(){
//		int time = 0;
//		float lon = 121.48f;
//		float lat = 31.22f; 
//		ptoDao.showByDistance(time, lon, lat);
//	}

}
