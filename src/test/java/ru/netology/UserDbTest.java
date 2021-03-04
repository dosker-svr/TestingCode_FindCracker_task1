package ru.netology;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserDbTest {
    @Test
    public void addUserInMapDbTest() {
        // Given:
        User user = new User("123", "124", "Cloya Goo Intov", "Москва, Академика академиков, д.666, кв 001");
        Map<String, User> usersDb = new TreeMap<>();

        // When:
        usersDb.put(user.getId(), user);

        // Then:

        assertEquals(user, usersDb.put(user.getId(), user));
        assertSame(user, usersDb.put(user.getId(), user));
    }

    @Test
    public void addIpToSetIpsForUserTest() {
        // Given:
        Set<String> set = new HashSet<>();
        Map<String, Set<String>> ipsForUser = new HashMap<>();
        String id = "123";
        String ip = "456";

        // When:
        set.add(ip);
        ipsForUser.put(id, set);

        // Then:
        assertTrue(set.add(ip));
        assertEquals(set, ipsForUser.put(id, set));
    }

    @Test
    public void getVillainTest() {
        // Given:
        User user = new User("1.2.3.4", "4567", "Ждордж Милославский", "Арбатский он");

        UsersDb users = new UsersDb();
        users.addUserInMapDb(user);
        users.addIpToSetIpsForUser(user.getId(), user.getIp());

        CountIpsDb countIpsDb = new CountIpsDb();
        countIpsDb.addCountIpsInMap(user.getIp());


        // When:
        User villain = users.getVillain(countIpsDb);

        // Then:
        assertEquals(user.getId(), villain.getId());
    }
}

/*Вопрос - нужно ли каждый раз создавать то, что используется в каждом тестируемом методе, напрмер:
* Map<String, Set<String>> ipsForUser = new HashMap<>();
* Map<String, User> usersDb = new TreeMap<>();
* как в этом тесте были бы применены @BeforeClass/@AfterClass и штука @Before/@After. нужно ли было делатьэто для метода getVillainTest()
 * */
