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
        UsersDb usersDb = new UsersDb();
        User user = new User("123", "124", "Cloya Goo Intov", "Москва, Академика академиков, д.666, кв 001");

        // When:
        usersDb.addUserInMapDb(user);

        // Then:
        assertEquals(user, usersDb.usersDb.get("124"));
    }

    @Test
    public void addIpToSetIpsForUserTest() {
        // Given:
        UsersDb usersDb = new UsersDb();
        String id = "123";
        String ip = "456";
        String ip2 = "789";
        Set<String> setIps = new TreeSet<>();
        setIps.add(ip);
        setIps.add(ip2);
        Iterable<String> iterable = setIps;
        System.out.println(iterable.toString());

        // When:
        usersDb.addIpToSetIpsForUser(id, ip);
        usersDb.addIpToSetIpsForUser(id, ip2);

        // Then:
        assertIterableEquals(iterable, usersDb.ipsForUser.get("123"));
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
        users.getVillain(countIpsDb);

        // Then:
        assertEquals("4567", users.getVillain(countIpsDb).getId());
    }
}

/*Вопрос - нужно ли каждый раз создавать то, что используется в каждом тестируемом методе, напрмер:
* Map<String, Set<String>> ipsForUser = new HashMap<>();
* Map<String, User> usersDb = new TreeMap<>();
* как в этом тесте были бы применены @BeforeClass/@AfterClass и штука @Before/@After. нужно ли было делатьэто для метода getVillainTest()
 * */
