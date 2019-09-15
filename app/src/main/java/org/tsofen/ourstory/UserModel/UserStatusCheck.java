package org.tsofen.ourstory.UserModel;

public class UserStatusCheck {
    static  String userStatus= "visitor";

    public static String getUserStatus()
    {
        return userStatus;
    }

    public static void setUserStatus(String status) {
        userStatus = status;
    }
}
