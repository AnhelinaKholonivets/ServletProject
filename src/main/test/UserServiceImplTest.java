import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.User;
import com.lnko.service.UserService;
import com.lnko.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private final UserDao userDao = Mockito.mock(UserDao.class);

    private final DaoFactory daoFactory = Mockito.mock(DaoFactory.class);

    private final UserService userService = new UserServiceImpl(daoFactory);

    @BeforeEach
    public void setUp() {
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    void updateBalanceWhenUserRefileBalance() {
        int id = 1;
        User user = createUserWithBalance(id, 10);
        int addBalance = 25;

        userService.refileBalance(user, BigDecimal.valueOf(addBalance));

        User expectUser = createUserWithBalance(id, 35);

        verify(userDao).updateBalance(eq(expectUser));
    }

    @Test
    void blockUserWhereUserIsUnblocked() {
        long id = 1;
        User user = createUserWithBlockStatus(id, false);

        when(userDao.findById(eq(id)))
                .thenReturn(user);

        userService.blockUser(user.getId());

        User expectUser = createUserWithBlockStatus(id, true);

        verify(userDao).updateStatus(eq(expectUser));

    }

    private User createUserWithBalance(long id, double balance) {
        User user = new User();
        user.setId(id);
        user.setBalance(BigDecimal.valueOf(balance));

        return user;
    }

    private User createUserWithBlockStatus(long id, boolean block) {
        User user = new User();
        user.setId(id);
        user.setBlocked(block);

        return user;
    }
}