package ltd.ontsol.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ltd.ontsol.core.dto.RoleDTO;
import ltd.ontsol.core.dto.UserDTO;
import ltd.ontsol.core.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findByUserName(username);
//        UserDTO defaultUser = userService.getDefaultUser();
//        if (defaultUser.getUserName().equalsIgnoreCase(username)) {
//            user = defaultUser;
//        }

        if (user == null) {
            throw new UsernameNotFoundException("Login in failed, please check your user name or password");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleDTO role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
//        private HttpServletRequest request;
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            session = request.getSession(true);
//        }
//        session.setAttribute("user",user);
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
