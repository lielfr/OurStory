package org.tsofen.ourstory.UserModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_STATUS= "visitor";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUserStatus(Context ctx, String userStatus)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_STATUS, userStatus);
        editor.commit();
    }

    public static String getPrefUserStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_STATUS, "");
    }
}
