package ru.netology;

import java.util.*;

public class CountIpsDb {
    Map<String, Integer> countOfRepeatsIps = new HashMap<>(); // в этой Map лежит: key - сам ip,  value - количество повторений ip.

    public void addCountIpsInMap(String ip) {
        if (countOfRepeatsIps.containsKey(ip)) {
            int countIp = countOfRepeatsIps.get(ip);
            countOfRepeatsIps.put(ip, countIp + 1);
        } else {
            countOfRepeatsIps.put(ip, 1);
        }
    }

    public int getCountRepeatIp(String ip) {
//        Integer countRepeatIp = countOfRepeatsIps.get(ip);
        return countOfRepeatsIps.get(ip);
    }

    public boolean containIpInMapIps(String ip) { // почему-то не работает это
        return countOfRepeatsIps.containsKey(ip);
    }


//    public void printMapWithCountRepeatsIps() {
//        for (Map.Entry<String, Integer> entry : countOfRepeatsIps.entrySet()) {
//            System.out.println(entry);
//        }
//    }
//
//    public String ipWithMaxCountVisits() {
//        SortedSet<Integer> sortedSetAllValues = new TreeSet<>(countOfRepeatsIps.values());
//        Integer maxCount = sortedSetAllValues.last();
//        String villainIp = "xxx.xxx.xxx.xxx";
//        for (Map.Entry<String, Integer> entry : countOfRepeatsIps.entrySet()) {
//            if (entry.getValue().equals(maxCount)) {
//                villainIp = entry.getKey();
//            }
//        }
//        return villainIp;
//    }
}
