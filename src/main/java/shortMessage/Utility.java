package shortMessage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.StrutsStatics;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;


public class Utility {

	//ʹ��StringBuffer��append���xml��ʽ���ַ���
	StringBuffer sub=new StringBuffer();
	BufferedReader br=null;
	URL url=null;
	HttpURLConnection con;
	String line;
	
	public static void main(String[] args) {
		
		
		Utility u = new Utility();
		u.SendSMS("15866636885",3333);
//		//��ѯ���÷���
//		xml=t.SelSum("1111", "qqqq", "mima").toString();
//		//���xml��ʽ���ַ���
//		System.out.println("���xml"+xml);
//
//		//��ֵ��xmlEntityʵ����
//		xmlentity.setReturnstatus("returnstatus");
//		xmlentity.setMessage("message");
//		xmlentity.setPayinfo("payinfo");
//		xmlentity.setOverage("overage");
//		xmlentity.setSendTotal("sendTotal");
//		//����XML�ַ�������ͨ�÷���
//		xmlentity=t.readStringXmlCommen(xmlentity, xml);
//		System.out.println("����״̬Ϊ:"+xmlentity.getReturnstatus()+"\n"+"���ظ��ѷ�ʽ:"+xmlentity.getPayinfo());
//		
//	    int zong=Integer.parseInt(xmlentity.getSendTotal());
//	    int yong=Integer.parseInt(xmlentity.getOverage());
//	    int sheng=zong-yong;
//	    System.out.println("������Ϊ��"+zong);
//	    System.out.println("��ʹ�ã�"+yong);
//	    System.out.println("ʣ�ࣺ��"+sheng);
	    
	   
		
		//״̬����
//		xml=t.StatusReport("1111", "qqqq", "mima").toString();
//		System.out.println(xml);
//		xmlentity.setStatusbox("statusbox");
//		xmlentity.setMobile("mobile");
//		xmlentity.setTaskid("taskid");
//		xmlentity.setStatus("status");
//		xmlentity.setReceivetime("receivetime");
//		xmlentity.setErrorstatus("errorstatus");
//		xmlentity.setError("error");
//		xmlentity.setRemark("remark");
//		xmlentity=t.readStringXmlCommen(xmlentity, xml);
//		System.out.println("��Ӧ�ֻ��ţ�"+xmlentity.getMobile()+"��Ӧ״̬"+xmlentity.getStatus()+"��Ӧ����ʱ��"+xmlentity.getReceivetime());
//		System.out.println("������룺"+xmlentity.getError());
	    
	}

    public static void sendMail(String fromMail, String user, String password,
                                String toMail,
                                String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties(); //���Լ���һ�������ļ�
        // ʹ��smtp�����ʼ�����Э��
        props.put("mail.smtp.host", "smtp.sina.com");//�洢�����ʼ�����������Ϣ
        props.put("mail.smtp.auth", "true");//ͬʱͨ����֤

        Session session = Session.getInstance(props);//���������½�һ���ʼ��Ự

        MimeMessage message = new MimeMessage(session);//���ʼ��Ự�½�һ����Ϣ����
        message.setFrom(new InternetAddress(fromMail));//���÷����˵ĵ�ַ
        message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toMail));//�����ռ���,���������������ΪTO
        message.setSubject(mailTitle);//���ñ���

        message.setContent(mailContent, "text/html;charset=gbk"); //����HTML�ʼ���������ʽ�ȽϷḻ
        message.setSentDate(new Date());//���÷���ʱ��
        message.saveChanges();//�洢�ʼ���Ϣ

        //�����ʼ�
