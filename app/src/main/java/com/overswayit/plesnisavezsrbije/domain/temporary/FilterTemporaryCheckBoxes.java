package com.overswayit.plesnisavezsrbije.domain.temporary;

import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterClassNames;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class FilterTemporaryCheckBoxes {

    private ArrayList<FilterCheckBox> filterCategoryCheckBoxes;
    private ArrayList<FilterCheckBox> filterClassCheckBoxes;

    /**
     *
     * @param filterCategoryCheckBoxes
     * @param filterClassCheckBoxes
     */
    public FilterTemporaryCheckBoxes(ArrayList<FilterCheckBox> filterCategoryCheckBoxes, ArrayList<FilterCheckBox> filterClassCheckBoxes) {
        this.filterCategoryCheckBoxes = filterCategoryCheckBoxes;
        this.filterClassCheckBoxes = filterClassCheckBoxes;
    }

    public void setTemporaryCategoryCheckBox(FilterCategoryNames checkBox, boolean isChecked) {
        for(FilterCheckBox box: filterCategoryCheckBoxes) {
            if (box.getKey().equals(checkBox)) {
                box.setChecked(isChecked);
            }
        }
    }

    public void setTemporaryClassCheckBox(FilterClassNames checkBox, boolean isChecked) {
        for(FilterCheckBox box: filterClassCheckBoxes) {
            if (box.getKey().equals(checkBox)) {
                box.setChecked(isChecked);
            }
        }
    }

    public ArrayList<FilterCheckBox> getFilterCategoryCheckBoxes() {
        return filterCategoryCheckBoxes;
    }

    public ArrayList<FilterCheckBox> getFilterClassCheckBoxes() {
        return filterClassCheckBoxes;
    }

    public void setFilterCategoryCheckBoxes(ArrayList<FilterCheckBox> filterCategoryCheckBoxes) {
        this.filterCategoryCheckBoxes = filterCategoryCheckBoxes;
    }

    public void setFilterClassCheckBoxes(ArrayList<FilterCheckBox> filterClassCheckBoxes) {
        this.filterClassCheckBoxes = filterClassCheckBoxes;
    }
}
