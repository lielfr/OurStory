package org.tsofen.ourstory.UserModel;

public class UserStatusCheck {
    static  String userStatus= "visitor";
    static void setUserStatus(String status)
    {
        userStatus=status;
    }
    public static String getUserStatus()
    {
        return userStatus;
    }
}
