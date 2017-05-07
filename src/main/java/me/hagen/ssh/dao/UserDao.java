package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import me.hagen.ssh.domain.Coupon;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.domain.Runpicture;
import me.hagen.ssh.domain.User;
import me.hagen.ssh.domain.UserInfo;
import me.hagen.ssh.dto.BindInfo;
import me.hagen.ssh.dto.youhui;
import me.hagen.ssh.dto.UInfo;
@Repository("userDao")
public class UserDao extends BaseDao<User>{
	
	
		
	/** 判断手机号  确定是 登录还是注册  
	 *     0  存在
	 *     1  不存在
	 * */
	public int ifExist(String phoneNumber){
		int result = -1;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> brief = session.createQuery("select u.id from User u where u.phoneNumber=?")
		.setParameter(0, phoneNumber)
		.list();
		if (brief == null || brief.isEmpty()){
			result = 1;
		}else{
			result = 0;
		}
		System.out.println(result);
		return result;
	}

	
	/** 身份证 真是姓名存入
	 * 
	 *  身份认证
	 *  @return 0失败 1成功
	 * */
	public int identify(String idNumber,String trueName,int userid){
		int result = 0;
		Session session = this.currentSession();
		result = session.createSQLQuery("update user_info set identityNumber=?,TrueName = ? where UserId = ?")
				.setParameter(0, idNumber)
				.setParameter(1, trueName)
				.setParameter(2, userid)
				.executeUpdate();
		return result;
	}
	
	
	/**  用户QQ登陆后  绑定手机号
	 * */
	public int BindPhoneForQQ(String phoneNumber,String QQNumber){
		int result = 0;
		Session session = this.currentSession();
		result = session.createSQLQuery("update user set QQNumber=? where phoneNumber = ?")
				.setParameter(0, QQNumber)
				.setParameter(1, phoneNumber)
				.executeUpdate();
		System.out.println("*************"+result);
		return result;
	}
	
	/**  用户微信登陆后  绑定手机号
	 * */
	public int BindPhoneForWeChat(String phoneNumber,String Wechat){
		int result = 0;
		Session session = this.currentSession();
		result = session.createSQLQuery("update user set wcNumber=? where phoneNumber = ?")
				.setParameter(0, Wechat)
				.setParameter(1, phoneNumber)
				.executeUpdate();
		return result;
	}
	

	
	/** 把token 更新存入user表
	 * */
	public int InsertToken(String userToken,int userid){
		int result = -1;
		Session session = this.currentSession();
		result = session.createSQLQuery("update user set userToken=? where Id = ?")
				.setParameter(0, userToken)
				.setParameter(1, userid)
				.executeUpdate();
		return result;

	}
	
