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
	 * �˷������ڸ����û��ĸ������� ͨ����Ԫ����
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
	 * �ж� ��¼ ����ע��
	 * */
	public int ifExist(String phoneNumber){
		int result =userDao.ifExist(phoneNumber);
		return result;
	}
	
	
	/**  ���û�ע��  �����ֻ���  ���������һ������Ϊ NickName	
	 * 
	 *  �����������������������������������������  ����ҪΪ���û��ڶ��ű������һЩ���� ����  ƽ�������� Ǯ��
	 * 
	 * 	@return ��ע���û���userid
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
		userInfoDao.create(userInfo); //�����û������µ� ������Ϣ
		AverageStar averageStar = new AverageStar();
		float f = 5.0f;
		averageStar.setPTPublisher(f);
		averageStar.setPTWorker(f);
		averageStar.setRunpublisher(f);
		averageStar.setRunWorker(f);
		averageStar.setUserId(userid); 
		averageStarDao.create(averageStar);//�����û������µ� ƽ��������
		result = userid;
		return result;
	}
	
	
	/** ��token ���´���user��
	 * */
	public int InsertToken(String userToken,int userid){
		int result = userDao.InsertToken(userToken, userid);
		return result;
		}
	
	/**  ����userid ȡ�ö�Ӧ�û��� Token
	 * */
	public String getToken(int userid){
		String token = userDao.getToken(userid);
		return token;
	}
	
	/** ���֤ ������������
	 * 
	 *  �����֤
	 *  @return 0ʧ�� 1�ɹ�
	 * */
	public int identify(String idNumber,String trueName,int userid){
		int result = userDao.identify(idNumber, trueName, userid);
		return result;	
	}

	/**���ֻ�����  �ṩ��QQ�û�
	 * */
	public int BindPhoneForQQ(String phoneNumber,String QQNumber){
		int result = userDao.BindPhoneForQQ(phoneNumber, QQNumber);
		return result;
	}
	
	/**���ֻ�����  �ṩ��QQ�û�
	 * */
	public int BindPhoneForWeChat(String phoneNumber,String WeChat){
		int result = userDao.BindPhoneForWeChat(phoneNumber, WeChat);
		return result;
	}
	
	
	
	/**
	 *  �����ֻ����� ��� userid ��token
	 * */
	public Map<String, Object> tokenForLogin(String phoneNumber){
		Map<String, Object> result = userDao.tokenForLogin(phoneNumber);
		return result;
	}
	
	/** ͨ��qq���� ���userid ��token
	 * */
	public Map<String,Object> tokenForQQ(String QQNumber){
		Map<String, Object> result = userDao.tokenForQQ(QQNumber);
		return result;
	}
	
	/** ͨ��΢�ź��� ���userid ��token
	 * */
	public Map<String,Object> tokenForWeChat(String WeChat){
		Map<String, Object> result = userDao.tokenForWeChat(WeChat);
		return result;
	}
	
	/** ������������
	 * 
	 * */
	public int InsertInfo(int UserId,String NickName, int gender, String age,String Occupation,String Education){
		int result = userDao.InsertInfo(UserId, NickName, gender, age, Occupation, Education);
		return result;
	}
	
	/** ���¸������� 
	 *  �Ա�������ǲ������޸ĵ� 
	 * */
	public int UpdateInfo(int UserId,String NickName,String Occupation,String Education){
		int result = userDao.UpdateInfo(UserId, NickName, Occupation, Education);
		return result;
	}
	
	/** ��������ҳ��   
	 * ��ʾ���˵���Ϣ
	 * */
	public List<UInfo> ShowInfo(int userid){
		List<UInfo> result = userDao.ShowInfo(userid);
		return result;
	}
	
	/** ȡ������
	 * */
	public String getTrueName(int UserId){
		String TrueName = userDao.getTrueName(UserId);
		return TrueName;
	}
	
	/** ȡ��ͷ������
	 * */
	public String getImgName(int userid){
		String ImgName = userDao.getImgName(userid);
		return ImgName;
	}
	
	/** ȡ���ֻ�����
	 * */
	public String getPhone(int UserId){
		String Phone = userDao.getPhone(UserId);
		return Phone;
	}
	
	/** ��ʾ���˵��Ż�ȯ
	 * */
	public List<youhui> MyCoupon(int userid){
		List<youhui> result = userDao.MyCoupon(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** �˺��밲ȫ
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
	
	/** �������߷�Ǯ
	 * */
	public int AddBalance(List<Integer> userid,float pay){
		int result = userDao.AddBalance(userid, pay);
		return result;
	}
	/** �û�����
	 * */
	public int DecreaseBalance(int userid, float pay){
		int result = userDao.DecreaseBalance(userid, pay);
		return result;
	}
	
	/** �û�����֧������
	 * */
	public int setPayPassword(String password,int userid){
		int result = -1;
		result = userDao.setPayPassword(password, userid);
		return result;
	}
	
	/**  ֧�������Ƿ�������ȷ
	 * */
	public boolean PassRight(String pspt,int userid){
		boolean i = userDao.PassRight(pspt, userid);
		return i;
	}
	
	/** ʹ���Ż�ȯ �ı���״̬
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
