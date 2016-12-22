package com.overswayit.plesnisavezsrbije.fragments.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.adapters.FilterAdapter;
import com.overswayit.plesnisavezsrbije.controllers.MainController;
import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterType;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/6/2016.
 */

public class FilterDialogFragment extends DialogFragment {

    private ArrayList<FilterCheckBox> filterCategoryCheckBoxes;
    private ArrayList<FilterCheckBox> filterClassCheckBoxes;

    private ListView categoryListView;
    private ListView classListView;

    private FilterAdapter filterAdapterCategory;
    private FilterAdapter filterAdapterClass;

    public interface FilterDialogListiner {
        public void onDialogPositivClick(DialogFragment dialog, Context context);
        public void onDialogNegativeClick(DialogFragment dialog, Context context);
    }

    private FilterDialogListiner listiner;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        categoryListView = (ListView) activity.findViewById(R.id.listView_filter_category) ;
        classListView = (ListView) activity.findViewById(R.id.listView_filter_class) ;
        filterAdapterCategory = new FilterAdapter(activity, R.layout.custom_list_item_dialog_filter, FilterType.CATEGORY);
        filterAdapterClass = new FilterAdapter(activity, R.layout.custom_list_item_dialog_filter, FilterType.CLASS);

        try {
            listiner = (FilterDialogListiner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FilterDialogListiner");
        }
        //ToDo: Controller.getInstance().updateFiltersOnDialogLunch();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        filterCategoryCheckBoxes = new ArrayList<>();
        filterClassCheckBoxes = new ArrayList<>();

        filterCategoryCheckBoxes = MainController.getInstance().getCheckedFilterCategories(getActivity());
        filterClassCheckBoxes = MainController.getInstance().getCheckedFilterClass(getActivity());

        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_filter_dialog_list, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(view).setMessage("Filter").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Send the positive button event back to the host activity
                //ToDo:
                listiner.onDialogPositivClick(FilterDialogFragment.this, getActivity());
            }
        }) .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Send the negative button event back to the host activity
                listiner.onDialogNegativeClick(FilterDialogFragment.this, getActivity());
            }
        });

        categoryListView = (ListView) view.findViewById(R.id.listView_filter_category) ;
        classListView = (ListView) view.findViewById(R.id.listView_filter_class) ;
        filterAdapterCategory = new FilterAdapter(getActivity(), R.layout.custom_list_item_dialog_filter, FilterType.CATEGORY);
        filterAdapterClass = new FilterAdapter(getActivity(), R.layout.custom_list_item_dialog_filter, FilterType.CLASS);

        categoryListView.setAdapter(filterAdapterCategory);
        classListView.setAdapter(filterAdapterClass);
        return builder.create();
    }
}
