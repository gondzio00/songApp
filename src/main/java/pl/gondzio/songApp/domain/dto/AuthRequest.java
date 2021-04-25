package pl.gondzio.songApp.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

    @NotNull(message = "Username field can not be null") @Email
    private String username;
    @NotNull
    private String password;

}