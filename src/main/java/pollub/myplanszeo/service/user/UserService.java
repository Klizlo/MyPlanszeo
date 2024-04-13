package pollub.myplanszeo.service.user;

import pollub.myplanszeo.model.User;

public interface UserService {

    User getUserById(Long userId);
    User addUser(User user);

}
