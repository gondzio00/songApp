package pl.gondzio.songApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gondzio.songApp.domain.model.Song;
import pl.gondzio.songApp.domain.model.SongGroup;
import pl.gondzio.songApp.domain.model.User;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

}

