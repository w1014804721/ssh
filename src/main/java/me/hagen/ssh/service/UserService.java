package me.hagen.ssh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.dialect.Ingres10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.hagen.ssh.dao.AverageStarDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.dao.UserInfoDao;
import me.hagen.ssh.domain.AverageStar;
import me.hagen.ssh.domain.User;
import me.hagen.ssh.domain.UserInfo;
import me.hagen.ssh.dto.BindInfo;
import me.hagen.ssh.dto.youhui;
import me.hagen.ssh.dto.UInfo;

@Service("UserService")
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private AverageStarDao averageStarDao;

	/**
	 * 此方法用于更新用户的个人资料 通过单元测试
	 * */
	public void updateUserInfo(int UserId, String IdentityNumber,
			String TrueName, String password, String BankNumber, String Age,
			String HeadImage, int gender, String Education) {
		UserInfo ui = new UserInfo();
		ui.setUserId(UserId);
		ui.setIdentityNumber(IdentityNumber);
		ui.setTrueName(TrueName);
		ui.setPassword(password);
		ui.setBankNumber(BankNumber);
		ui.setAge(Age);
		ui.setHeadImage(HeadImage);
		ui.setGender(gender);
		ui.setEducation(Education);
		userInfoDao.createOrUpdate(ui);
	}
	
	/**
	 * 判断 登录 还是注册
	 * */
	public int ifExist(String phoneNumber){
		int result =userDao.ifExist(phoneNumber);
		return result;
	}
	
	
	/**  新用户注册  存入手机号  并随机赋予一串乱码为 NickName	
	 * 
	 *  ！！！！！！！！！！！！！！！！这个方法中  还需要为新用户在多张表中添加一些内容 比如  平均信誉度 钱包
	 * 
	 * 	@return 新注册用户的userid
	 * */
	public int Register(String phoneNumber){
		int result = -1;
		User user = new User();
		user.setPhoneNumber(phoneNumber);
		userDao.create(user);
		int userid = user.getId();
		//System.out.println("#####################"+userid);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userid);
		userInfoDao.create(userInfo); //给新用户建立新的 个人信息
		AverageStar averageStar = new AverageStar();
		float f = 5.0f;
		averageStar.setPTPublisher(f);
		averageStar.setPTWorker(f);
		averageStar.setRunpublisher(f);
		averageStar.setRunWorker(f);
		averageStar.setUserId(userid); 
		averageStarDao.create(averageStar);//给新用户建立新的 平均信誉度
		result = userid;
		return result;
	}
	
	
	/** 把token 更新存入user表
	 * */
	public int InsertToken(String userToken,int userid){
		int result = userDao.InsertToken(userToken, userid);
		return result;
		}
	
	/**  根据userid 取得对应用户的 Token
	 * */
	public String getToken(int userid){
		String token = userDao.getToken(userid);
		return token;
	}
	
	/** 身份证 真是姓名存入
	 * 
	 *  身份认证
	 *  @return 0失败 1成功
	 * */
	public int identify(String idNumber,String trueName,int userid){
		int result = userDao.identify(idNumber, trueName, userid);
		return result;	
	}

	/**绑定手机号码  提供给QQ用户
	 * */
	public int BindPhoneForQQ(String phoneNumber,String QQNumber){
		int result = userDao.BindPhoneForQQ(phoneNumber, QQNumber);
		return result;
	}
	
	/**绑定手机号码  提供给QQ用户
	 * */
	public int BindPhoneForWeChat(String phoneNumber,String WeChat){
		int result = userDao.BindPhoneForWeChat(phoneNumber, WeChat);
		return result;
	}
	
	
	
	/**
	 *  输入手机号码 获得 userid 和token
	 * */
	public Map<String, Object> tokenForLogin(String phoneNumber){
		Map<String, Object> result = userDao.tokenForLogin(phoneNumber);
		return result;
	}
	
	/** 通过qq号码 获得userid 和token
	 * */
	public Map<String,Object> tokenForQQ(String QQNumber){
		Map<String, Object> result = userDao.tokenForQQ(QQNumber);
		return result;
	}
	
	/** 通过微信号码 获得userid 和token
	 * */
	public Map<String,Object> tokenForWeChat(String WeChat){
		Map<String, Object> result = userDao.tokenForWeChat(WeChat);
		return result;
	}
	
	/** 新增个人资料
	 * 
	 * */
	public int InsertInfo(int UserId,String NickName, int gender, String age,String Occupation,String Education){
		int result = userDao.InsertInfo(UserId, NickName, gender, age, Occupation, Education);
		return result;
	}
	
	/** 更新个人资料 
	 *  性别和年龄是不可以修改的 
	 * */
	public int UpdateInfo(int UserId,String NickName,String Occupation,String Education){
		int result = userDao.UpdateInfo(UserId, NickName, Occupation, Education);
		return result;
	}
	
	/** 个人中心页面   
	 * 显示个人的信息
	 * */
	public List<UInfo> ShowInfo(int userid){
		List<UInfo> result = userDao.ShowInfo(userid);
		return result;
	}
	
	/** 取得真名
	 * */
	public String getTrueName(int UserId){
		String TrueName = userDao.getTrueName(UserId);
		return TrueName;
	}
	
	/** 取得头像名称
	 * */
	public String getImgName(int userid){
		String ImgName = userDao.getImgName(userid);
		return ImgName;
	}
	
	/** 取得手机号码
	 * */
	public String getPhone(int UserId){
		String Phone = userDao.getPhone(UserId);
		return Phone;
	}
	
	/** 显示个人的优惠券
	 * */
	public List<youhui> MyCoupon(int userid){
		List<youhui> result = userDao.MyCoupon(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** 账号与安全
	 * */
	public List<BindInfo> safty(int userid){
		List<BindInfo> result = new ArrayList<BindInfo>();
		BindInfo b = new BindInfo();
		b.setIdentifyNumber(userDao.getIdNumber(userid));
		b.setPhone(userDao.getPhone(userid));
		b.setQQNumber(userDao.getQQ(userid));
		b.setWeChat(userDao .getWeChat(userid));
		result.add(b);
		return result;
	}
	
	/** 给工作者发钱
	 * */
	public int AddBalance(List<Integer> userid,float pay){
		int result = userDao.AddBalance(userid, pay);
		return result;
	}
	/** 用户提现
	 * */
	public int DecreaseBalance(int userid, float pay){
		int result = userDao.DecreaseBalance(userid, pay);
		return result;
	}
	
	/** 用户设置支付密码
	 * */
	public int setPayPassword(String password,int userid){
		int result = -1;
		result = userDao.setPayPassword(password, userid);
		return result;
	}
	
	/**  支付密码是否输入正确
	 * */
	public boolean PassRight(String pspt,int userid){
		boolean i = userDao.PassRight(pspt, userid);
		return i;
	}
	
	/** 使用优惠券 改变其状态
	 * */
	public int UseCoupon(int id){
		int result = userDao.UseCoupon(id);
		return result;
	}

    public Map CheckIn(int id) {
		Map ss = userDao.CheckIn(id);
		return ss;
    }
}
