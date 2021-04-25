package pl.gondzio.songApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gondzio.songApp.domain.model.Role;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("")
public class indexController {

    @GetMapping
    public String index(){
        return "Hey";
    }

}
