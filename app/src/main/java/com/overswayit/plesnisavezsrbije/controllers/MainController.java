package com.overswayit.plesnisavezsrbije.controllers;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;

import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.Activities;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;
import com.overswayit.plesnisavezsrbije.domain.enums.List;
import com.overswayit.plesnisavezsrbije.fragments.dialog.FilterDialogFragment;
import com.overswayit.plesnisavezsrbije.properties.MainProperties;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/5/2016.
 */
public class MainController {

    //Controllers
    private FilterController filterController;
    private ScreenController screenController;
    private DataBaseController dataBaseController;

    private Integer selectedScreen;

    private static MainController ourInstance = new MainController();

    public static MainController getInstance() {
        return ourInstance;
    }

    private MainController() {
        filterController = new FilterController();
        screenController = new ScreenController();
        dataBaseController = new DataBaseController();
        selectedScreen = null;
    }

    /**
     * Initialise SharedPreferences, Fill temporary CheckBoxes
     * @param context
     */
    public void firstInitialisation(Context context) {
        if (!screenController.isSharedPreferencesSet(MainProperties.INITIALISATION_KEY,context)) {
            screenController.initialiseSharedPreferences(Activities.BODOVNA_LISTA,context, MainProperties.INITIALISATION_KEY);
        }
        if (!filterController.isSharedPreferencesSet(MainProperties.INITIALISATION_KEY,context)) {
            filterController.initialiseSharedPreferences(MainProperties.INITIALISATION_KEY,context);
        }
        filterController.fillTemporaryCheckBoxes(context);
    }

    /**
     * Get Frst screen as Enum
     * @param context
     * @return
     */
    public Activities getFirstScreen(Context context) {
        return screenController.getFirstScreen(context);
    }

    public void setFirstScreen(Activities activities, Context context) {
        screenController.setFirstScreen(activities,context);
    }

    /**
     * Get Checked Filter Categories
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> getCheckedFilterCategories(Context context) {
        return filterController.getCategoryCheckBoxes(context);
    }

    /**
     * Set Checked Filter Categories
     * @param checkBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> setCheckedFilterCategories(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        return filterController.setCategoryCheckBoxes(checkBoxes,context);
    }

    /**
     * Get Checked Filter Classes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> getCheckedFilterClass(Context context) {
        return  filterController.getClassCheckBoxes(context);
    }

    /**
     * Set Checked Filter Classes
     * @param checkBoxes
     * @param context
     * @return
     */
    public ArrayList<FilterCheckBox> setCheckedFilterClass(ArrayList<FilterCheckBox> checkBoxes, Context context) {
        return filterController.setClassCheckBoxes(checkBoxes,context);
    }

    /**
     * Set Temporary Check Box for Category
     * @param tempraryCheckedFilterCategories
     */
    public void setTempraryCheckedFilterCategories(FilterCheckBox tempraryCheckedFilterCategories ) {
        filterController.setTemporaryCategoryCheckBoxe(tempraryCheckedFilterCategories);
    }

    /**
     * Set Temporary Check Box for Class
     * @param tempraryCheckedFilterCategories
     */
    public void setTempraryCheckedFilterClass(FilterCheckBox tempraryCheckedFilterCategories ) {
        filterController.setTemporaryClassCheckBoxe(tempraryCheckedFilterCategories);
    }

    public void updateCheckBoxes(Context context) {
        filterController.updateCheckBoxes(context);
        //ToDo: UpdateFragments
    }

    public void cancleTemporaryCheckBoxes(Context context) {
        filterController.cancleTemporaryCheckBoxes(context);
    }

    /**
     * Make Filter Dialog appear
     * @param fragmentManager
     */
    public void showFilterDialog(FragmentManager fragmentManager) {
        DialogFragment dialogFragment = new FilterDialogFragment();
        dialogFragment.show(fragmentManager, "FilterDialogFragment");
    }

    public Integer getSelectedScreen() {
        return selectedScreen;
    }

    public void setSelectedScreen(Integer id) {
        selectedScreen = id;
    }

    public ArrayList<DanceCouple> getDanceCouples(FilterCategoryNames category) {
        return dataBaseController.getDanceCouples(category);
    }

    public ArrayList<DanceCouple> getAllDanceCouples() {
        return dataBaseController.getDanceCouples();
    }

    public ArrayList<DanceCouple> getDanceCouples(FilterCategoryNames category, List list) {
        return dataBaseController.getDanceCouples(category, list);
    }
}
