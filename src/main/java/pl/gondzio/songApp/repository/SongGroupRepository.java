package pl.gondzio.songApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gondzio.songApp.domain.model.SongGroup;
import pl.gondzio.songApp.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongGroupRepository extends JpaRepository<SongGroup, Integer> {
    Optional<SongGroup> findOneByOwner(User user);

    Optional<List<SongGroup>> findByOwnerIsNull();

    Optional<SongGroup> findOneById(int id);
}

