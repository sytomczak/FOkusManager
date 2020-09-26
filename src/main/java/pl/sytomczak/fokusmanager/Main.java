package pl.sytomczak.fokusmanager;

import pl.sytomczak.fokusmanager.dbUtils.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("First class");
        Connection connection = DBConnection.getConnection();
    }
}
