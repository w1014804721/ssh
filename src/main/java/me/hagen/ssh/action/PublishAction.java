package me.hagen.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import me.hagen.ssh.dto.Message;
import me.hagen.ssh.service.PartTimeCommentService;
import me.hagen.ssh.service.PartTimeOrderService;
import me.hagen.ssh.service.PictureService;
import me.hagen.ssh.service.RunCommentService;
import me.hagen.ssh.service.RunoffOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("PublishAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class PublishAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -31684102558128376L;
	/**
	 * ���Action ��ԭ����ģ���з������ ר�Ŵ����ְ��Ϣ��������Ϣ�ķ��� ԭ���� �����ڶ�
	 * */
	@Autowired
	PartTimeOrderService ptoService;
	@Autowired
	RunoffOrderService RunoffOrderService;
	@Autowired
	PictureService piecureService;
	@Autowired
	PartTimeCommentService partTimeCommentService;
	@Autowired
	RunCommentService runCommentService;
	
	private List<File> file;//  ͼƬ�ϴ���ص���������
    private List<String> fileFileName;
    private List<String> fileContentType;
	private int RunId;
	private int UserId;
	private String Name; //����
	private String Detail;//��ϸ��Ϣ
	private String Pay;//��ְ����
	private String Destination; //�����ʹ��Ŀ�ĵ�
	private String ReleaseTime; //����ʱ��
	private String FinishTime;// ���Ƚ���ʱ��
	private String EndInTime; //��������ʱ��
	private float Longitude;//����
	private float Latitude;//ά��
	private int Status;//״̬ Ĭ��Ϊ0
	
	private String StartTime;//������ʼʱ��
	private String CutoffTime;//����ʱ��
	private int PublisherId;
	
	private int Gender;//�Ա� ����0Ů1��
	private String Location;//�ص�����
	private String Age;//����
	private String Education;//ѧ��
	private String Occupation;//ְҵ
	private int RequiredNumber;//��Ҫ����
	private int CurrentNumber;//�������� ����ʱΪ0
	private String Cid;//С����
	private String SafeMoney;//���ս��
	private String content; //���۵���ϸ����
	private int workId;
	private int star; // �����е�����
	private int Type;//����0 ����1    ���� 0�Ƿ����� 1�ǹ�����
	private float AverageStar;
	private Message[] m = Message.message;

	/**
	 * �����µĶ���  
	 * */
	public String PublishPartTimeOrder() {
		
		if(UserId==0|| Name==null|| Detail==null|| Pay==null|| Location==null||
				Longitude==0|| Latitude==0|| ReleaseTime==null|| StartTime==null|| CutoffTime==null||EndInTime ==null||
				 Age==null|| Education==null|| Occupation==null||Cid==null||SafeMoney==null){
			
			System.out.println(UserId);
			System.out.println(Name);
			System.out.println(Detail);
			System.out.println(Pay);
			System.out.println(Location);
			System.out.println(Longitude);
			System.out.println(Latitude);
			System.out.println(ReleaseTime);
			System.out.println(StartTime);
			System.out.println(CutoffTime);
			System.out.println(EndInTime);
			System.out.println(Gender);
			System.out.println(Age);
			System.out.println(Education);
			System.out.println(Occupation);
			System.out.println(RequiredNumber);
			System.out.println(CurrentNumber);
			System.out.println(Cid);
			System.out.println(SafeMoney);
			System.out.println(Type);
			
			
			return info(Message.message[19]);
		}
	
		
		Timestamp ReleaseTime1 = Timestamp.valueOf(ReleaseTime);
		Timestamp StartTime1 = Timestamp.valueOf(StartTime);
		Timestamp CutoffTime1 = Timestamp.valueOf(CutoffTime);
		Timestamp EndInTime1 = Timestamp.valueOf(EndInTime);
		
		int PartTimeId = ptoService.publishPTOrder(UserId, Name, Detail, Pay, Location,Longitude, Latitude, ReleaseTime1, StartTime1, CutoffTime1,EndInTime1,Gender, Age, Education, Occupation, RequiredNumber, CurrentNumber, Cid, SafeMoney, Status, Type);

		if (PartTimeId > 0&&file!=null){
			//����ɹ� �������֮�����ͼƬ�Ĺ���
			this.upload(UserId);  //ͼƬ���������
			int i = fileFileName.size();
			int index = 0;
			for(;i>0;i--){
				
				String imgURL = "F:\\img\\"+UserId+"\\"+fileFileName.get(index);
				piecureService.UploadPartTimePicutre(imgURL, PartTimeId,fileFileName.get(index));
				index++;
			}
			System.out.println("&&&&&&&&&&&&&&&success");
			return info(Message.message[0]);
			}
		if (PartTimeId > 0&&file==null){
			return info("you have publish without img");
		}
		else{
			//����ʧ�� ͼƬҲ���ò�����
			System.out.println("############fail");
			return info(Message.message[18]);
			}
	}
	
	
	
	
	/**
	 * �����µ�����  
	 * */
	public String PublishRunoffOrder() {
		System.out.println("????????????????????????????????");
		if(UserId==0|| Name==null|| Detail==null|| Pay==null|| Destination==null||
				Longitude==0|| Latitude==0|| ReleaseTime==null|| FinishTime==null){
			System.out.println(Name);
			return info(Message.message[19]);
			
		}
		
		Timestamp ReleaseTime1 = Timestamp.valueOf(ReleaseTime);
		Timestamp FinishTime1 = Timestamp.valueOf(FinishTime);
		
		int RunOrderId =  RunoffOrderService.publicRORDER(UserId, Name, Detail, Pay, Longitude, Latitude, ReleaseTime1, Destination, FinishTime1, Status); 
		if (RunOrderId >0&&file!=null){
			//����ɹ� �������֮�����ͼƬ�Ĺ���
			this.upload(UserId);  //ͼƬ���������
			int i = fileFileName.size();
			int index = 0;
			
			for(;i>0;i--){
				piecureService.UploadRunPicture(fileFileName.get(index), RunOrderId); //�������ݿ�
				index++;
			}
		
			System.out.println("&&&&&&&&&&&&&&&success");
			return info(Message.message[0]);
		}
		if (RunOrderId > 0&&file==null){
			return info("you have publish without img");
		}
		else{
			System.out.println("############fail");
			return info(Message.message[18]);
			}
		
	}
	
	/**  �����û�ͷ��
	 * */
	public String HeadImg(){
	
		this.upload(UserId);  //ͼƬ���������
		
		int result = piecureService.UploadHeadImg(fileFileName.get(0), UserId);
		if (result ==1){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/** �����µ� ��ְ ����
	 * */
	public String PublishWorkComment(){
		if(UserId==0||workId==0){
			System.out.println(UserId);
			System.out.println(workId);
			System.out.println(Type);
			return info(Message.message[19]);
		}
		int WorkCommentId = partTimeCommentService.pubishNewComment(content, workId, UserId, Type, star,PublisherId);
		
		if(WorkCommentId > 0&&file!=null){
			
			this.upload(PublisherId);  //ͼƬ���������
			int i = fileFileName.size();
			int index = 0;
			for(;i>0;i--){
				String imgURL = "F:\\img\\"+UserId+"\\"+fileFileName.get(index);
				piecureService.UploadWorkCommentPicutre(imgURL, WorkCommentId, fileFileName.get(index));
			}
			System.out.println("&&&&&&&&&&&&&&&success");
			return info(Message.message[0]);
			
		}if (WorkCommentId > 0&&file==null){
			return info("you have publish without img");
		}
		else {
			//����ʧ�� ͼƬҲ���ò�����
			System.out.println("############fail");
			return info(Message.message[18]);
		}
	} 
	
	
	
	/** �����µ���������  ���Է���ͼƬ
	 **/
	public String PublishRunComment(){
		if (RunId==0||UserId==0) {
			System.out.println(UserId);
			System.out.println(RunId);
			return info(Message.message[19]);
		}
		int RunCommentId = runCommentService.pubishNewComment(content, RunId, UserId, Type, star,PublisherId);
		if (RunCommentId > 0&&file!=null) {
			this.upload(PublisherId);  //ͼƬ���������
			
			int i = fileFileName.size();
			int index = 0;
			for(;i>0;i--){
				piecureService.UploadRunCommentPicture(fileFileName.get(index), RunCommentId);
				index++;
			}
			System.out.println("&&&&&&&&&&&&&&&success");
			return info(Message.message[0]);
			
		}if (RunCommentId > 0&&file==null){
			return info("you have publish without img");
		}
		else {
			//����ʧ�� ͼƬҲ���ò�����
			System.out.println("############fail");
			return info(Message.message[18]);
		}
	}
	
	 /**
     * �ϴ�ͼƬ�ķ���  �����������е�������  -1ʧ��  1�ɹ�
     *  */ 
	
	  public String upload(int id) {
      	try{
      		
      		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
      		System.out.println(fileFileName.get(0));
      		System.out.println(fileContentType.get(0));
      /**
       * �õ����̱���ͼƬ��·��
       * root ��Ĭ��ͼƬ·��+�û�id���ļ���
       *  */ 
//          String root = "F:\\img"+"\\"+userid;  //ת���ַ�
//      		File dir = new File(servletRequest.getRealPath("")+"/Img/"+userid+"/");
//  			if(!dir.exists())dir.mkdirs();
      	
      		 String root = servletRequest.getRealPath("")+"/Img/"+id;
      		System.out.println(root);
          System.out.println(file.size());
          
          //Ϊÿ���û�����ר���ļ���
          File f =new File(root);    
          if  (!f.exists()  && !f.isDirectory())      
          {       
              System.out.println("//������");  
              f.mkdirs();    
              System.out.println("�Ѿ�����");  
          } else   
          {  
              System.out.println("//Ŀ¼����");  
          }
          
      
          //ѭ���ϴ����ļ�
          for(int i = 0 ; i < file.size() ; i ++){
          	 //System.out.println(file.size());
              InputStream is = new FileInputStream(file.get(i));
              
              //�õ�ͼƬ�����λ��(����root���õ�ͼƬ�����·����tomcat�µĸù�����)
              File destFile = new File(root,this.getFileFileName().get(i));
              
              //��ͼƬд�뵽�������õ�·����
              OutputStream os = new FileOutputStream(destFile);
              byte[] buffer = new byte[1024];
              int length  = 0 ;
              while((length = is.read(buffer))>0){
                  os.write(buffer, 0, length);
              }
            
              is.close();
              os.close();
          }
      	}catch(Exception e){
      		e.printStackTrace();
      	}
          return SUCCESS;
      }
      
      
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Message[] getM() {
		return m;
	}

	public void setM(Message[] m) {
		this.m = m;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getAverageStar() {
		return AverageStar;
	}

	public void setAverageStar(float averageStar) {
		AverageStar = averageStar;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getPay() {
		return Pay;
	}

	public void setPay(String pay) {
		Pay = pay;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public float getLongitude() {
		return Longitude;
	}

	public void setLongitude(float longitude) {
		Longitude = longitude;
	}

	public String getFinishTime() {
		return FinishTime;
	}


	public String getEndInTime() {
		return EndInTime;
	}

	public void setEndInTime(String endInTime) {
		EndInTime = endInTime;
	}

	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}


	public float getLatitude() {
		return Latitude;
	}

	public void setLatitude(float latitude) {
		Latitude = latitude;
	}

	public String getReleaseTime() {
		return ReleaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		ReleaseTime = releaseTime;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getCutoffTime() {
		return CutoffTime;
	}

	public void setCutoffTime(String cutoffTime) {
		CutoffTime = cutoffTime;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}



	public int getRequiredNumber() {
		return RequiredNumber;
	}




	public void setRequiredNumber(int requiredNumber) {
		RequiredNumber = requiredNumber;
	}




	public int getCurrentNumber() {
		return CurrentNumber;
	}




	public void setCurrentNumber(int currentNumber) {
		CurrentNumber = currentNumber;
	}




	public String getCid() {
		return Cid;
	}

	public void setCid(String cid) {
		Cid = cid;
	}

	public String getSafeMoney() {
		return SafeMoney;
	}

	public void setSafeMoney(String safeMoney) {
		SafeMoney = safeMoney;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public int getRunId() {
		return RunId;
	}

	public void setRunId(int runId) {
		RunId = runId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getPublisherId() {
		return PublisherId;
	}
	public void setPublisherId(int publisherId) {
		PublisherId = publisherId;
	}
}