	/**  根据userid 取得对应用户的 Token
	 * */
	public String getToken(int userid){
		String resultString="";
		Session session = this.currentSession();
		String sqlString = "select * from user where Id ="+userid;
		@SuppressWarnings("unchecked")
		List<User> brief = session.createSQLQuery(sqlString)
				.addEntity(User.class).list();
		if (brief == null || brief.isEmpty())
			return null;
		else {
			 resultString = brief.get(0).getUserToken();
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!"+resultString);
		return resultString;
	}
	
	
	/**
	 *  输入手机号码  获得 userid  和 token
	 * */
	public  Map<String, Object> tokenForLogin(String phoneNumber){
		Map<String, Object> result = new HashMap<String, Object>();
		Session session = this.getSession();
		int userid =  (int) session
				.createQuery(
						"select u.id from User u where u.phoneNumber=?")
				.setParameter(0, phoneNumber).uniqueResult();
		
		String token = (String) session
				.createQuery(
						"select u.userToken from User u where u.phoneNumber=?")
				.setParameter(0, phoneNumber).uniqueResult();
		
		result.put("userid", userid);
		result.put("token", token);
		return result;
	}
	
	/** 通过qq号码 获得userid 和token
	 * */
	public Map<String,Object> tokenForQQ(String QQNumber){
		Map<String, Object> result = new HashMap<String, Object>();
		Session session = this.getSession();
		User u = (User) session
				.createQuery(
						"select u from User u where u.QQNumber=?")
				.setParameter(0, QQNumber).uniqueResult();
		int userid = 0;
		String token = "";
		if(u ==null){
			
		}else{
			userid = u.getId();
			token = u.getUserToken();
		}
		result.put("userid", userid);
		result.put("token", token);
		return result;
	}
	

	
	
	/** 通过微信号码 获得userid 和token
	 * */
	public Map<String,Object> tokenForWeChat(String WeChat){
		Map<String, Object> result = new HashMap<String, Object>();
		Session session = this.getSession();
		User u = (User) session
				.createQuery(
						"select u from User u where u.wcNumber=?")
				.setParameter(0, WeChat).uniqueResult();
		int userid = 0;
		String token = "";
		if(u ==null){
			
		}else{
			userid = u.getId();
			token = u.getUserToken();
		}
		result.put("userid", userid);
		result.put("token", token);
		return result;
	}
	
	
	
	
	/** 新增个人资料
	 * */
	public int InsertInfo(int UserId,String NickName, int gender, String age,String Occupation,String Education){
		Session session = this.getSession();
		String sql = "update user_info set NickName =?,gender=?,Age=?,Occupation=?,Education=? where UserId = ?";
		int result = session.createSQLQuery(sql)
				.setParameter(0, NickName)
				.setParameter(1, gender)
				.setParameter(2, age)
				.setParameter(3, Occupation)
				.setParameter(4, Education)
				.setParameter(5, UserId)
				.executeUpdate();
		return result;
	}
	
	/** 填写或者发布头像
	 * 
	 * 通过单元测试
	 * */


	public int UpdateHeadImg(int userid,String headImg){
		Session session = this.getSession();
		String sql = "update user_info set HeadImage =? where UserId = ?";
		int result = session.createSQLQuery(sql)
				.setParameter(0, headImg)
				.setParameter(1, userid)
				.executeUpdate();
		return result;
	} 
	
	/** 根据userid  去得到所属的图片的名字
	 * 把名字放在一个String的list里面返回
	 * 通过单元测试
	 * */
	public String getImgName(int userid){
		String HeadImage;
		Session session = this.currentSession();
		
		HeadImage =  (String) session
				.createQuery(
						"select a.HeadImage from UserInfo a where a.UserId=?")
				.setParameter(0, userid).uniqueResult();
		System.out.println(HeadImage);
		return HeadImage;
	}
	
	
	
	
	
	
	
	
	/** 更新个人资料 
	 *  性别和年龄是不可以修改的 
	 * */
	public int UpdateInfo(int UserId,String NickName,String Occupation,String Education){
		Session session = this.getSession();
		String sql = "update user_info set NickName =?,Occupation=?,Education=? where UserId = ?";
		int result = session.createSQLQuery(sql)
				.setParameter(0, NickName)
				.setParameter(1, Occupation)
				.setParameter(2, Education)
				.setParameter(3, UserId)
				.executeUpdate();
		return result;
	}
	
	/** 显示个人的信息
	 * */
	public List<UInfo> ShowInfo(int userid){
		List<UInfo> result = new ArrayList<UInfo>();
		String sql = "select * from user_info where UserId ="+userid;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<UserInfo> brief = session.createSQLQuery(sql)
				.addEntity(UserInfo.class).list();
		if (brief == null || brief.isEmpty())
			return null;
		else {
			UInfo ui = new UInfo();
			ui.setAge(brief.get(0).getAge());
			ui.setEducation(brief.get(0).getEducation());
			ui.setGender(brief.get(0).getGender());
			ui.setNickName(brief.get(0).getNickName());
			ui.setOccupation(brief.get(0).getOccupation());
			result.add(ui);
		}
		return result;
	}
	
	/** 取得真名
	 * */
	public String getTrueName(int UserId){
		String TrueName;
		Session session = this.currentSession();
		
		TrueName =  (String) session
				.createQuery(
						"select a.TrueName from UserInfo a where a.UserId=?")
				.setParameter(0, UserId).uniqueResult();
		System.out.println(TrueName);
		return TrueName;
	}
	
	

	/** 取得余额
	 * */
	public float getBalance(int UserId){
		float Balance;
		Session session = this.currentSession();
		
		Balance =    ((Number) session
				.createQuery(
						"select a.Balance from UserInfo a where a.UserId=?")
				.setParameter(0, UserId).uniqueResult()).floatValue();
		return Balance;
	}
	
	/** 取得手机号码
	 * */
	public String getPhone(int UserId){
		String Phone;
		Session session = this.currentSession();
		
		Phone =  (String) session
				.createQuery(
						"select u.phoneNumber from User u where u.id=?")
				.setParameter(0, UserId).uniqueResult();
		return Phone;
	}
	
	/** 取得身份证号
	 * */
	public String getIdNumber(int UserId){
		String IdNumber;
		Session session = this.currentSession();
		
		IdNumber =  (String) session
				.createQuery(
						"select u.IdentityNumber from UserInfo u where u.UserId=?")
				.setParameter(0, UserId).uniqueResult();
		return IdNumber;
	}
	
	/** 获得 qq号
	 * */
	public String getQQ(int UserId){
		String QQ;
		Session session = this.currentSession();
		
		QQ =  (String) session
				.createQuery(
						"select u.QQNumber from User u where u.id=?")
				.setParameter(0, UserId).uniqueResult();
		return QQ;
	}
	
	/** 获得 Wechat号
	 * */
	public String getWeChat(int UserId){
		String WeChat;
		Session session = this.currentSession();
		
		WeChat =  (String) session
				.createQuery(
						"select u.wcNumber from User u where u.id=?")
				.setParameter(0, UserId).uniqueResult();
		return WeChat;
	}
	/** 显示个人的优惠券
	 * */
	public List<youhui> MyCoupon(int userid){
	List<youhui> result = new ArrayList<youhui>();
	String sql="select c from Coupon c where c.UserId=?";
	Session session = this.currentSession();
	@SuppressWarnings("unchecked")
	List<Coupon> brief = session.createQuery(sql).setParameter(0, userid).list();
	
	if(brief == null || brief.isEmpty()){
		return null;
	}else{
		for(Coupon c: brief){
			youhui y = new youhui();
			y.setId(c.getId());
			y.setDecreased(c.getDecreased());
			y.setFinishedTime(c.getFinishedTime().toString());
			y.setLimit(c.getLimit());
			y.setType(c.getType());
			y.setStatus(c.getStatus());
			result .add(y);
		}
	}
	return result;
	}
	
	
	
	/** 使用优惠券 改变其状态
	 * */
	public int UseCoupon(int id){
		int result = -1;
		String hql = "update Coupon c set c.Status = 1 where c.id=?";
		Session session = this.currentSession();
		result = session.createQuery(hql).setParameter(0, id).executeUpdate();
		System.out.println("**************"+result);
		return result;
	}
	
	
	/**  统计优惠券张数
	 * */
	public int CountCoupon(int userid){
		String sql = "select count(*) as number from Coupon C where C.UserId = ?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		int result =  ((Number) session.createQuery(sql).setParameter(0, userid).iterate().next()).intValue();
		return result;
	}
	
	/** 获取用户名昵称
	 * */
	public String getNickName(int UserId){
		String WeChat;
		Session session = this.currentSession();
		
		WeChat =  (String) session
				.createQuery(
						"select u.NickName from UserInfo u where u.UserId=?")
				.setParameter(0, UserId).uniqueResult();
		return WeChat;
	}
	
	/** 付款 批量 更新用户钱包的余额
	 * 
	 *  通过Junit 单元测试
	 * */
	public int AddBalance(List<Integer> userid,float pay){
		int result = -1;
		String hql = "update UserInfo ui set ui.Balance = ui.Balance+"+pay+" where ui.UserId in (:idlist)";
		Session session = this.currentSession();
		result = session.createQuery(hql).setParameterList("idlist", userid).executeUpdate();
		System.out.println("**************"+result);
		return result;
	}
	
	
	/** 用户提现
	 * */
	public int DecreaseBalance(int userid, float pay){
		int result = -1;
		 String hql = "update UserInfo ui set ui.Balance = ui.Balance-"+pay+" where ui.UserId=?";
		 Session session = this.currentSession();
		 result = session.createQuery(hql).setParameter(0, userid).executeUpdate();
		 return result;
	}
	
	/** 用户设置支付密码  更新密码
	 * */
	public int setPayPassword(String password,int userid){
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALitqmAhbEtR1IKVO31sGsc7ng"
				+ "nNcXt7t4BSdP6f/Fd2CDF8OM1R/rNa5rjE0rkUuVeu+EGZsrHspLONeXzCR8XIPSTGzJesengJlYNfKaaK"
				+ "hqyuoo2o2JwI57JawXN6miUQI9cBzy8smT7aGeQk//qAxpYacqHihQ46TCY6"
				+ "kbmDAgMBAAECgYBucKyqwFIa2NzVGplw7+"
				+ "0zqBtikVGEHwAl5SnB+7rUMunDbh4p6xb9+yZu6IV72UDdhu0/jcKnVHVKSxiT2zs/r9TttdMpLzR7z"
				+ "/eKSQXp0tCKPs5MvM3vY5YARyXrtAda9WIJTuQBLz3TuDxvlFbxHLm8jktcWNpAzXlrTMgcAQJBAN6yf1RaHFaa6Sv/meVbexm5w"
				+ "CpjHW+lhJtaGUAfI+igQKUrpddL8jg765ufxnhMofWHf+zkpkM86fbHeNcGi0ECQQDUS7HC6kIMQqrLE"
				+ "LKn8hpZKmBEVe/3z3cLsgwVu9hcz/AKNjpbY598wg33QG1OmRP17wXSXoSBFlTPmdXzbufDAkEAuyrYW2PU13EhMiq"
				+ "EBMGLR8bXXIirD8/UEOF7wWmqu3QcgPWbT8Tz0hGCZuy1WRxHLt3wRYCywGYs2Y0y4b4NQQJAUObzvDLnrq"
				+ "JtUetwGf5yOg6jFwek5kwisstMPbATOPNpJ4sY3CDv82xERDhCL5S4fZ10G4I3frYS5fZbCWT"
				+ "3XwJBAK9agjx5/kYH5CiAUkTUYW2a8iHZUg3PUWZNtNqqvxptnWQU2BaYSvv92tjgOMn+Ri2E"
				+ "OlolC2BQnJdMBcFPocY=";
		String text = alipayUtility.MD5.sign(password, privateKey,"utf-8");
		Session session = this.getSession();
		String sql = "update user_info set PayPassword=? where UserId = ?";
		int result = session.createSQLQuery(sql)
				.setParameter(0, text)
				.setParameter(1, userid)
				.executeUpdate();
		return result;
	}
	
	/**  是否设置了支付密码
	 * */
	public String PassExit(int userid){
		String pspt;
		Session session = this.currentSession();
		
		pspt =  (String) session
				.createQuery(
						"select u.PayPassword from UserInfo u where u.UserId=?")
				.setParameter(0, userid).uniqueResult();
		System.out.println(pspt);
		return pspt;
	}
	
	/** 判断密码是否正确
	 * */
	public boolean PassRight(String pspt,int userid){
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALitqmAhbEtR1IKVO31sGsc7ng"
				+ "nNcXt7t4BSdP6f/Fd2CDF8OM1R/rNa5rjE0rkUuVeu+EGZsrHspLONeXzCR8XIPSTGzJesengJlYNfKaaK"
				+ "hqyuoo2o2JwI57JawXN6miUQI9cBzy8smT7aGeQk//qAxpYacqHihQ46TCY6"
				+ "kbmDAgMBAAECgYBucKyqwFIa2NzVGplw7+"
				+ "0zqBtikVGEHwAl5SnB+7rUMunDbh4p6xb9+yZu6IV72UDdhu0/jcKnVHVKSxiT2zs/r9TttdMpLzR7z"
				+ "/eKSQXp0tCKPs5MvM3vY5YARyXrtAda9WIJTuQBLz3TuDxvlFbxHLm8jktcWNpAzXlrTMgcAQJBAN6yf1RaHFaa6Sv/meVbexm5w"
				+ "CpjHW+lhJtaGUAfI+igQKUrpddL8jg765ufxnhMofWHf+zkpkM86fbHeNcGi0ECQQDUS7HC6kIMQqrLE"
				+ "LKn8hpZKmBEVe/3z3cLsgwVu9hcz/AKNjpbY598wg33QG1OmRP17wXSXoSBFlTPmdXzbufDAkEAuyrYW2PU13EhMiq"
				+ "EBMGLR8bXXIirD8/UEOF7wWmqu3QcgPWbT8Tz0hGCZuy1WRxHLt3wRYCywGYs2Y0y4b4NQQJAUObzvDLnrq"
				+ "JtUetwGf5yOg6jFwek5kwisstMPbATOPNpJ4sY3CDv82xERDhCL5S4fZ10G4I3frYS5fZbCWT"
				+ "3XwJBAK9agjx5/kYH5CiAUkTUYW2a8iHZUg3PUWZNtNqqvxptnWQU2BaYSvv92tjgOMn+Ri2E"
				+ "OlolC2BQnJdMBcFPocY=";
		String text = alipayUtility.MD5.sign(pspt, privateKey,"utf-8");
		String password;
		Session session = this.currentSession();
		
		password =  (String) session
				.createQuery(
						"select u.PayPassword from UserInfo u where u.UserId=?")
				.setParameter(0, userid).uniqueResult();
		if(password.equals(text)){
			System.out.println("*******");
			return true;
		}else{
			System.out.println("?????");
			return false;
		}
	}


    public Map CheckIn(int UserId) {
		Session session = this.getSession();
		String sql = "update user_info set checkin=checkin+1 where UserId = "+UserId;
		int s =  session.createSQLQuery("update user_info u set u.checkin=u.checkin+1 where u.UserId = ?")
				.setParameter(0,UserId)
				.executeUpdate();
		Integer pspt = (Integer) session.createQuery("SELECT u.checkin FROM UserInfo u where u.UserId=?").setParameter(0, UserId).uniqueResult();
		Map map = new HashMap();
		map.put("success",pspt);
		return map;
    }
}
