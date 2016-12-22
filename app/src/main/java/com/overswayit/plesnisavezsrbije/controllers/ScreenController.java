package com.overswayit.plesnisavezsrbije.controllers;

import android.content.Context;

import com.overswayit.plesnisavezsrbije.domain.enums.Activities;
import com.overswayit.plesnisavezsrbije.domain.preferences.ScreenPreferences;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class ScreenController {

    private ScreenPreferences screenPreferences;

    public ScreenController() {
        screenPreferences = new ScreenPreferences();
    }

    /**
     * Get First Screen
     * @param context
     * @return
     */
    public Activities getFirstScreen(Context context) {
        return screenPreferences.getFirstScreen(context);
    }

    /**
     * Set First Screen
     * @param activities
     * @param context
     * @return
     */
    public Activities setFirstScreen(Activities activities, Context context) {
        return screenPreferences.setFirstScreen(activities,context);
    }

    /**
     * Check if shared preferences is set
     * @param initialisationKey
     * @param context
     * @return
     */
    public boolean isSharedPreferencesSet(String initialisationKey, Context context) {
         return screenPreferences.isSharedPreferencesSet(context, initialisationKey);
    }

    /**
     * Initialise Shared Preferences
     * @param activities
     * @param context
     * @param initialisationKey
     * @return
     */
    public Activities initialiseSharedPreferences(Activities activities, Context context, String initialisationKey) {
            return screenPreferences.initialiseSharePreferences(activities,context,initialisationKey);
    }

}
