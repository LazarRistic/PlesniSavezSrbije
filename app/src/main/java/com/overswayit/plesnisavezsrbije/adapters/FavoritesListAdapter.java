package com.overswayit.plesnisavezsrbije.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.enums.List;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/8/2016.
 */

public class FavoritesListAdapter extends ArrayAdapter<DanceCouple> {

    private int couple_id;
    private int layout;
    private ArrayList<DanceCouple> danceCouples;
    private List list;
    private Context context;

    public FavoritesListAdapter(Context context, ArrayList<DanceCouple> resource, int layout, List list) {
        super(context, 0, resource);
        couple_id = 0;
        this.context = context;
        this.layout = layout;
        this.danceCouples = resource;
        this.list = list;
    }

    public ArrayList<DanceCouple> getDanceCouples() {
        return danceCouples;
    }

    public void setDanceCouples(ArrayList<DanceCouple> danceCouples) {
        this.danceCouples = danceCouples;
    }

    @Nullable
    @Override
    public DanceCouple getItem(int position) {
        return getDanceCouples().get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        }
        renderGUI(position, convertView);

        return convertView;
    }

    private void renderGUI(int position, View convertView) {
        DanceCouple danceCouple = getItem(position);
        View firstView = convertView.findViewById(R.id.first_included_layout);
        View secondView = convertView.findViewById(R.id.second_included_layout);
        View thirdView = convertView.findViewById(R.id.third_included_layout);

        TextView textView = (TextView) convertView.findViewById(R.id.textView_couple_names_layout);
        textView.setText(danceCouple.getMale() + " - " + danceCouple.getFemale());

        switch (list) {
            case FavoritesPointList:
                fillView(firstView, danceCouple.getDanceClassLA(), "LATINO" , danceCouple.getPointsLA());
                fillView(secondView, danceCouple.getDanceClassST(), "STANDARD" , danceCouple.getPointsST());
                thirdView.setVisibility(View.GONE);
                break;
            case FavoritesRatingList:
                fillView(firstView, danceCouple.getRejtingLatinoPlace(), "LATINO" , danceCouple.getRejtingLatinoPoints());
                fillView(secondView, danceCouple.getRejtingStandardPlace(), "STANDARD" , danceCouple.getRejtingStandardPoints());
                fillView(thirdView, danceCouple.getRejtingKombinacijaPlace(), "TEN DANCE" , danceCouple.getRejtingKombinacijaPoints());
                break;
        }
    }

    private void fillView(View view, String danceClassOrPlace, String danceType, String points) {
        TextView textViewPlaceOrClass = (TextView) view.findViewById(R.id.textView_class_letter_or_place);
        TextView textViewDanceType = (TextView) view.findViewById(R.id.textView_dance_type);
        TextView textViewPoints = (TextView) view.findViewById(R.id.textView_points);

        textViewPlaceOrClass.setText(danceClassOrPlace);
        textViewDanceType.setText(danceType);
        textViewPoints.setText(points);
    }
}
