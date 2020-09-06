package pl.camp.it.library.dao.impl.test;

import org.springframework.stereotype.Repository;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.User;

@Repository
public class UserDAOStub implements IUserDAO {

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        user.setId(4);
        user.setLogin("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");

        return user;
    }

    @Override
    public void addUser(User user) {

    }
}
