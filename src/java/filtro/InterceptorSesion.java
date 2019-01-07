/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author Alumno
 */
public class InterceptorSesion implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session = ActionContext.getContext().getSession();
        System.out.println("filtro.InterceptorSesion.intercept() afuera");
        if (session.containsKey("logueado")) {
            System.out.println(":>" + ai.getAction().toString());
            System.out.println("filtro.InterceptorSesion.intercept() logueado");
            return ai.invoke();
        } else {
            System.out.println("filtro.InterceptorSesion.intercept() nologin");
            return "nologin";
        }
    }

}
