package com.gigawattstechnology.customer_side;

public class authtransfer {
    private static String auth;
    public static void storeauth(String name){
        auth=name;
    }
    public static String giveauth(){
        return auth;
    }
}
