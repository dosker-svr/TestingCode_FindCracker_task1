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
        String id = "159753";
        String ipO = "666.12.789.159";
        String ipT = "789.456.123";
        Set<String> set = new HashSet<>();
        Map<String, Set<String>> ipsForUser = new HashMap<>(); // Мапа где храним множества ip по каждому user. key - ID юзера, value - множество ip-шников юзера
        set.add(ipO);
        set.add(ipT);

        // When:
        ipsForUser.put(id, set);

        // Then:
        assertThat(set, hasItems("666.12.789.159", "789.456.123"));
        assertThat(ipsForUser, hasKey("159753"));
        assertThat(ipsForUser, hasValue(hasItems("666.12.789.159", "789.456.123")));
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
        assertThat(users.usersDb.entrySet(), not(empty()));
        assertThat(users.ipsForUser, hasKey("4567"));
    }
}
