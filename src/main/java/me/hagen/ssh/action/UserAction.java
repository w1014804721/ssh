package me.hagen.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import me.hagen.ssh.domain.User;
import me.hagen.ssh.dto.BindInfo;
import me.hagen.ssh.dto.youhui;
import me.hagen.ssh.dto.Message;
import me.hagen.ssh.dto.UInfo;
import me.hagen.ssh.service.UserService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import antlr.Token;
import shortMessage.Utility;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

@Controller("UserAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class UserAction extends AbstractAction {

    @Autowired
    private UserService UserService;
    /**
     *
     */
    private static final long serialVersionUID = -5443855637967501453L;
    private String emailNumber;
    private String checkNumber;//���տͻ��˷�������֤��
    private static String random;//��λ�������֤��
    private final int length = 16;
    private String idNumber;
    private String trueName;
    private int userid;
    private String Occupation;
    private String age;
    private int gender;
    private String Education;
    private String NickName;
    private String QQNumber;
    private String WeChat;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }
    public String CheckIn(){
        return info(UserService.CheckIn(userid));
    }

    /**
     * tokenֵ���ɺ����ӿ�
     */
    public String CreateToken() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();//����������������������
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            //Ŀ�������ѡ���������֣���Сд��ĸ
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//����������0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//��ֻ֤�����65~90֮�������
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;//��ֻ֤�����97~122֮�������
                    sb.append((char) data);
                    break;
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * tokenֵ���ɺ����ӿ�
     */
    public String getToken() {
        String tokenString = UserService.getToken(userid);
        return info(tokenString);
    }

    /**
     * �����ֻ�����
     * ��������� ���Ͷ���ȥ �ƶ���  �ȴ���һ����ƥ��
     * <p>
     * �÷��� �ڽ��յ��û��ֻ���֮��    �Ժ�������ж�   ������º��� ��֤�뷢���� register()
     * ������Ѵ��ں���    ���ýӿ� login()
     */
    public String SendMessages() throws Exception {
        Utility u = new Utility();
        String result = u.SendMessages(emailNumber);
        random = result;
        if (UserService.ifExist(emailNumber) == 0) {
            return info("Login " + result);
        }
        if (UserService.ifExist(emailNumber) == 1) {
            return info("Register " + result);
        } else {
            System.out.println(result);
            return info(Message.message[20]);
        }
    }

    /**
     * �Ƚϲ��� ���������ݿ� ���ע�������
     *
     * @return һ��map ������ע���û��� tokenֵ �� userid
     */
    public String Register() {
        int uid = UserService.Register(emailNumber);
        if (uid != -1) {
            String token = this.CreateToken();
            //��token�������ݿ�
            UserService.InsertToken(token, uid);
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("token", token);
//			resultMap.put("userid", uid);
//			return info(resultMap);

            Message message = new Message(uid, token);
            return info(message);
        } else {

            return info(Message.message[18]);
        }

    }

    /**
     * �Ƚϲ��� ���ע������
     */
    public String Login() {
        if (checkNumber.equals(random)) {
            // ��½�ɹ� �����ݿ���� ������ҳ��
            return info(Message.message[0]);
        } else {
            //��ʾ����  �����check number
            return info(Message.message[1]);
        }
    }

    /**
     * ���֤ ������������
     * <p>
     * �����֤
     *
     * @return 0ʧ�� 1�ɹ�
     */
    public String identify() {
        int result = UserService.identify(idNumber, trueName, userid);
        if (result == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }

    /**
     * ���ֻ�����   �ṩ����QQ��½���û�
     */
    public String BindPhoneForQQ() {
        int result = UserService.BindPhoneForQQ(emailNumber, QQNumber);
        if (result == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }


    /**
     * ���ֻ�����   �ṩ����΢�ŵ�½���û�
     */
    public String BindPhoneForWeChat() {
        int result = UserService.BindPhoneForWeChat(emailNumber, WeChat);
        if (result == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }


    /**
     * �����ֻ�����  ��� userid  �� token
     */
    public String tokenForLogin() {
        Map<String, Object> result = UserService.tokenForLogin(emailNumber);
        return info(result);
    }

    /**
     * ͨ��QQ���� ��� userid ��token
     */
    public String tokenForQQ() {
        Map<String, Object> result = UserService.tokenForQQ(QQNumber);
        return info(result);
    }

    /**
     * ͨ��΢�ź��� ��� userid ��token
     */
    public String tokenForWeChat() {
        Map<String, Object> result = UserService.tokenForWeChat(WeChat);
        return info(result);
    }

    /**
     * ������������
     */
    public String InsertInfo() {
        int result = UserService.InsertInfo(userid, NickName, gender, age, Occupation, Education);
        if (result == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }

    /**
     * ���¸�������
     * �Ա�������ǲ������޸ĵ�
     */
    public String UpdateInfo() {
        int result = UserService.UpdateInfo(userid, NickName, Occupation, Education);
        if (result == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }

    /**
     * ��������ҳ��
     * ��ʾ���˵���Ϣ
     */
    public String ShowInfo() {
        List<UInfo> result = UserService.ShowInfo(userid);
        return info(result);
    }


    /**
     * ��ʾ���˵��Ż�ȯ��Ϣ
     */
    public String MyCoupon() {
        List<youhui> result = UserService.MyCoupon(userid);
        return info(result);
    }

    /**
     * ��ȡͷ������
     */
    public String getHeadImg() {
        String name = UserService.getImgName(userid);
        return info(name);
    }

    /**
     * �˺��밲ȫ
     */
    public String safty() {
        List<BindInfo> result = UserService.safty(userid);
        return info(result);
    }

    /**
     * �û�����֧������
     */
    public String setPayPassword() {
        int reuslt = UserService.setPayPassword(password, userid);
        if (reuslt == 1) {
            return info(Message.message[0]);
        } else {
            return info(Message.message[18]);
        }
    }

    /**
     * ȡ���ֻ�����
     */
    public String getPhone() {
        Map<String, Object> result = new HashMap<String, Object>();
        String phone = UserService.getPhone(userid);
        String TrueName = UserService.getTrueName(userid);
        result.put("phone", phone);
        result.put("TrueName", TrueName);
        return info(result);
    }



    public String getPhoneNumber() {
        return emailNumber;
    }

    public void setPhoneNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getQQNumber() {
        return QQNumber;
    }

    public void setQQNumber(String qQNumber) {
        QQNumber = qQNumber;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String weChat) {
        WeChat = weChat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
