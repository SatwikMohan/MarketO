package com.gigawattstechnology.marketo;

public class authtransfer {
private static String supermarket,address,manager,auth;
public static void storesupermarket(String name){
    supermarket=name;
}
public static String givesupermarket(){
    return supermarket;
}
public static void storeaddress(String name){
    address=name;
}
public static String giveaddress(){
    return address;
}
public static void storemanager(String name){
    manager=name ;
}
public static String givemanager(){
    return manager;
}
public static void storeauth(String name){
    auth=name;
}
public static String giveauth(){
    return auth;
}
}
