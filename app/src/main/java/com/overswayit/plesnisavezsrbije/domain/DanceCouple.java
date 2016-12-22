package com.overswayit.plesnisavezsrbije.domain;

import java.io.Serializable;

/**
 * Created by Lazar on 2/3/2016.
 */
public class DanceCouple implements Serializable {

    private int id;
    private String pointsLA;
    private String pointsST;
    private String male;
    private String female;
    private String club;
    private String category;
    private String danceClassLA;
    private String danceClassST;
    private boolean following = false;
    private String rejtingLatinoPoints;
    private String rejtingStandardPoints;
    private String rejtingKombinacijaPoints;
    private String rejtingLatinoPlace;
    private String rejtingStandardPlace;
    private String rejtingKombinacijaPlace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointsLA() {
        return pointsLA;
    }

    public void setPointsLA(String pointsLA) {
        this.pointsLA = pointsLA;
    }

    public String getPointsST() {
        return pointsST;
    }

    public void setPointsST(String pointsST) {
        this.pointsST = pointsST;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = setLatineLatter(male);
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = setLatineLatter(female);
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = setLatineLatter(club);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = localiseCategory(category);
    }

    public String getDanceClassLA() {
        return danceClassLA;
    }

    public void setDanceClassLA(String danceClassLA) {
        this.danceClassLA = danceClassLA;
    }

    public String getDanceClassST() {
        return danceClassST;
    }

    public void setDanceClassST(String danceClassST) {
        this.danceClassST = danceClassST;
    }

    public String getRejtingLatinoPoints() {
        return rejtingLatinoPoints;
    }

    public void setRejtingLatinoPoints(String rejtingLatinoPoints) {
        this.rejtingLatinoPoints = rejtingLatinoPoints;
    }

    public String getRejtingStandardPoints() {
        return rejtingStandardPoints;
    }

    public void setRejtingStandardPoints(String rejtingStandardPoints) {
        this.rejtingStandardPoints = rejtingStandardPoints;
    }

    public String getRejtingKombinacijaPoints() {
        return rejtingKombinacijaPoints;
    }

    public void setRejtingKombinacijaPoints(String rejtingKombinacijaPoints) {
        this.rejtingKombinacijaPoints = rejtingKombinacijaPoints;
    }

    public String getRejtingLatinoPlace() {
        return rejtingLatinoPlace;
    }

    public void setRejtingLatinoPlace(String rejtingLatinoPlace) {
        this.rejtingLatinoPlace = rejtingLatinoPlace;
    }

    public String getRejtingStandardPlace() {
        return rejtingStandardPlace;
    }

    public void setRejtingStandardPlace(String rejtingStandardPlace) {
        this.rejtingStandardPlace = rejtingStandardPlace;
    }

    public String getRejtingKombinacijaPlace() {
        return rejtingKombinacijaPlace;
    }

    public void setRejtingKombinacijaPlace(String rejtingKombinacijaPlace) {
        this.rejtingKombinacijaPlace = rejtingKombinacijaPlace;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public int isFollowingInteger() {
        if (following) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setFollowingInteger(int following) {
        if (following == 0) {
            this.following = false;
        } else {
            this.following = true;
        }
    }

    @Override
    public String toString() {
        return "DanceCouple{" +
                "id=" + id +
                ", pointsLA='" + pointsLA + '\'' +
                ", pointsST='" + pointsST + '\'' +
                ", male='" + male + '\'' +
                ", female='" + female + '\'' +
                ", club='" + club + '\'' +
                ", category='" + category + '\'' +
                ", danceClassLA='" + danceClassLA + '\'' +
                ", danceClassST='" + danceClassST + '\'' +
                ", following=" + following +
                ", rejtingLatinoPoints='" + rejtingLatinoPoints + '\'' +
                ", rejtingStandardPoints='" + rejtingStandardPoints + '\'' +
                ", rejtingKombinacijaPoints='" + rejtingKombinacijaPoints + '\'' +
                ", rejtingLatinoPlace='" + rejtingLatinoPlace + '\'' +
                ", rejtingStandardPlace='" + rejtingStandardPlace + '\'' +
                ", rejtingKombinacijaPlace='" + rejtingKombinacijaPlace + '\'' +
                '}';
    }

    private String localiseCategory(String parameter) {
        parameter = parameter.replace("SEN", "Senior");
        parameter = parameter.replace("PIO", "Pionir");
        parameter = parameter.replace("MLO", "Mlađi Omladinac");
        parameter = parameter.replace("STO", "Stariji Omladinac");
        parameter = parameter.replace("OML", "Omladinac");
        return parameter;
    }

    private String setLatineLatter(String params) {

        /*
        params = params.replace("\\u00c3\\u00a6", "ć");
        params = params.replace("\\u00c3\\u0086", "Ć");
        params = params.replace("\\u00c2\\u009e", "ž");
        params = params.replace("\\u00c2\\u008e", "Ž");
        params = params.replace("\\u00c2\\u009a", "š");
        params = params.replace("\\u00c2\\u008a", "Š");
        params = params.replace("\\u00c3\\u00b0", "đ");
        params = params.replace("\\u00c3\\u0090", "Đ");
        params = params.replace("\\u00c3\\u00a8", "č");
        params = params.replace("\\u00c3\\u0088", "Č");
        */

        params = params.replace("Ã\u0086", "Ć");
        params = params.replace("Ã¦", "ć");
        params = params.replace("Â\u008A", "Š");
        params = params.replace("Â\u009A", "š");
        params = params.replace("Â\u008E", "Ž");
        params = params.replace("Â\u009E", "ž");
        params = params.replace("Ã\u0090", "Đ");
        params = params.replace("Ã°", "đ");
        params = params.replace("Ã\u0088", "Č");
        params = params.replace("Ã¨", "č");





        return params;
    }
}
