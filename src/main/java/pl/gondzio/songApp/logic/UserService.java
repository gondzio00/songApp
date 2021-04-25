package pl.gondzio.songApp.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gondzio.songApp.domain.dto.CreateUserRequest;
import pl.gondzio.songApp.domain.dto.UpdateUserRequest;
import pl.gondzio.songApp.domain.model.User;
import pl.gondzio.songApp.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.Optional;


import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(CreateUserRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }
        if (request.getAuthorities() == null) {
            request.setAuthorities(new HashSet<>());
        }

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        user = userRepo.save(user);

        return user;
    }

    @Transactional
    public Optional<User> update(int id, UpdateUserRequest request) {
        Optional<User> user = userRepo.findById(id);

        user.ifPresent(user1 -> {
            userRepo.save(user1);
        });

        return user;
    }

    @Transactional
    public User upsert(CreateUserRequest request) {
        Optional<User> optionalUser = userRepo.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            return create(request);
        } else {
            UpdateUserRequest updateUserRequest = new UpdateUserRequest();
            updateUserRequest.setFullName(request.getFullName());
            return update(optionalUser.get().getId(), updateUserRequest).get();
        }
    }

    @Transactional
    public void delete(int id) {
        Optional<User> user = userRepo.findById(id);

            user.ifPresent(u -> {
                u.setUsername(u.getUsername().replace("@", String.format("_%s@", u.getId())));
                u.setEnabled(false);
                u = userRepo.save(u);
            });


    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );
    }

    public boolean usernameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public Optional<User> getUser(int id) {
        return userRepo.findById(id);
    }

//    public List<User> searchUsers(Page page, SearchUsersQuery query) {
//        List<User> users = userRepo.searchUsers(page, query);
//        return users;
//    }

}