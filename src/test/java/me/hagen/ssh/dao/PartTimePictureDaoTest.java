package me.hagen.ssh.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.hagen.ssh.util.SpringTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PartTimePictureDaoTest extends SpringTestBase{

@Autowired
PartTimePictureDao ptpDao;
@Autowired
RunOrderPictureDao rpDao;
@Autowired
BossRunDAO bossrundao;

	@Test
	public void testGetImgName() {
		bossrundao.enRolledMan(1);
//		System.out.println(result.get(0));
//		System.out.println(result.get(1));
	}

}
