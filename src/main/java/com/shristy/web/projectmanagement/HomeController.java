/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement;

import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.AppUserRole;
import com.shristy.web.projectmanagement.entity.Room;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.UserImage;
import com.shristy.web.projectmanagement.entity.ga.ClassRoom;
import com.shristy.web.projectmanagement.entity.ga.Combination;

import com.shristy.web.projectmanagement.entity.ga.Day;
import com.shristy.web.projectmanagement.entity.ga.TimeSlot;
import com.shristy.web.projectmanagement.entity.ga.Week;
import com.shristy.web.projectmanagement.repository.SubjectsRepository;
import com.shristy.web.projectmanagement.repository.impl.AppUserRoleServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.AppUserServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.RoomServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.UserImageServiceImpl;
import com.shristy.web.projectmanagement.service.UserService;

import com.shristy.web.projectmanagement.utils.WebUtils;
import com.shristy.web.projectmanagement.wise.Init;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
  @Autowired
    private TeacherServiceImpl tr;
  @Autowired
    private CoursesServiceImpl cr;
     @Autowired
    private RoomServiceImpl rr;
    @Autowired
    private SubjectsServiceImpl sr;
    @Autowired
    private SubjectsRepository srr;
     @Autowired
    BCryptPasswordEncoder encoder;
 @Autowired
    private AppUserRoleServiceImpl ur;
    
    @Autowired
    private UserImageServiceImpl ui;
      @Autowired
    UserService userService;
      
       @Autowired
    private AppUserServiceImpl aur;
    
    
    @GetMapping
    public String home() throws IOException, SQLException {
            

        return "homePage";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
    @ResponseBody
@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        System.out.println("testtttttttttttttt---"+tr.test());
        return "loginPage";
    }
    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String RegisterPage(Model model) {

        return "registerPage";
    }
   
   @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  
     public String RegisterUser(Model model,@ModelAttribute("user") AppUser users, @RequestParam("uname") String username,
     @RequestParam("fname") String fullname,
     @RequestParam("role") String role,
     @RequestParam("email") String email,@RequestParam("password") String password) {
        
     AppUser user= new AppUser();
     AppUserRole urole= new AppUserRole();
         
     Long roleid = null;
      

     if(null != role)
     switch (role) {
     case "ROLE_TEACHER":
     roleid=(long)2;
     break;
     case "ROLE_STUDENT":
     roleid =(long)3;
     break;
     }
        
        
     user.setUserId(users.getUserId());
     user.setUserName(username);
     user.setFullName(fullname);
      user.setEncrytedPassword(encoder.encode((CharSequence) password));
      // user.setEncrytedPassword(encoder.encode(user.getEncrytedPassword()));
    // user.setEncrytedPassword(password);
     user.setEmail(email);
     user.setEnabled(true);
     aur.save(user);
     urole.setUserRoleId(ur.lastId() + 1);
     urole.setUserId(user.getUserId());
     urole.setRoleId(roleid);
     /* System.out.println(aur.lastId().getUserId() + 1);
     System.out.println(username);
     System.out.println(fullname);
     System.out.println(password);
     System.out.println(email);*/

       //System.out.println(roleid);
      ur.save(urole);
      ui.save (new UserImage((ui.lastId().getImgId()+1),"/img/avatar7.png",user));
               
       model.addAttribute("successMessage", "You are registered successfully");
       model.addAttribute("room",rr.getAll());
       model.addAttribute("courses",cr.getAll());
      if("ROLE_STUDENT".equals(role))
          return "user/student/studentProfile";
       else
     return "loginPage";
     }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
    
     @RequestMapping(value = "/uploadServlet",  method = RequestMethod.POST)
//@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
//public class UploadController extends HttpServlet {
     
    // database connection settings
  
     
    public String doPost(HttpServletRequest request,
            HttpServletResponse response,Principal principal,Model model) throws ServletException, IOException {
        
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
             System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        String content=filePart.getContentType();
       String cont = "";
        int j=0;
        for(int i =content.indexOf("/")+ 1; i <content.length();i++){
             cont+=content.charAt(i);
             j++;
             }
        
         //   D:\classes\project_third\manager-test\src\main\resources\static\img
              FileOutputStream os = new FileOutputStream("e:/classes/project_edit/manager-test/src/main/resources/static/img/"+ principal.getName()+"."+ cont);
              String src="/img/"+ principal.getName()+"."+ cont;
              byte[] data = new byte[1024 * 10];
            int i = 0;
           if (inputStream != null) {
                while((i=inputStream.read(data)) != -1) {
                os.write(data, 0, i);
           }
            os.close();
          inputStream.close();
        } else {
            System.out.println("Not enough parameters");
       }
                UserImage img = new UserImage();
             String name = principal.getName();
               for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             img.setImgId(us.getImgId());
             System.out.println(us.getUser());
          }else{
           img.setImgId((ui.lastId().getImgId()+1));
          }
         }
               
        for (AppUser us : aur.getAll()) {
            if (us.getUserName().equals(name)) {
                
                img.setUser(us);
                img.setImage(src);
                 System.out.println(us);
            }
        }
      
            ui.save(img);
     return "redirect:/user/userProfile";
    }
}
           
             

