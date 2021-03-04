package ru.netology;

import java.util.*;

public class UsersDb {
    Map<String, User> usersDb = new TreeMap<>(); // таблица User, key - ID юзера, value - сам user
    Map<String, Set<String>> ipsForUser = new HashMap<>(); // Мапа где храним множества ip по каждому user. key - ID юзера, value - множество ip-шников юзера

    public void addUserInMapDb(User user) {
        usersDb.put(user.getId(), user);
    }

    public void addIpToSetIpsForUser(String id, String ip) {
        if (ipsForUser.containsKey(id)) {
            ipsForUser.get(id).add(ip);
        } else {
            Set<String> set = new HashSet<>();
            set.add(ip);
            ipsForUser.put(id, set);
        }
    }

    public boolean containsUserInMapDb(String userId) {
        return usersDb.containsKey(userId);
    }

    public User getVillain(CountIpsDb countIpsDb) {
        Map<String, Integer> countRepeatsIpsUser = new HashMap<>();    // key - ID Юзера, value - сумма повторений со всех ip Юзера

        for (Map.Entry<String, Set<String>> entry : ipsForUser.entrySet()) {    // итерируемся по Мапе ipsForUser
            for (String ip : entry.getValue()) {    // итерируемся по ip в Set определенного user в Мапе ipsForUser
                if (countIpsDb.containIpInMapIps(ip)) {
                    String idUser = entry.getKey();     // id Юзера
                    if (!countRepeatsIpsUser.containsKey(idUser)) {
                        countRepeatsIpsUser.put(idUser, 0);
                    }
                    int countRepeat = countRepeatsIpsUser.get(idUser);   // кол-во повторений ip у конкретного юзера
                    countRepeat = countRepeat + countIpsDb.getCountRepeatIp(ip);    // к кол-ву повторений Юзера, добавляем кол-во повторений по ещё одному ip
                    countRepeatsIpsUser.put(idUser, countRepeat);    // кладём в Мапу суммарное кол-во повторений
                }
            }
        }

        String idVillain = "id";
        List<Integer> listValuesRepeats = new ArrayList<>(countRepeatsIpsUser.values());
        Collections.sort(listValuesRepeats);
        Integer lastInt = listValuesRepeats.get(listValuesRepeats.size() - 1);
        for (Map.Entry<String, Integer> entry : countRepeatsIpsUser.entrySet()) {
            if(entry.getValue().equals(lastInt)) {
                idVillain = entry.getKey();
            }
        }
//        for (Map.Entry<String, Integer> entry : countRepeatsIpsUser.entrySet()) {
//            System.out.println(entry);
//        }
        return usersDb.get(idVillain);
    }

//    public void addSetIpsToMap() {
//        for (Map.Entry<String, User> entry : usersDb.entrySet()) {
//            User ipsUser = entry.getValue();
//            ipsForUser.put(ipsUser.getId(), ipsUser.getSetIp());
//        }
//    }

//    public void printMapWithIps() {
//        System.out.println("ip адреса клиентов:\n" +
//                "\t\t\tid, ip");
//        for (Map.Entry<String, Set<String>> entry : ipsForUser.entrySet()) {
//            System.out.println(entry);
//        }
//    }

//    public User villain(String villainIp) {
//        String villainID = "id";
//        for (Map.Entry<String, Set<String>> entry : ipsForUser.entrySet()) { // цикл прохождения по Map ipsForUser
//            for (String string : entry.getValue()) {
//                if (string.equals(villainIp)) {
//                    villainID = entry.getKey();
//                }
//            }
//        }
//        return usersDb.get(villainID);
//    }

//    public void addIpInSetOfUserIps(User user) {
//        User userInMap = usersDb.get(user.getId()); // берём по ключу user из Map
//        userInMap.putIpInSet(user.getIp()); // этому пользователю добавляем ip
//    }

//    public boolean containsUserInMap(User user) {
//        return usersDb.containsValue(user);
//    }

//    public void printMap() {
//        System.out.println("База клиентов:\n" +
//                "\t\t\tid, ФИО, Адрес, ip");
//        for (Map.Entry<String, User> entry : usersDb.entrySet()) {
//            System.out.println(entry);
//        }
//    }

//    public void addIpInSetOfUserIps(String userID, String userIP) {
//        User userInMap = usersDb.get(userID); // берём по ключу user из Map
//        userInMap.putIpInSet(userIP); // этому пользователю добавляем ip
//        ipsForUser.put(userID, userInMap.getSetIp());
//    }
}
