import com.lnko.exception.LowBalanceException;
import com.lnko.model.dao.DaoFactory;
import com.lnko.model.dao.OrderDao;
import com.lnko.model.dao.TariffDao;
import com.lnko.model.dao.UserDao;
import com.lnko.model.entity.Tariff;
import com.lnko.model.entity.User;
import com.lnko.service.OrderService;
import com.lnko.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {
    private final TariffDao tariffDao = Mockito.mock(TariffDao.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);
    private final OrderDao orderDao = Mockito.mock(OrderDao.class);

    private final DaoFactory daoFactory = Mockito.mock(DaoFactory.class);

    private final OrderService orderService = new OrderServiceImpl(daoFactory);


    @BeforeEach
    public void setUp() {
        when(daoFactory.createOrderDao()).thenReturn(orderDao);
        when(daoFactory.createTariffDao()).thenReturn(tariffDao);
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    void saveNewOrdersShouldThrowLowBalanceExceptionWhenUserBalanceIsLowerThanSumOfTariffsPrice() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        long userId = 1;

        when(tariffDao.findAllByIds(eq(ids)))
                .thenReturn(findAllByIdsResponse(3, 4));

        when(userDao.findById(eq(userId)))
                .thenReturn(createUserWithBalance(1));

        LowBalanceException ex = assertThrows(LowBalanceException.class,
                () -> orderService.saveNewOrders(ids, userId));

        assertNotNull(ex);
    }

    private List<Tariff> findAllByIdsResponse(double... prices) {
        List<Tariff> result = new ArrayList<>();
        for (double price : prices) {
            Tariff tariff = new Tariff();
            tariff.setPrice(BigDecimal.valueOf(price));
            result.add(tariff);
        }

        return result;
    }

    private User createUserWithBalance(double balance) {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(balance));

        return user;
    }
}