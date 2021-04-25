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
import pl.gondzio.songApp.domain.dto.SongInsert;
import pl.gondzio.songApp.domain.model.SongGroup;
import pl.gondzio.songApp.domain.model.User;
import pl.gondzio.songApp.logic.SongGroupService;
import pl.gondzio.songApp.logic.UserService;
import pl.gondzio.songApp.repository.SongGroupRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "song-group/")
@RequiredArgsConstructor
public class SongGroupApi {

    private final SongGroupService songGroupService;

    @GetMapping("main")
    public ResponseEntity<SongGroup> getAllSongGroups(){

        Optional<SongGroup> group = songGroupService.getMainSongGroup();

        if(group.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(group.get());
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("add")
    public ResponseEntity<SongGroup> addSongToMainGroup(@RequestBody @Valid SongInsert song) throws Exception {
        SongGroup group = songGroupService.addSongToMainGroup(song);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

}