package com.overswayit.plesnisavezsrbije.initialisation.data;

import com.overswayit.plesnisavezsrbije.domain.FilterCheckBox;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterClassNames;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class FilterClassCheckBoxesData implements InitialisationData {

    private ArrayList<FilterCheckBox> fillCheckBoxes() {
        ArrayList<FilterCheckBox> checkBoxes = new ArrayList<>();
        for (FilterClassNames names: FilterClassNames.values()) {
            checkBoxes.add(new FilterCheckBox(names, true));
        }
        return checkBoxes;
    }

    @Override
    public ArrayList<FilterCheckBox> getCheckBoxes() {
        return fillCheckBoxes();
    }
}
