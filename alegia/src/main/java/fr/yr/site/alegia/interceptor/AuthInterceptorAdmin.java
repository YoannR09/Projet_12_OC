package fr.yr.site.alegia.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptorAdmin extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation pInvocation) throws Exception {
        String vResult;
        if (pInvocation.getInvocationContext().getSession().get("admin") != null ) {
            vResult = pInvocation.invoke();
        } else {
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }
}
