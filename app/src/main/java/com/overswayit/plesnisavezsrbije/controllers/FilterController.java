package com.overswayit.plesnisavezsrbije.controllers;

import android.content.Context;

import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterClassNames;
import com.overswayit.plesnisavezsrbije.domain.preferences.FilterCategoryPreferences;
import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.preferences.FilterClassPreferences;
import com.overswayit.plesnisavezsrbije.domain.temporary.FilterTemporaryCheckBoxes;
import com.overswayit.plesnisavezsrbije.initialisation.data.FilterCategoryCheckBoxesData;
import com.overswayit.plesnisavezsrbije.initialisation.data.FilterClassCheckBoxesData;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/6/2016.
 */
public class FilterController {

    private FilterCategoryPreferences filterCategoryPreferences;
    private FilterClassPreferences filterClassPreferences;

    private FilterCategoryCheckBoxesData filterCategoryCheckBoxes;
    private FilterClassCheckBoxesData filterClassCheckBoxes;

    private FilterTemporaryCheckBoxes filterTemporaryCheckBoxes;

    public FilterController() {
        filterCategoryPreferences = new FilterCategoryPreferences();
        filterClassPreferences = new FilterClassPreferences();
        filterCategoryCheckBoxes = new FilterCategoryCheckBoxesData();
        filterClassCheckBoxes = new FilterClassCheckBoxesData();
    }

    public ArrayList<FilterCheckBox> getCategoryCheckBoxes(Context context) {
        return filterCategoryPreferences.getCategoryCheckBoxes(context);
    }

    public ArrayList<FilterCheckBox> setCategoryCheckBoxes(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        return filterCategoryPreferences.setCategoryCheckBoxes(checkBoxes, context);
    }

    public ArrayList<FilterCheckBox> getClassCheckBoxes(Context context) {
        return filterClassPreferences.getClassCheckBoxes(context);
    }

    public ArrayList<FilterCheckBox> setClassCheckBoxes(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        return filterClassPreferences.setClassCheckBoxes(checkBoxes, context);
    }

    /**
     * Checking if Shared Preferences is Initiated in Filter Category and Class
     * @param initialisationKey
     * @param context
     * @return
     */
    public boolean isSharedPreferencesSet(String initialisationKey, Context context) {
        //ToDo: Put FilterClass
        if (filterCategoryPreferences.isSharedPreferencesSet(initialisationKey, context) && filterClassPreferences.isSharedPreferencesSet(initialisationKey, context) ) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Initialise Shared Preferences for Category and Class on Filter
     * @param initialisationKey
     * @param context
     */
    public void initialiseSharedPreferences(String initialisationKey, Context context) {
        if (filterCategoryPreferences.isSharedPreferencesSet(initialisationKey, context)) {
            filterCategoryPreferences.initialiseSharedPreferences(filterCategoryCheckBoxes.getCheckBoxes(), context, initialisationKey);
        }
        if (filterClassPreferences.isSharedPreferencesSet(initialisationKey, context)) {
            filterClassPreferences.inistialiseSharedPreferences(filterClassCheckBoxes.getCheckBoxes(),context,initialisationKey);
        }
    }

    public void fillTemporaryCheckBoxes(Context context){
        filterTemporaryCheckBoxes = new FilterTemporaryCheckBoxes(getCategoryCheckBoxes(context), getClassCheckBoxes(context));
    }

    public void setTemporaryCategoryCheckBoxe(FilterCheckBox tempraryCheckedFilter) {
        filterTemporaryCheckBoxes.setTemporaryCategoryCheckBox((FilterCategoryNames) tempraryCheckedFilter.getKey(), tempraryCheckedFilter.isChecked());
    }

    public void setTemporaryClassCheckBoxe(FilterCheckBox tempraryCheckedFilter) {
        filterTemporaryCheckBoxes.setTemporaryClassCheckBox((FilterClassNames) tempraryCheckedFilter.getKey(), tempraryCheckedFilter.isChecked());
    }

    public void cancleTemporaryCheckBoxes(Context context) {
        filterTemporaryCheckBoxes.setFilterCategoryCheckBoxes(getCategoryCheckBoxes(context));
        filterTemporaryCheckBoxes.setFilterClassCheckBoxes(getClassCheckBoxes(context));
    }

    public void updateCheckBoxes(Context context) {
        setCategoryCheckBoxes(filterTemporaryCheckBoxes.getFilterCategoryCheckBoxes(), context);
        setClassCheckBoxes(filterTemporaryCheckBoxes.getFilterClassCheckBoxes(), context);
    }
}
