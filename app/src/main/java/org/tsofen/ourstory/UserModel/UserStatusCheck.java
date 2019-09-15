package org.tsofen.ourstory.UserModel;

public class UserStatusCheck {
    static  String userStatus= "visitor";
    static void setUserStatus(String status)
    {
        userStatus=status;
    }
    static String getUserStatus()
    {
        return userStatus;
    }
}
