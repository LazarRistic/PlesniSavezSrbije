package com.overswayit.plesnisavezsrbije.domain.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterClassNames;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/7/2016.
 */
public class FilterClassPreferences {

    /**
     * Key for Preferences
     */
    private final String _PREFERENCE_KEY = "overswayit_class_preferences_key";

    /**
     * Write Class CheckBoxes in Shared Preferences
     * @param checkBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> setClassCheckBoxes(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (FilterCheckBox checkBox :
                checkBoxes) {
            editor.putBoolean(checkBox.getKey().name(), checkBox.isChecked());
        }
        editor.commit();
        return getClassCheckBoxes(context);
    }

    /**
     * Get all Class CheckBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> getClassCheckBoxes(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, context.MODE_PRIVATE);
        ArrayList<FilterCheckBox> checkBoxes = new ArrayList<>();
        for (FilterClassNames key: FilterClassNames.values()) {
            checkBoxes.add(getFilterCheckBox(sharedPreferences, key));
        }
        return checkBoxes;
    }

    /**
     * Geting FilterCheckBox from SharedPreferences
     * @param sharedPreferences
     * @param key
     * @return
     */
    private FilterCheckBox getFilterCheckBox(SharedPreferences sharedPreferences, FilterClassNames key) {
        boolean defaultValue = true;
        return new FilterCheckBox(key, sharedPreferences.getBoolean(key.name(), defaultValue));
    }

    /**
     * Check if Shared Preferences is set
     * @param inistialisationKey
     * @param context
     * @return boolean
     */
    public boolean isSharedPreferencesSet(String inistialisationKey, Context context) {
        boolean defaultValue = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        try {
            boolean isSet = sharedPreferences.getBoolean(inistialisationKey, defaultValue);
            return isSet;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * When first time app is runed call this method to initialise shared preferences
     * @param checkBoxes
     * @param context
     * @param inistialisationKey
     * @return
     */
    public ArrayList<FilterCheckBox> inistialiseSharedPreferences(ArrayList<FilterCheckBox> checkBoxes, Context context, String inistialisationKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(inistialisationKey, true);
        editor.commit();
        return setClassCheckBoxes(checkBoxes,context);
    }
}
