package me.hagen.ssh.action;


import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by yujingyang on 2017/2/20.
 */
@SuppressWarnings("serial")
public class Upload extends AbstractAction{
    private File file;
    private String fileFileName;
    private String fileFileContentType;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }


    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    public String execute() throws Exception {

        response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            File f = this.getFile();
            if(this.getFileFileName().endsWith(".exe")){
                message="wrongFormat";
                out.println(message);
                return null;
            }
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream("/var/lib/tomcat7/webapps/ssh/img/"+ this.getFileFileName());
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            message = "error";
        }
        message = "/ssh/img/"+this.getFileFileName();
        out.println(message);
        return null;
    }
}
