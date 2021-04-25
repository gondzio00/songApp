package pl.gondzio.songApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.gondzio.songApp.domain.dto.SearchUsersQuery;
import pl.gondzio.songApp.domain.exception.NotFoundException;
import pl.gondzio.songApp.domain.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    <S extends User> List<S> saveAll(Iterable<S> entities);

    <S extends User> S save(S entity);

    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);
//
//    List<User> searchUsers(Page page, SearchUsersQuery query);

}

interface UserRepoCustom {

    List<User> searchUsers(Page page, SearchUsersQuery query);

}
