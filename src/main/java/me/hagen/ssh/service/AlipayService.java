package me.hagen.ssh.service;

import me.hagen.ssh.dao.AlipayDao;
import me.hagen.ssh.domain.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PayService")   //出了问题恢复这里
@Transactional
public class AlipayService {
	@Autowired
	AlipayDao alipayDao;
	
	public int BackMoney(String detail){
		int result = -1;
		alipay ap = new alipay();
		ap.setDetail(detail);
		alipayDao.create(ap);
		result = ap.getId();
		return result;
	}
}
