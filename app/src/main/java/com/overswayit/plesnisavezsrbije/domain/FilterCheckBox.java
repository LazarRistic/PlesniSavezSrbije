package com.overswayit.plesnisavezsrbije.domain;

/**
 * Created by Rivendell on 12/7/2016.
 */

public class FilterCheckBox {

    private Enum key;
    private boolean isChecked;

    public FilterCheckBox(Enum key, Boolean isChecked) {
        this.key = key;
        this.isChecked = isChecked;
    }

    public Enum getKey() {
        return key;
    }

    public void setKey(Enum key) {
        this.key = key;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
