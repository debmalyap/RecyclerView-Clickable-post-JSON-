package in.qbent.com.qbentrecclick;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static in.qbent.com.qbentrecclick.PreferencesUtility.LOGGED_IN_PREF;

public class SaveSharedPreferences
{
    static SharedPreferences getPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //set the login status//
    public static void setLoggedIn(Context context, boolean loggedIn)
    {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    //get the login status//
    public static boolean getLoggedStatus(Context context)
    {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }
}
