package com.overswayit.plesnisavezsrbije.listeners;

import android.content.Context;
import android.view.View;

import com.overswayit.plesnisavezsrbije.controllers.MainController;
import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterType;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class FilterCheckBoxListener implements View.OnClickListener {

    private FilterType filterType;
    private int position;
    private Context context;

    public FilterCheckBoxListener(FilterType filterType, int position, Context context) {
        this.filterType = filterType;
        this.position = position;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (filterType) {
            case CATEGORY:
                MainController.getInstance().setTempraryCheckedFilterCategories(
                        new FilterCheckBox(MainController.getInstance().getCheckedFilterCategories(context).get(position).getKey(),
                                !MainController.getInstance().getCheckedFilterCategories(context).get(position).isChecked()));
                break;
            case CLASS:
                MainController.getInstance().setTempraryCheckedFilterClass(
                        new FilterCheckBox(MainController.getInstance().getCheckedFilterClass(context).get(position).getKey(),
                                !MainController.getInstance().getCheckedFilterClass(context).get(position).isChecked()));
        }
    }
}
