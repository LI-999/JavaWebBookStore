package com.jakie.book.web;

import com.google.gson.Gson;
import com.jakie.book.pojo.User;
import com.jakie.book.service.UserService;
import com.jakie.book.service.impl.UserServiceImpl;
import com.jakie.book.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/***
 * @Author jakie
 * @Description //TODO 将先前的RegisterServlet和LoginServlet合并成UserServlet来处理业务
 * @Date 17:40 2025/6/17
 * @Param
 * @return
 **/
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        System.out.println(servletContext.getRealPath("/footer.jsp"));
//
//    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        if(user!=null){
            System.out.println("登录成功");
            //视频教的
            //登录成功的用户名
            req.getSession().setAttribute("user",user);

            //2025-6-27 22:35 用cookie有什么弊端吗  后期生成订单需要用到用户的id但 cookie中没有id session中有
//            Cookie cookie = new Cookie("username", username);
//            resp.addCookie(cookie);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            System.out.println("用户名或密码错误");
            req.setAttribute("Msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /***
     * @Author jakie
     * @Description //TODO
     * @Date 14:16 2025/6/18
     * @Param [request, response]
     * @return void
     **/
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //获取验证码
        String kaptchaSessionKey = (String)request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
//        System.out.println(username + " " + password + " " + email + " " + code);

        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());



        if (kaptchaSessionKey!=null&&kaptchaSessionKey.equals(code)) {
            if (!userService.existsUsername(username)) {
                System.out.println("注册成功");
                userService.registerUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            } else {
                System.out.println("用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                request.setAttribute("Msg","用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            }
        } else {
            System.out.println("验证码不正确");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.setAttribute("Msg","验证码不正确");
            try {
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("logout被调用");
//        HttpSession session = req.getSession();
//        session.invalidate();
//        resp.sendRedirect(req.getContextPath()+"/index.jsp");

        Cookie[] cookies = req.getCookies();

        for(Cookie cookie:cookies)
            if("username".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

    /**
     * @Author jakie
     * @Description //TODO 通过ajax技术 实现用户注册页面 输入用户名 输入框失焦过后直接判断该用户名是否有效
     * @Date 15:13 2025/7/1
     * @Param [request, response]
     * @return void
     **/
    protected void ajaxExsistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        Map<String,Boolean> map = new HashMap<>();

        map.put("exsistsUsername",userService.existsUsername(username));

        Gson gson = new Gson();

        String s = gson.toJson(map);

        response.getWriter().write(s);


    }
}
