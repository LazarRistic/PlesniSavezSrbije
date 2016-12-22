package com.overswayit.plesnisavezsrbije.domain.enums;

/**
 * Created by Rivendell on 12/4/2016.
 */

public enum List {
    PointLatinoList(0),
    PointStandardList(1),
    RatingLatinoList(2),
    RatingStandardList(3),
    Rating10DanceList(4),
    FavoritesPointList(5),
    FavoritesRatingList(6);

    private final int value;
    private List(int value) {
        this.value = value;
    }

    public int getInt() {
        return value;
    }
}
