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
    private String checkNumber;//接收客户端发来的验证码
    private static String random;//四位数随机验证码
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
     * token值生成函数接口
     */
    public String CreateToken() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();//随机用以下三个随机生成器
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;//保证只会产生97~122之间的整数
                    sb.append((char) data);
                    break;
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * token值生成函数接口
     */
    public String getToken() {
        String tokenString = UserService.getToken(userid);
        return info(tokenString);
    }

    /**
     * 接受手机号码
     * 生成随机数 发送短信去 移动端  等待进一步的匹配
     * <p>
     * 该方法 在接收到用户手机号之后    对号码进行判断   如果是新号码 验证码发送至 register()
     * 如果是已存在号码    调用接口 login()
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
     * 比较参数 并存入数据库 完成注册的流程
     *
     * @return 一个map 包含新注册用户的 token值 和 userid
     */
    public String Register() {
        int uid = UserService.Register(emailNumber);
        if (uid != -1) {
            String token = this.CreateToken();
            //把token存入数据库
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
     * 比较参数 完成注册流程
     */
    public String Login() {
        if (checkNumber.equals(random)) {
            // 登陆成功 无数据库操作 进入主页面
            return info(Message.message[0]);
        } else {
            //提示错误  错误的check number
            return info(Message.message[1]);
        }
    }

    /**
     * 身份证 真是姓名存入
     * <p>
     * 身份认证
     *
     * @return 0失败 1成功
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
     * 绑定手机号码   提供给用QQ登陆的用户
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
     * 绑定手机号码   提供给用微信登陆的用户
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
     * 输入手机号码  获得 userid  和 token
     */
    public String tokenForLogin() {
        Map<String, Object> result = UserService.tokenForLogin(emailNumber);
        return info(result);
    }

    /**
     * 通过QQ号码 获得 userid 和token
     */
    public String tokenForQQ() {
        Map<String, Object> result = UserService.tokenForQQ(QQNumber);
        return info(result);
    }

    /**
     * 通过微信号码 获得 userid 和token
     */
    public String tokenForWeChat() {
        Map<String, Object> result = UserService.tokenForWeChat(WeChat);
        return info(result);
    }

    /**
     * 新增个人资料
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
     * 更新个人资料
     * 性别和年龄是不可以修改的
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
     * 个人中心页面
     * 显示个人的信息
     */
    public String ShowInfo() {
        List<UInfo> result = UserService.ShowInfo(userid);
        return info(result);
    }


    /**
     * 显示个人的优惠券信息
     */
    public String MyCoupon() {
        List<youhui> result = UserService.MyCoupon(userid);
        return info(result);
    }

    /**
     * 获取头像名称
     */
    public String getHeadImg() {
        String name = UserService.getImgName(userid);
        return info(name);
    }

    /**
     * 账号与安全
     */
    public String safty() {
        List<BindInfo> result = UserService.safty(userid);
        return info(result);
    }

    /**
     * 用户设置支付密码
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
     * 取得手机号码
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
