package user.processor.service;

import java.util.Optional;
import user.processor.model.User;

public interface UserService {
    User save(User user);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String Email);
}
