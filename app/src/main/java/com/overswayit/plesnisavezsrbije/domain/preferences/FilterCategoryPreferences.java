package com.overswayit.plesnisavezsrbije.domain.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/6/2016.
 */
public class FilterCategoryPreferences {

    /**
     * Key for Preferences
     */
    private final String _PREFERENCE_KEY = "overswayit_categories_preferences_key";

    /**
     * Write Category CheckBoxes in Shared Preferences
     * @param checkBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> setCategoryCheckBoxes(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (FilterCheckBox checkBox :
                checkBoxes) {
            editor.putBoolean(checkBox.getKey().name(), checkBox.isChecked());
        }
        editor.commit();
        return  getCategoryCheckBoxes(context);
    }

    /**
     * Get all Category CheckBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> getCategoryCheckBoxes(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        ArrayList<FilterCheckBox> checkBoxes = new ArrayList<>();
        for (FilterCategoryNames key :
                FilterCategoryNames.values()) {
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
    private FilterCheckBox getFilterCheckBox(SharedPreferences sharedPreferences, FilterCategoryNames key) {
        boolean defaultValue = true;
        return new FilterCheckBox(key, sharedPreferences.getBoolean(key.name(), defaultValue));
    }

    /**
     * Check if Shared Preferences is set
     * @param inistialisationKey
     * @param context
     * @return
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
     * @param initialKey
     * @return
     */
    public ArrayList<FilterCheckBox> initialiseSharedPreferences(ArrayList<FilterCheckBox> checkBoxes, Context context, String initialKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(initialKey, true);
        editor.commit();
        return setCategoryCheckBoxes(checkBoxes,context);
    }


}
