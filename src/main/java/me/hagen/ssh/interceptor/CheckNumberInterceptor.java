package me.hagen.ssh.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by yujingyang on 2017/2/21.
 */
public class CheckNumberInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        String checkNumberRequest = (String) ServletActionContext.getRequest().getAttribute("checkNumber");
        if(checkNumberRequest!=null){
            String checkNumberSession = (String) ActionContext.getContext().getSession().get("checkNumber");
            if(checkNumberRequest.equals(checkNumberSession))
                return invocation.invoke();
            HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("error");
            return null;
        }
        return invocation.invoke();
    }
}
