package com.example.hotel;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final long ID;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private static List<User> users = new ArrayList<>(); // Static list to hold all users

    User(String name, String userName, String email, String password, String phoneNumber) {
        this.ID = users.size() + 100000;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        users.add(this); // Add the user to the static list upon creation
    }

    public abstract void updateProfile(String name, String userName, String email, String password, String phoneNumber);
    public abstract void printUserDetails();

    void updateUserName(String userName) {
        this.userName = userName;
    }
    void updateEmail(String email) {
        this.email = email;
    }
    void updatePassword(String password) {
        this.password = password;
    }
    void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    void updateName(String name) {
        this.name = name;
    }
    long getID() {
        return ID;
    }
    String getName() {
        return name;
    }
    String getUserName() {
        return userName;
    }
    String getEmail() {
        return email;
    }
    String getPassword() {
        return password;
    }
    String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    static List<User> getAllUsers() {
        return users;
    }
    static void removeUser(User user) {
        users.remove(user);
    }
    static void clearUsers() {
        users.clear();
    }
    static boolean checkUserRegistered(String u) {
        for (User user : users) {
            if (u.equals(user.getUserName())){
                return true;
            }
        }
        return false;
    }
    static void printUserCount() {
        System.out.println("Total number of users: " + users.size());
    }
    static void printUserDetailsByID(long ID) {
        for (User user : users) {
            if (user.getID() == ID) {
                System.out.println(user);
                return;
            }
        }
        System.out.println("User with ID " + ID + " not found.");
    }
    static void printUserDetailsByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                System.out.println(user);
                return;
            }
        }
        System.out.println("User with name " + name + " not found.");
    }
    static void printUserDetailsByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println(user);
                return;
            }
        }
        System.out.println("User with email " + email + " not found.");
    }



}
