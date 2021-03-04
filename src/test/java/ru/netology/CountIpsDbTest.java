package ru.netology;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class CountIpsDbTest {
    @Test
    public void addCountIpsInMapTest() {
        // Given:
        String ip = "123.456.789";
        Integer count = 666;
        Map<String, Integer> countOfRepeatsIps = new HashMap<>();

        // When:
        countOfRepeatsIps.put(ip, count);
        countOfRepeatsIps.containsKey(ip);
        List<String> listIp = new ArrayList<>(countOfRepeatsIps.keySet());

        // Then:
        assertThat(count, equalTo(countOfRepeatsIps.get(ip)));
        assertThat(listIp, not(empty()));
        assertThat(listIp, hasItem("123.456.789"));
        assertTrue(countOfRepeatsIps.containsKey(ip));
        assertThat(countOfRepeatsIps, hasKey("123.456.789"));
        assertThat(countOfRepeatsIps, hasValue(666));
    }

}
