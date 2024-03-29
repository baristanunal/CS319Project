package com.ErasmusApplication.ErasmusApp.Security;

import com.ErasmusApplication.ErasmusApp.DataOnly.AuthDao;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
//    private final UserClassService userClassService;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final UserClassService userClassService;
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/registrate")
    public ResponseEntity<UserClass> registrateUser(UserClass user, String roleName){
        UserClass userClass = userClassService.registerUser(user,roleName);
        return new ResponseEntity<>(userClass,HttpStatus.OK );
    }

    @PostMapping("/updatePassword/{userId}")
    public ResponseEntity<UserClass> updatePassword(@PathVariable Long userId){
        UserClass userClass = userClassService.updatePassword(userId);
        return new ResponseEntity<>(userClass,HttpStatus.OK );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthDao> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println(request);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//        );

        AuthDao authDao = new AuthDao();
        final UserDetails user = userDao.findUserBySchoolId(request.getUserName());
        authDao.setRole(userClassService.getRole(request.getUserName()));
        if (user != null){
//                return new ResponseEntity<>(jwtUtils.generateToken(user), HttpStatus.OK);
            authDao.setToken(jwtUtils.generateToken(user));
            return new ResponseEntity<>(authDao, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
