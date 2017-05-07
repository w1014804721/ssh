package me.hagen.ssh.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.util.SpringTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends SpringTestBase {

	@Autowired
	private UserDao userDao;
//	@Test
//	public void testIfExist() {
//		List<Integer> userid = new ArrayList<Integer>();
//		userid.add(1);
//		userid.add(24);
//	userDao.AddBalance(userid, "10");
//	}

	
	@Test
	public void testIfExist() {
	userDao.getBalance(24);
	}
}
