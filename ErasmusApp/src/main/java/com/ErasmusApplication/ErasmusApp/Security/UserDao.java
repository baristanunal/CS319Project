package com.ErasmusApplication.ErasmusApp.Security;

import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service @AllArgsConstructor
public class UserDao {
    private final UserClassService userClassService;

//    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new User(
//                    "asd",
//                    "123",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            ),
//            new User(
//                    "qwe",
//                    "123",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_RULE"))
//            )
//    );
    public UserDetails findUserBySchoolId( String id){
        UserClass user = userClassService.getUserBySchoolId(id);
        return userClassService.loginCheck(id);
    }


//    public UserDetails findUserBySchoolId( String id){
//        return APPLICATION_USERS
//                .stream()
//                .filter(u -> u.getUsername().equals(id))
//                .findFirst()
//                .orElseThrow( () -> new UsernameNotFoundException("No user was found!!"));
//    }

}
