package pollub.myplanszeo.service;

import pollub.myplanszeo.model.User;

public interface UserService {

    User getUserById(Long userId);
    User addUser(User user);

}
