package ru.netology;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, RuntimeException {
        UsersDb usersDb = new UsersDb();
        File file1 = new File("users.db");
        Scanner scannerDb = new Scanner(file1);

        CountIpsDb countIpsDb = new CountIpsDb();
        File file2 = new File("server.log");
        Scanner scannerLog = new Scanner(file2);

        while (scannerDb.hasNext()) {
            String line = scannerDb.nextLine();
            String[] parts = line.split(";");
            parseLineToSet(usersDb, parts);
        }
        scannerDb.close();

//        usersDb.addSetIpsToMap();

        while (scannerLog.hasNext()) {
            String line = scannerLog.nextLine();
            String[] parts = line.split(":");
            countIpsDb.addCountIpsInMap(parts[0]);
        }

//        countIpsDb.printMapWithCountRepeatsIps();
//        System.out.println(countIpsDb.containIpInMapIps("1.10.186.173"));

//        String villainIp = countIpsDb.ipWithMaxCountVisits();
//        User villain = usersDb.villain(villainIp);

        System.out.println("Кто же негодяй? Это :\n");
        System.out.println(usersDb.getVillain(countIpsDb));

        scannerLog.close();
    }

    private static void parseLineToSet(UsersDb usersDb, String[] parts) {
        String ip = parts[0];
        String id = parts[1];
        String fio = parts[2];
        String address = parts[3];
        if (id.equals("id")) {
            return;
        }

        if (usersDb.containsUserInMapDb(id)) {
            /*нам нужно получить пользователя который есть в Map под ID. а потом во множество этого пользователя положить ip*/
            usersDb.addIpToSetIpsForUser(id, ip);
//                usersDb.addIpInSetOfUserIps(id, ip);
        } else {
            User user = new User(ip, id, fio, address);
            usersDb.addUserInMapDb(user);
            usersDb.addIpToSetIpsForUser(id, ip);
        }
    }
}
