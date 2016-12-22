package com.overswayit.plesnisavezsrbije.properties;

import com.overswayit.plesnisavezsrbije.domain.enums.List;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/5/2016.
 */

public class MainProperties {

    /**
     * Key for initialisation shared preferences
     */
    public final static String INITIALISATION_KEY = "key_for_initialisation_shared_preferences";

    private ArrayList<List> ENUMS_POINT_LIST;
    private ArrayList<List> ENUMS_RATEING_LIST;
    private ArrayList<List> ENUMS_FAVORITES_LIST;

    public static ArrayList<List> getEnumsPointList() {
        ArrayList<List> ENUMS_POINT_LIST = new ArrayList<>();
        ENUMS_POINT_LIST.add(List.PointLatinoList);
        ENUMS_POINT_LIST.add(List.PointStandardList);
        return ENUMS_POINT_LIST;
    }

    public static ArrayList<List> getEnumsRateingList() {
        ArrayList<List> ENUMS_RATEING_LIST = new ArrayList<>();
        ENUMS_RATEING_LIST.add(List.RatingLatinoList);
        ENUMS_RATEING_LIST.add(List.RatingStandardList);
        ENUMS_RATEING_LIST.add(List.Rating10DanceList);
        return ENUMS_RATEING_LIST;
    }

    public static ArrayList<List> getEnumFavoritesList() {
        ArrayList<List> ENUMS_FAVORITES_LIST = new ArrayList<>();
        ENUMS_FAVORITES_LIST.add(List.FavoritesPointList);
        ENUMS_FAVORITES_LIST.add(List.FavoritesRatingList);
        return ENUMS_FAVORITES_LIST;
    }
}
