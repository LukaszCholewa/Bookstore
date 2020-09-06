package pl.camp.it.library.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.library.AppConfigurationTest;
import pl.camp.it.library.dao.IAuthorDAO;
import pl.camp.it.library.dao.IBookDAO;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IUserDAO userDAO;
    @MockBean
    IBookDAO bookDAO;
    @MockBean
    IAuthorDAO authorDAO;

    @Before
    public void setUpMocks() {
        User user = new User();
        user.setId(5);
        user.setLogin("lukasz");
        user.setPassword(DigestUtils.md5Hex("asdffg"));

        Mockito.when(this.userDAO.getUserByLogin("lukasz")).thenReturn(user);

        user = new User();
        user.setId(5);
        user.setLogin("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");

        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(user);

        Mockito.when(userDAO.getUserByLogin("badLogin")).thenReturn(null);
    }

    @Test
    public void wrongPasswordAuthenticationTest() {
        /*Mockito
                .when(this.userDAO.getUserByLogin(anyString()))
                .thenReturn(generateUser("lukasz", "asds", 5));*/
        User user = new User();
        user.setLogin("lukasz");
        user.setPassword("asd");

        boolean result = userService.authenticate(user);
        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest() {
        /*Mockito
                .when(this.userDAO.getUserByLogin(anyString()))
                .thenReturn(generateUserAndHashPassword("admin", "admin", 6));*/
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);
        Assert.assertTrue(result);
    }

    @Test
    public void wrongLoginAuthenticationTest() {
        User user = new User();
        user.setLogin("badLogin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);

        Assert.assertFalse(result);
    }

    @Test
    public void wrongRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "janusz2";

        boolean result = userService.registerUser(user, repeatedPassword);

        Assert.assertFalse(result);
    }

    @Test
    public void correctRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "janusz";

        boolean result = userService.registerUser(user, repeatedPassword);
        verify(this.userDAO, times (1)).addUser(any());

        Assert.assertTrue(result);
    }

    /*private User generateUser(String login, String hashedPass, int id) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(hashedPass);
        user.setId(id);
        return user;
    }

    private User generateUserAndHashPassword (String login, String pass, int id) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(pass));
        user.setId(id);
        return user;
    }*/
}
