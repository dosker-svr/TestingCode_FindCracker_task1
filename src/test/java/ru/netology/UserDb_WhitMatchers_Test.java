package ru.netology;

import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDb_WhitMatchers_Test {

    @Test
    public void addIpToSetIpsForUser_Test() {
        // Given:
        UsersDb usersDb = new UsersDb();
        String id = "159753";
        String ipO = "666.12.789.159";
        String ipT = "789.456.123";
        Set<String> set = new HashSet<>();
        set.add(ipO);
        set.add(ipT);

        // When:
        usersDb.addIpToSetIpsForUser(id, ipO);
        usersDb.addIpToSetIpsForUser(id, ipT);

        // Then:
        assertThat(usersDb.ipsForUser.get(id), hasItems("666.12.789.159", "789.456.123"));
        assertThat(usersDb.ipsForUser, hasKey("159753"));
        assertThat(usersDb.ipsForUser, hasValue(hasItems("666.12.789.159", "789.456.123")));
    }

    @Test
    public void getVillain_Test() {
        // Given:
        User userV = new User("1.2.3.4", "4567", "Ждордж Милославский", "Арбатский он");

        UsersDb users = new UsersDb();
        users.addUserInMapDb(userV);
        users.addIpToSetIpsForUser(userV.getId(), userV.getIp());

        CountIpsDb countIpsDb = new CountIpsDb();
        countIpsDb.addCountIpsInMap(userV.getIp());

        // When:
        User villain = users.getVillain(countIpsDb);

        // Then:
        assertThat(villain.getId(), equalTo("4567"));
        assertThat(users.ipsForUser, hasKey("4567"));
    }
}
