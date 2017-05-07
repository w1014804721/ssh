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
	 * 这个Action 从原本的模块中分离出来 专门处理兼职信息和跑腿信息的发布 原因是 参数众多
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
	
	private List<File> file;//  图片上传相关的三个参数
    private List<String> fileFileName;
    private List<String> fileContentType;
	private int RunId;
	private int UserId;
	private String Name; //名称
	private String Detail;//详细信息
	private String Pay;//兼职报酬
	private String Destination; //跑腿送达的目的地
	private String ReleaseTime; //发布时间
	private String FinishTime;// 跑腿结束时间
	private String EndInTime; //报名截至时间
	private float Longitude;//经度
	private float Latitude;//维度
	private int Status;//状态 默认为0
	
	private String StartTime;//工作开始时间
	private String CutoffTime;//结束时间
	private int PublisherId;
	
	private int Gender;//性别 （男0女1）
	private String Location;//地点名称
	private String Age;//年龄
	private String Education;//学历
	private String Occupation;//职业
	private int RequiredNumber;//需要人数
	private int CurrentNumber;//现有人数 发布时为0
	private String Cid;//小分类
	private String SafeMoney;//保险金额
	private String content; //评论的详细内容
	private int workId;
	private int star; // 评论中的评分
	private int Type;//竞标0 抢单1    或者 0是发布者 1是工作者
	private float AverageStar;
	private Message[] m = Message.message;

	/**
	 * 发布新的订单  
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
			//插入成功 在这个分之内完成图片的工作
			this.upload(UserId);  //图片传入服务器
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
			//插入失败 图片也不用操作了
			System.out.println("############fail");
			return info(Message.message[18]);
			}
	}
	
	
	
	
	/**
	 * 发布新的跑腿  
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
			//插入成功 在这个分之内完成图片的工作
			this.upload(UserId);  //图片传入服务器
			int i = fileFileName.size();
			int index = 0;
			
			for(;i>0;i--){
				piecureService.UploadRunPicture(fileFileName.get(index), RunOrderId); //存入数据库
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
	
	/**  发布用户头像
	 * */
	public String HeadImg(){
	
		this.upload(UserId);  //图片传入服务器
		
		int result = piecureService.UploadHeadImg(fileFileName.get(0), UserId);
		if (result ==1){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/** 发布新的 兼职 评论
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
			
			this.upload(PublisherId);  //图片传入服务器
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
			//插入失败 图片也不用操作了
			System.out.println("############fail");
			return info(Message.message[18]);
		}
	} 
	
	
	
	/** 发布新的跑腿评论  可以发布图片
	 **/
	public String PublishRunComment(){
		if (RunId==0||UserId==0) {
			System.out.println(UserId);
			System.out.println(RunId);
			return info(Message.message[19]);
		}
		int RunCommentId = runCommentService.pubishNewComment(content, RunId, UserId, Type, star,PublisherId);
		if (RunCommentId > 0&&file!=null) {
			this.upload(PublisherId);  //图片传入服务器
			
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
			//插入失败 图片也不用操作了
			System.out.println("############fail");
			return info(Message.message[18]);
		}
	}
	
	 /**
     * 上传图片的方法  在两个发布中调用他们  -1失败  1成功
     *  */ 
	
	  public String upload(int id) {
      	try{
      		
      		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
      		System.out.println(fileFileName.get(0));
      		System.out.println(fileContentType.get(0));
      /**
       * 得到工程保存图片的路径
       * root 是默认图片路径+用户id的文件夹
       *  */ 
//          String root = "F:\\img"+"\\"+userid;  //转义字符
//      		File dir = new File(servletRequest.getRealPath("")+"/Img/"+userid+"/");
//  			if(!dir.exists())dir.mkdirs();
      	
      		 String root = servletRequest.getRealPath("")+"/Img/"+id;
      		System.out.println(root);
          System.out.println(file.size());
          
          //为每个用户建立专属文件夹
          File f =new File(root);    
          if  (!f.exists()  && !f.isDirectory())      
          {       
              System.out.println("//不存在");  
              f.mkdirs();    
              System.out.println("已经创建");  
          } else   
          {  
              System.out.println("//目录存在");  
          }
          
      
          //循环上传的文件
          for(int i = 0 ; i < file.size() ; i ++){
          	 //System.out.println(file.size());
              InputStream is = new FileInputStream(file.get(i));
              
              //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
              File destFile = new File(root,this.getFileFileName().get(i));
              
              //把图片写入到上面设置的路径里
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
