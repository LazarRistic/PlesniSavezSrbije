package com.overswayit.plesnisavezsrbije.listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.overswayit.plesnisavezsrbije.domain.DanceCouple;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/4/2016.
 */

public class FragmentListItemListener implements AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<DanceCouple> danceCouples;

    public FragmentListItemListener(Context context, ArrayList<DanceCouple> danceCouples) {
        this.context = context;
        this.danceCouples = danceCouples;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //ToDo: Go to DanceCouple activity
        //Intent intent = new Intent()
        DanceCouple danceCouple = danceCouples.get(position);
        Toast.makeText(context, danceCouple.getMale() + " - " + danceCouple.getFemale(), Toast.LENGTH_SHORT).show();
    }
}
