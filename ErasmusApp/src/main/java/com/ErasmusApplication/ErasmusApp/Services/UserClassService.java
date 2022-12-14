package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.RoleRepository;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

//@RequiredArgsConstructor
@Service  @Transactional
public class UserClassService implements UserDetailsService {
//    ErasmusManager erasmusManager;
    private final RoleRepository roleRepository;
    private final UserClassRepository userClassRepository;

    @Autowired
    public UserClassService(RoleRepository roleRepository, UserClassRepository userClassRepository) {
        this.roleRepository = roleRepository;
        this.userClassRepository = userClassRepository;
    }


    public User loginCheck(String id){
        UserClass user = userClassRepository.findBySchoolIdOpt(id)
                .orElseThrow( () -> new UsernameNotFoundException("No user was found!!"));

        return new User(user.getSchoolId(),user.getPassword(),user.getAuthorities());
    }
    public UserClass saveUser(UserClass user) {
        Optional<UserClass> userBySchoolId = userClassRepository.findBySchoolIdOpt(user.getSchoolId());
        if( userBySchoolId.isPresent()){
            throw new IllegalStateException("School Id is taken!");
        }
        return userClassRepository.save(user);
    }

    @Transactional
    public UserClass updatePassword(Long userId) {
        UserClass user = getUser(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }
    public UserClass registrateUser(UserClass user, String roleName){
        UserClass userFromDatabase = saveUser(user);

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRole(roleRepository.findByName(roleName));
        return user;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


    public void addRoleToUser(String schoolId, String roleName) {
        UserClass user = getUserBySchoolId(schoolId);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public UserClass getUser(Long userId) {

        return userClassRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User With Id: " + userId + " does not exist."
        ));
    }

    public UserClass getUserBySchoolId(String schoolId) {
        UserClass user = userClassRepository.findBySchoolId(schoolId);
        return user;
    }

    //    @Autowired
//    public UserClassService(ErasmusManager erasmusManager) {
//        this.erasmusManager = erasmusManager;
//    }
//TODO  add erasmus manager with its implementation
    public List<UserClass> getUsers(){
        return userClassRepository.findAll();
    }


    public void deleteStudent(Long userClassId) {
        boolean exist = userClassRepository.existsById(userClassId);
        if(!exist){
            throw new IllegalStateException("User with Id: " + userClassId + " does not exist!");
        }
        userClassRepository.deleteById(userClassId);
    }

//    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new User(
//                    "admin",
//                    "admin",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            ),
//            new User(
//                    "user",
//                    "user",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            )
//    );
    @Override
    public UserDetails loadUserByUsername(String username) {
        //We userd schooldId as username so

        UserClass user = getUserBySchoolId(username);
        return new User(user.getSchoolId(),user.getPassword(),user.getRoles());
    }
}
