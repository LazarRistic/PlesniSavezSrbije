package com.overswayit.plesnisavezsrbije.controllers;

import com.overswayit.plesnisavezsrbije.database.StringBroker;
import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;
import com.overswayit.plesnisavezsrbije.domain.enums.List;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/12/2016.
 */

public class DataBaseController {

    private StringBroker dbBroker;

    public DataBaseController() {
        dbBroker = new StringBroker();
    }

    public ArrayList<DanceCouple> getDanceCouples(FilterCategoryNames category, List list) {
        String cat = null;
        switch (category) {
            case PIONIRI:
                cat = "PIO";
                break;
            case MLADJI_OMLADINCI:
                cat = "MLO";
                break;
            case OMLADINCI:
                cat = "OML";
                break;
            case STARIJI_OMLADINCI:
                cat = "STO";
                break;
            case SENIORI:
                cat = "SEN";
                break;
        }
        return dbBroker.getDanceCouples(cat, list);

    }

    public ArrayList<DanceCouple> getDanceCouples(FilterCategoryNames category) {
        String cat = null;
        switch (category) {
            case PIONIRI:
                cat = "PIO";
                break;
            case MLADJI_OMLADINCI:
                cat = "MLO";
                break;
            case OMLADINCI:
                cat = "OML";
                break;
            case STARIJI_OMLADINCI:
                cat = "STO";
                break;
            case SENIORI:
                cat = "SEN";
                break;
        }
        return dbBroker.getDanceCouples(cat);
    }

    public ArrayList<DanceCouple> getDanceCouples() {
        return dbBroker.getAllDanceCouples();
    }
}
