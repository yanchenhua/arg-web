package ltd.ontsol.web.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ltd.ontsol.core.dto.UserDTO;
import ltd.ontsol.core.service.HomeService;
import ltd.ontsol.core.service.UserService;
import ltd.ontsol.core.util.IpUtil;

@Controller
public class ManageController {

    @Inject
    UserService userService;

    @Inject
    HomeService homeService;

    @RequestMapping(value = {"/manage/{pageName}"}, method = RequestMethod.GET)
    public String managePage(@PathVariable("pageName") String pageName, Principal principal, ServletRequest servletRequest) {
        if (principal != null) {
            String userName = principal.getName();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession(false);
            if (session == null) {
                session = request.getSession(true);
            }

            if (session.getAttribute("user") == null) {
                UserDTO user = userService.findByUserName(userName);
                session.setAttribute("user", user == null ? new UserDTO() : user);
            }

            if (session.getAttribute("ipaddr") == null) {
                session.setAttribute("ipaddr", IpUtil.getIpAddr(request));
            }
            //init data for home dto
            if (homeService.countHome() == 0) {
                return "/manage/basic_info";
            }
        }
        return "/manage/" + pageName;
    }

    @RequestMapping(value = {"/manage/"}, method = RequestMethod.GET)
    public String manageIndexPage() {
        return "/manage/index";
    }

    @RequestMapping(value = {"/manage_static/{pageName}"}, method = RequestMethod.GET)
    public String manageStaticPage(@PathVariable("pageName") String pageName) {
        return "/manage_static/" + pageName;
    }

    @RequestMapping(value = {"/manage/{pageName}/{secName}"}, method = RequestMethod.GET)
    public String manageSecPage(@PathVariable("pageName") String pageName,
                                @PathVariable("secName") String secName) {
        return "/manage/" + pageName + "/" + secName;
    }

}