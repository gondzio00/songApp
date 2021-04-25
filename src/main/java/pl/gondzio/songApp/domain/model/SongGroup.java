package pl.gondzio.songApp.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class SongGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Song.class)
    private Set<Song> songs = new HashSet<>();

    public SongGroup() {
    }

}