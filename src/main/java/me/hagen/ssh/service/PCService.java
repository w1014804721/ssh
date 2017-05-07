package me.hagen.ssh.service;

import java.util.HashMap;
import java.util.Map;

import me.hagen.ssh.dao.PartTimeCommentDao;
import me.hagen.ssh.dao.PersonalCenterDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.domain.AverageStar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pcService")
@Transactional
public class PCService {
	@Autowired
	private PersonalCenterDao personalCenterDao;
	@Autowired
	private PartTimeCommentDao partTimeCommentDao;
	@Autowired
	private UserDao userDao;
	
	public Map<String, Object> enter(int userid){
		Map<String, Object> result = new HashMap<String, Object>();
		AverageStar as = partTimeCommentDao.getStar(userid);
		float balance = userDao.getBalance(userid);
		int CountCoupon = userDao.CountCoupon(userid);
		String NickName = userDao.getNickName(userid);
		String pspt = userDao.PassExit(userid);
		int exist = -1;
		if(pspt==null){
			exist = 0;//Œ¥…Ë÷√
		}else
		{
			exist = 1;
		}
		result.put("star1", as.getPTPublisher());
		result.put("star2", as.getPTWorker());
		result.put("balance", balance);
		result.put("CountCoupon", CountCoupon);
		result.put("NickName", NickName);
		result.put("exist", exist);
		return result;
	}
	
	
}
