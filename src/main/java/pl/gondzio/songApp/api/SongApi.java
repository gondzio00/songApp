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
public class SongApi {



}