package com.overswayit.plesnisavezsrbije.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.controllers.MainController;
import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterType;
import com.overswayit.plesnisavezsrbije.listeners.FilterCheckBoxListener;

/**
 * Created by Rivendell on 12/7/2016.
 */
public class FilterAdapter extends ArrayAdapter {

    private FilterType filterType;
    private Context context;

    public FilterAdapter(Context context, int resource, FilterType filterType) {
        super(context, resource);
        this.filterType = filterType;
        this.context = context;
    }

    @Override
    public int getCount() {
        switch (filterType) {
            case CATEGORY:
                return MainController.getInstance().getCheckedFilterCategories(getContext()).size();
            case CLASS:
                return MainController.getInstance().getCheckedFilterClass(getContext()).size();
            default:
                return 0;
        }
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        switch (filterType) {
            case CATEGORY:
                return MainController.getInstance().getCheckedFilterCategories(getContext()).get(position);
            case CLASS: return MainController.getInstance().getCheckedFilterClass(getContext()).get(position);
            default:
                return null;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(getContext().getResources().getLayout(R.layout.custom_list_item_dialog_filter), parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textViewDialogFilterListItem);
        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxDialogFilterListItem);

        FilterCheckBox filterCheckBox;

        switch (filterType) {
            case CATEGORY:
                filterCheckBox = MainController.getInstance().getCheckedFilterCategories(getContext()).get(position);
                break;
            case CLASS: filterCheckBox = MainController.getInstance().getCheckedFilterClass(getContext()).get(position);
                break;
        }

        if (filterType == FilterType.CATEGORY) {
            filterCheckBox = MainController.getInstance().getCheckedFilterCategories(getContext()).get(position);
        }
        else {
            filterCheckBox = MainController.getInstance().getCheckedFilterClass(getContext()).get(position);
        }


        textView.setText(filterCheckBox.getKey().name());
        checkBox.setChecked(filterCheckBox.isChecked());
        checkBox.setOnClickListener(new FilterCheckBoxListener(filterType, position, getContext()));

        return convertView;
    }
}
