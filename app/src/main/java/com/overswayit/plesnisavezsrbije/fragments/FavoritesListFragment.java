package com.overswayit.plesnisavezsrbije.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.adapters.FavoritesListAdapter;
import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.enums.List;
import com.overswayit.plesnisavezsrbije.listeners.CustomSwipeToRefrhListiner;
import com.overswayit.plesnisavezsrbije.listeners.FragmentListItemListener;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/8/2016.
 */

public class FavoritesListFragment extends Fragment {

    private FavoritesListAdapter adapter;
    private List list;
    private ListView listView;
    private ArrayList<DanceCouple> danceCouples;
    private SwipeRefreshLayout swipeContainer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "list";
    private static final String ARG_DANCE_COUPLES = "param2";

    private RateingListFragment.OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    private int mList;

    public FavoritesListFragment() {
        //Needs an empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = (List) List.values()[getArguments().getInt(ARG_LIST)];
        danceCouples = new ArrayList<DanceCouple>();
        //ToDo: danceCouples = Controller.getInstace().getDanceCouples(list);
        adapter = new FavoritesListAdapter(getContext(), danceCouples, R.layout.custom_favorites_list_item_layout, list);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_layout, container, false);
        listView = (ListView) mainView.findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
        listView.setOnItemClickListener(new FragmentListItemListener(getContext(), danceCouples));

        //ToDo: If list is empty do something

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //For swipe down to refresh List
        swipeToRefreshInitialiser(mainView);

        return mainView;
    }

    /**
     * Checking if there is database on Server has changed
     * @param mainView
     */
    private void swipeToRefreshInitialiser(View mainView) {
        //Swipe To Refresh
        swipeContainer = (SwipeRefreshLayout) mainView.findViewById(R.id.card_view_layout_id).findViewById(R.id.swipeContainer);
        //Setip refres listener wich triggers new data loading
        swipeContainer.setOnRefreshListener(new CustomSwipeToRefrhListiner(adapter, swipeContainer));

        //Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark);
    }

    public static FavoritesListFragment newInstancE(int mList) {
        FavoritesListFragment fragment = new FavoritesListFragment();
        Bundle args = new Bundle();
        /*
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        */
        args.putInt(ARG_LIST, mList);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RateingListFragment.OnFragmentInteractionListener) {
            mListener = (RateingListFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
