package ru.netology;

import java.util.*;

public class User {
//    private Set<String> ipSet;
    private String ip;
    private String id;
    private String fio;
    private String address;

    public User(String ip, String id, String fio, String address) {
        /*суть положить в Set пользователя все его ip содержащиеся в db
        * если такой пользователь(проверяем по ID) уже есть в базе, то мы берём его ip и кладём в Set*/
        this.ip = ip;
//        ipSet = new HashSet<>();
//        ipSet.add(ip);
        this.id = id;
        this.fio = fio;
        this.address = address;
    }

//    public void putIpInSet(String ip) {
//        ipSet.add(ip);
//    }

//    public Set<String> getSetIp() {
//        return ipSet;
//    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getFio() {
        return fio;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return  "ФИО:  " + fio + "  |||   " +
                "Адрес:  " + address;
//        return  id + ", " + fio + ", " + address + ", " + ipSet;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || !this.getClass().equals(object.getClass()))
            return false;

        User othrUser = (User) object;
        return othrUser.id.equals(this.id);
    }
}