//        Transport transport = session.getTransport("smtp");
        Transport transport = session.getTransport("smtp");
        transport.connect(user, password);
        transport.sendMessage(message, message.getAllRecipients());//�����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
        transport.close();
    }


    /** ��ͨѶ���ŷ���
     * ����Ϊ�ʼ�����
	 * */
	public String SendMessages(String email) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i<5;i++){
            stringBuilder.append(String.valueOf(random.nextInt(10)));
        }
        String checkNumber = stringBuilder.toString();
        sendMail("forsshmail@sina.com", "forsshmail", "123456789",
                email,
                new String("ssh��վ��֤�ʼ�"),
                new String("������֤��Ϊ")+checkNumber);
		return checkNumber;
	}
	
	
	
	/**���ŷ��ͷ���
	 * */
	public String SendSMS(String phoneNumber,int random){
		xmlEntity xmlentity=new xmlEntity();
		String xml=null;
		Utility t=new Utility();
		String content ="������֤��"+random+"��������ܡ�";
		 //���͵���
		xml=t.SendMessage("", "AB00183", "AB001830", phoneNumber, content, "").toString();
        System.out.println(xml);
        xmlentity.setReturnstatus("returnstatus");
        xmlentity.setMessage("message");
        xmlentity.setRemainpoint("remainpoint");
        xmlentity.setTaskID("taskID");
        xmlentity.setSuccessCounts("successCounts");
        xmlentity=t.readStringXmlCommen(xmlentity, xml);
        System.out.println("״̬"+xmlentity.getReturnstatus()+"������Ϣ"+xmlentity.getMessage()+"�ɹ�����"+xmlentity.getSuccessCounts());
       	return xmlentity.getSuccessCounts();
	}
	
	//��ѯ���
	public StringBuffer SelSum(String userid,String account,String password) 
	{
		try {
			url=new URL("http://114.113.154.5/sms.aspx?action=overage&userid="+userid+"&account="+account+"&password="+password+"");	
			con = (HttpURLConnection)url.openConnection();
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		    br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		     
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ������XML��ʽ���ַ���
		    	sub.append(line+"");
		    	//System.out.println("��ȡ���� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	    
	}

	
	//���Ͷ���
	public StringBuffer SendMessage(String userid,String account,String password,String mobile,String content,String sendTime)
	{
		
		try {
			//���÷������ݵı��뷽ʽ
			String send_content=URLEncoder.encode(content.replaceAll("<br/>", " "), "UTF-8");//��������
			
			url=new URL("http://114.113.154.5/sms.aspx?action=send&userid="+userid+"&account="+account+"&password="+password+"&mobile="+mobile+"&content="+send_content+"&sendTime="+sendTime+"");	
			con = (HttpURLConnection)url.openConnection();
			
			br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		     
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ������XML��ʽ���ַ���
		    	sub.append(line+"");
		    	//System.out.println("��ȡ���� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	}

	//״̬����ӿ�
	public StringBuffer StatusReport(String userid,String account,String password )
	{
		try {
			url=new URL("http://114.113.154.5/statusApi.aspx?action=query&userid="+userid+"&account="+account+"&password="+password+"");	
			con = (HttpURLConnection)url.openConnection();
			
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		   
			br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		    
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ������XML��ʽ���ַ���
		    	sub.append(line+"");
		    //	System.out.println("��ȡ���� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	}
	
	
	//����xml�ַ���
	public void readStringXml(String xml)
	{
		Document doc=null;
		
		try {
			//���ַ�ת��ΪXML
			doc=DocumentHelper.parseText(xml);
			//��ȡ���ڵ�
			Element rootElt=doc.getRootElement();
			
			//�õ����ڵ������
			//System.out.println("���ڵ����ƣ�"+rootElt.getName());
			
			//��ȡ���ڵ��µ��ӽڵ��ֵ
			String returnstatus=rootElt.elementText("returnstatus").trim();
			String message=rootElt.elementText("message").trim();
			String payinfo=rootElt.elementText("payinfo").trim();
			String overage=rootElt.elementText("overage").trim();
			String sendTotal=rootElt.elementText("sendTotal").trim();
			
			System.out.println("����״̬Ϊ��"+returnstatus);
			System.out.println("������Ϣ��ʾ��"+message);
			System.out.println("����֧����ʽ��"+payinfo);
			System.out.println("����ʣ�����������"+overage);
			System.out.println("������������"+sendTotal);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//XML�ַ�������ͨ�÷���
	public xmlEntity readStringXmlCommen(xmlEntity xmlentity,String xml)
	{
		xmlEntity xe=new xmlEntity();
		
		Document doc=null;
		
		try {
			//���ַ�ת��ΪXML
			doc=DocumentHelper.parseText(xml);
			//��ȡ���ڵ�
			Element rootElt=doc.getRootElement();
			//�õ����ڵ������
			//System.out.println("���ڵ㣺" + rootElt.getName()); 
			
			//��ȡ���ڵ��µ��ӽڵ��ֵ
			if(xmlentity.getReturnstatus()!=null)
			{
				xe.setReturnstatus(rootElt.elementText(xmlentity.getReturnstatus()).trim());
			}
			if(xmlentity.getMessage()!=null)
			{
				xe.setMessage(rootElt.elementText(xmlentity.getMessage()).trim());
			}
			if(xmlentity.getRemainpoint()!=null)
			{
				xe.setRemainpoint(rootElt.elementText(xmlentity.getRemainpoint()).trim());
			}
			if(xmlentity.getTaskID()!=null)
			{
				xe.setTaskID(rootElt.elementText(xmlentity.getTaskID()).trim());
			}
			if(xmlentity.getSuccessCounts()!=null)
			{
				xe.setSuccessCounts(rootElt.elementText(xmlentity.getSuccessCounts()).trim());
			}
			if(xmlentity.getPayinfo()!=null)
			{
				xe.setPayinfo(rootElt.elementText(xmlentity.getPayinfo()).trim());
			}
			if(xmlentity.getOverage()!=null)
			{
				xe.setOverage(rootElt.elementText(xmlentity.getOverage()).trim());
			}
			if(xmlentity.getSendTotal()!=null)
			{
				xe.setSendTotal(rootElt.elementText(xmlentity.getSendTotal()).trim());
			}
			//����״̬���صı���
			if(rootElt.hasMixedContent()==false)
			{
				System.out.println("�޷���״̬��");
			}
			else
			{
				for (int i = 1; i <= rootElt.elements().size(); i++) {
					if(xmlentity.getStatusbox()!=null)
					{
						System.out.println("״̬"+i+":");
						//��ȡ���ڵ��µ��ӽڵ�statusbox
						 Iterator iter = rootElt.elementIterator(xmlentity.getStatusbox()); 
						// ����statusbox�ڵ� 
						 while(iter.hasNext())
						 {
							 Element recordEle = (Element) iter.next();
							 xe.setMobile(recordEle.elementText("mobile").trim());
							 xe.setTaskid(recordEle.elementText("taskid").trim());
							 xe.setStatus(recordEle.elementText("status").trim());
							 xe.setReceivetime(recordEle.elementText("receivetime").trim());
							 System.out.println("��Ӧ�ֻ��ţ�"+xe.getMobile());
							 System.out.println("ͬһ������ID��"+xe.getTaskid());
							 System.out.println("״̬����----10�����ͳɹ���20������ʧ�ܣ�"+xe.getStatus());
							 System.out.println("����ʱ�䣺"+xe.getReceivetime());
						 }	 
					 }
					
				}

			}
			
			//���󷵻صı���
			if(xmlentity.getErrorstatus()!=null)
			{
				//��ȡ���ڵ��µ��ӽڵ�errorstatus
				 Iterator itererr = rootElt.elementIterator(xmlentity.getErrorstatus()); 
				// ����errorstatus�ڵ�
	            while(itererr.hasNext())
	            {
	            	Element recordElerr = (Element) itererr.next();
	            	xe.setError(recordElerr.elementText("error").trim());
	            	xe.setRemark(recordElerr.elementText("remark").trim());
	            	System.out.println("������룺"+xe.getError());
	            	System.out.println("����������"+xe.getRemark());
	            }
			}
			
//			if(xmlentity.getCallbox()!=null)
//			{
//				//��ȡ���ڵ��µ��ӽڵ�errorstatus
//				Iterator itercallbox = rootElt.elementIterator("errorstatus"); 
//				// ����errorstatus�ڵ�
//				while(itercallbox.hasNext())
//				{
//					Element recordcallbox = (Element) itercallbox.next();
//					String content=recordcallbox.elementText("content").trim();
//					String receivetime=recordcallbox.elementText("receivetime").trim();
//					String mobile=recordcallbox.elementText("mobile").trim();
//					String taskid=recordcallbox.elementText("taskid").trim();
//					
//				}
//			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return xe;
	}
	
	
	
	
	
	
	
}
