package pl.gondzio.songApp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gondzio.songApp.configuration.security.JwtTokenUtil;
import pl.gondzio.songApp.domain.dto.AuthRequest;
import pl.gondzio.songApp.domain.dto.CreateUserRequest;
import pl.gondzio.songApp.domain.model.User;
import pl.gondzio.songApp.logic.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @GetMapping("user")
    public ResponseEntity<User> getAuthUser(Authentication authentication){

        if(authentication.isAuthenticated()) {
            return ResponseEntity.ok((User) authentication.getPrincipal());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("user_details")
    public ResponseEntity<List<Object>> getAuthUserDetails(Authentication authentication){

        if(authentication.isAuthenticated()) {

            List<Object> creditials = new ArrayList<>();

            creditials.add(authentication.getPrincipal());
            creditials.add(authentication.getDetails());
            creditials.add(authentication.getAuthorities());
            creditials.add(authentication.getCredentials());

            return ResponseEntity.ok(creditials);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public User register(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    @PostMapping("logout")
    public ResponseEntity logout(Authentication authentication) {
        authentication.setAuthenticated(false);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}