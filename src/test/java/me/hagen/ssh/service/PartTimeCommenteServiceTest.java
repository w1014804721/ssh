package me.hagen.ssh.service;

import static org.junit.Assert.*;
import me.hagen.ssh.dao.PartTimeCommentDao;
import me.hagen.ssh.util.SpringTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PartTimeCommenteServiceTest extends SpringTestBase {

	@Autowired
	public PartTimeCommentService pcs;
	@Autowired
	public PictureService ps;
	@Autowired
	public RunCommentService rc;
	@Autowired
	PartTimeCommentService ptcService;
	
	
	@Test
	public void testPubishNewComment() {
		ptcService.CommentAsWorker(37);
	}

}
