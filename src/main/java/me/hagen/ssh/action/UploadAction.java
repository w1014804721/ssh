package me.hagen.ssh.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

@Controller("UploadAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class UploadAction extends AbstractAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1174643383678770382L;

	
    
    
		private int userid;
    	private List<File> file;
        private List<String> fileFileName;
        private List<String> fileContentType;
        private String imgName;
       
        
        
        
        
        public String upload() {
        	try{
        		
        		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        		System.out.println(fileFileName.get(0));
        		System.out.println(fileContentType.get(0));
        /**
         * 得到工程保存图片的路径
         * root 是默认图片路径+用户id的文件夹
         *  */ 
//            String root = "F:\\img"+"\\"+userid;  //转义字符
//        		File dir = new File(servletRequest.getRealPath("")+"/Img/"+userid+"/");
//    			if(!dir.exists())dir.mkdirs();
        	
        		 String root ="/var/lib/tomcat7/Img/"+userid;
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        public String getImgName() {
			return imgName;
		}


		public void setImgName(String imgName) {
			this.imgName = imgName;
		}


















		private ByteArrayInputStream inputStream;
        
          
        public ByteArrayInputStream getInputStream() {
			return inputStream;
		}


		public void setInputStream(ByteArrayInputStream inputStream) {
			this.inputStream = inputStream;
		}

		public String getPicture() throws Exception{
        //http://localhost/ssh/Upload/getPicture?imgName=IMG_0001.JPG&&userid=0  输入这个url就可以看到图片

        	
//        	String img = "F:\\img\\"+userid+"\\"+imgName;
//        	String img ="/Image/"+userid+"/"+imgName;
			
			String img =  servletRequest.getRealPath("")+"/Img/"+userid+"/"+imgName;
        	System.out.println(img);
        
        		
        		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        		 File fl = new File(img);
                 if (!fl.exists()) {
                     return null;
                 }
                 FileInputStream fis = new FileInputStream(fl);
                 InputStream input = new BufferedInputStream(fis);
                 byte[] bt = new byte[1024];
                 
                 while (input.read(bt) > 0) {
         			bos.write(bt);
         		}
                 this.inputStream = new ByteArrayInputStream(bos.toByteArray());
         		bos.close();
         		input.close();
         	
        	
             
             // 返回函数值
             return SUCCESS;

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
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
    
    	
}


