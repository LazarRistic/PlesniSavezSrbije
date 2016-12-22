package com.overswayit.plesnisavezsrbije.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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
 * Created by Rivendell on 12/3/2016.
 */
public class RateingListAdapter extends ArrayAdapter<DanceCouple> {

    private int couple_id;
    private int layout;
    private ArrayList<DanceCouple> danceCouples;
    private List list;
    private Context context;

    //Views for fillDynamicPlacesInView Method to fill
    TextView textViewClassLetter;
    TextView textViewPoints;
    //View for fillStaticPlacesInView
    TextView textViewClubName;
    TextView textViewMaleName;
    TextView textViewFemaleName;

    public RateingListAdapter(Context context, ArrayList<DanceCouple> resource, int layout, List list) {
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
        if (list == List.PointLatinoList) {
            renderGUI(position, convertView, getItem(position).getDanceClassLA(), getItem(position).getPointsLA());
        } else if(list == List.PointStandardList) {
            renderGUI(position, convertView, getItem(position).getDanceClassST(), getItem(position).getPointsST());
        } else if(list == List.RatingLatinoList) {
            renderGUI(position, convertView, getItem(position).getRejtingLatinoPlace(), getItem(position).getRejtingLatinoPoints());
            Log.d("AAAAAAAAAAAA", "LA Name: " + getItem(position).getMale());
            Log.d("AAAAAAAAAAAA", "LA Rejting: " + getItem(position).getRejtingLatinoPlace());
        } else if(list == List.RatingStandardList) {
            renderGUI(position, convertView, getItem(position).getRejtingStandardPlace(), getItem(position).getRejtingStandardPoints());
            Log.d("AAAAAAAAAAAA", "ST Name: " + getItem(position).getMale());
            Log.d("AAAAAAAAAAAA", "ST Rejting: " + getItem(position).getRejtingStandardPlace());
        } else if(list == List.Rating10DanceList) {
            renderGUI(position, convertView, getItem(position).getRejtingKombinacijaPlace(), getItem(position).getRejtingKombinacijaPoints());
            Log.d("AAAAAAAAAAAA", "KM Name: " + getItem(position).getMale());
            Log.d("AAAAAAAAAAAA", "KM Rejting: " + getItem(position).getRejtingKombinacijaPlace());
        }

        return convertView;
    }

    /**
     * To initialise gui components and fill them with values
     * @param position
     * @param convertView
     */
    private void renderGUI(int position, View convertView, String classLetter, String points) {
        DanceCouple danceCouple = getItem(position);

        //Initials views
        initialiseView(convertView);

        fillStaticPlacesInView(danceCouple);
        fillDynamicPlacesInView(classLetter, points);

    }

    private void initialiseView(View convertView) {
        //Initials views
        textViewClassLetter = (TextView) convertView.findViewById(R.id.textView_class_letter);
        textViewPoints = (TextView) convertView.findViewById(R.id.textView_points);
        textViewClubName = (TextView) convertView.findViewById(R.id.textView_club_name);
        textViewMaleName = (TextView) convertView.findViewById(R.id.textView_male_name);
        textViewFemaleName = (TextView) convertView.findViewById(R.id.textView_female_name);
    }

    private void fillStaticPlacesInView(DanceCouple danceCouple) {
        //Fill static views values
        textViewClubName.setText(danceCouple.getClub());
        textViewFemaleName.setText(danceCouple.getFemale());
        textViewMaleName.setText(danceCouple.getMale());
    }

    /**
     * Fill Class Letter and Points in View
     * @param classLetter example: I, A, B, C, D, E or for Rateing place
     * @param points
     */
    private void fillDynamicPlacesInView(String classLetter, String points) {
        textViewClassLetter.setText(classLetter);
        textViewPoints.setText(points);
    }
}
