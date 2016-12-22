package com.overswayit.plesnisavezsrbije.domain.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.overswayit.plesnisavezsrbije.domain.enums.Activities;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class ScreenPreferences {

    /**
     * Key for Preferences
     */
    private final String _PREFERENCE_KEY = "overswayit_first_screen_preferences_key";

    /**
     * Key for finding frst Screen
     */
    private final String _FIRST_SCREEN_KEY = "firtsScreen";

    /**
     * Get First Screen from shared preferences
     * @param context
     * @return
     */
    public Activities getFirstScreen(Context context) {
        String defaultValue = Activities.BODOVNA_LISTA.name();
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String firstScreenName = sharedPreferences.getString(_FIRST_SCREEN_KEY, defaultValue);
        Activities activities = Activities.valueOf(firstScreenName);
        return activities;
    }

    /**
     * Set First Screen in shared preferences
     * @param activities
     * @param context
     * @return
     */
    public Activities setFirstScreen(Activities activities, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(_FIRST_SCREEN_KEY, activities.name());
        editor.commit();
        return getFirstScreen(context);
    }

    /**
     * Check if Shared Preferences is set
     * @param context
     * @param initialisationKey
     * @return
     */
    public boolean isSharedPreferencesSet(Context context, String initialisationKey) {
        boolean defaultValue = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        try {
            boolean isSet = sharedPreferences.getBoolean(initialisationKey, defaultValue);
            return isSet;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * initialise Shared PReferences
     * @param activities
     * @param context
     * @param initialisationKey
     * @return
     */
    public Activities initialiseSharePreferences(Activities activities, Context context, String initialisationKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(initialisationKey, true);
        editor.commit();
        return setFirstScreen(activities,context);
    }

}
