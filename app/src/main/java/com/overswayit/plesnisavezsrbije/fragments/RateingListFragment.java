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
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.adapters.RateingListAdapter;
import com.overswayit.plesnisavezsrbije.controllers.MainController;
import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.enums.FilterCategoryNames;
import com.overswayit.plesnisavezsrbije.domain.enums.List;
import com.overswayit.plesnisavezsrbije.listeners.CustomSwipeToRefrhListiner;
import com.overswayit.plesnisavezsrbije.listeners.FragmentListItemListener;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/3/2016.
 */
public class RateingListFragment extends Fragment {

    //Adapter za punjenje fragmenta
    private RateingListAdapter adapter;

    private RateingListAdapter adapterSenior;
    private RateingListAdapter adapterStarijiOmladinac;
    private RateingListAdapter adapterOmladiniac;
    private RateingListAdapter adapterMladjiOmladinac;
    private RateingListAdapter adapterPionir;

    //Enum for getting right Array
    private List list;

    //Views
    //private ListView listView;

    private ListView listViewSeniori;
    private ListView listViewStarijiOmladinci;
    private ListView listViewOmladinci;
    private ListView listViewMladjiOmladinci;
    private ListView listViewPioniri;

    private ArrayList<DanceCouple> danceCouples;

    //Swipe to refresh
    private SwipeRefreshLayout swipeContainer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "list";
    private static final String ARG_DANCE_COUPLES = "param2";

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    private int mList;

    public RateingListFragment() {
        //Needs an empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */
        list = (List) List.values()[getArguments().getInt(ARG_LIST)];
        danceCouples = new ArrayList<DanceCouple>();
        //ToDo: danceCouples = Controller.getInstace().getDanceCouples(list);
        //danceCouples = MainController.getInstance().getAllDanceCouples();
        //adapter = new RateingListAdapter(getContext(), danceCouples, R.layout.cutom_rateing_list_item_layout, list);

        adapterSenior = new RateingListAdapter(getContext(), MainController.getInstance().getDanceCouples(FilterCategoryNames.SENIORI, list), R.layout.cutom_rateing_list_item_layout, list);
        adapterStarijiOmladinac = new RateingListAdapter(getContext(), MainController.getInstance().getDanceCouples(FilterCategoryNames.STARIJI_OMLADINCI, list), R.layout.cutom_rateing_list_item_layout, list);
        adapterOmladiniac = new RateingListAdapter(getContext(), MainController.getInstance().getDanceCouples(FilterCategoryNames.OMLADINCI, list), R.layout.cutom_rateing_list_item_layout, list);
        adapterMladjiOmladinac = new RateingListAdapter(getContext(), MainController.getInstance().getDanceCouples(FilterCategoryNames.MLADJI_OMLADINCI, list), R.layout.cutom_rateing_list_item_layout, list);
        adapterPionir = new RateingListAdapter(getContext(), MainController.getInstance().getDanceCouples(FilterCategoryNames.PIONIRI, list), R.layout.cutom_rateing_list_item_layout, list);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_layout, container, false);

        initialiseListView(mainView, listViewSeniori, FilterCategoryNames.SENIORI, list, R.id.card_view_first, adapterSenior);
        initialiseListView(mainView, listViewStarijiOmladinci, FilterCategoryNames.STARIJI_OMLADINCI, list, R.id.card_view_second, adapterStarijiOmladinac);
        initialiseListView(mainView, listViewOmladinci, FilterCategoryNames.OMLADINCI, list, R.id.card_view_third, adapterOmladiniac);
        initialiseListView(mainView, listViewMladjiOmladinci, FilterCategoryNames.MLADJI_OMLADINCI, list, R.id.card_view_fourth, adapterMladjiOmladinac);
        initialiseListView(mainView, listViewPioniri, FilterCategoryNames.PIONIRI, list, R.id.card_view_fifth, adapterPionir);

        /*
        listView = (ListView) mainView.findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
        listView.setOnItemClickListener(new FragmentListItemListener(getContext(), danceCouples));

        //ToDo: If list is empty do something

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        //For swipe down to refresh List
        swipeToRefreshInitialiser(mainView);

        return mainView;
    }

    private void initialiseListView(View mainView, ListView listView2, FilterCategoryNames categoryNames, List list, int include, RateingListAdapter adapter) {
        if (MainController.getInstance().getDanceCouples(categoryNames,list).isEmpty()) {
            mainView.findViewById(include).setVisibility(View.GONE);
        } else {
            ListView listView;
            if (categoryNames == FilterCategoryNames.SENIORI) {
                listView = listViewSeniori;
                listViewSeniori = (ListView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
                TextView textView = (TextView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.category_name);
                textView.setText(categoryNames.name());
                listViewSeniori.setOnItemClickListener(new FragmentListItemListener(getContext(), MainController.getInstance().getDanceCouples(categoryNames,list)));
                listViewSeniori.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else if (categoryNames == FilterCategoryNames.STARIJI_OMLADINCI) {
                listView = listViewStarijiOmladinci;
                listViewStarijiOmladinci = (ListView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
                TextView textView = (TextView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.category_name);
                textView.setText(categoryNames.name());
                listViewStarijiOmladinci.setOnItemClickListener(new FragmentListItemListener(getContext(), MainController.getInstance().getDanceCouples(categoryNames,list)));
                listViewStarijiOmladinci.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else if (categoryNames == FilterCategoryNames.OMLADINCI) {
                listView = listViewOmladinci;
                listViewOmladinci = (ListView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
                TextView textView = (TextView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.category_name);
                textView.setText(categoryNames.name());
                listViewOmladinci.setOnItemClickListener(new FragmentListItemListener(getContext(), MainController.getInstance().getDanceCouples(categoryNames,list)));
                listViewOmladinci.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else if (categoryNames == FilterCategoryNames.MLADJI_OMLADINCI) {
                listView = listViewMladjiOmladinci;
                listViewMladjiOmladinci = (ListView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
                TextView textView = (TextView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.category_name);
                textView.setText(categoryNames.name());
                listViewMladjiOmladinci.setOnItemClickListener(new FragmentListItemListener(getContext(), MainController.getInstance().getDanceCouples(categoryNames,list)));
                listViewMladjiOmladinci.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else if (categoryNames == FilterCategoryNames.PIONIRI) {
                listView = listViewPioniri;
                listViewPioniri = (ListView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.listView_list);
                TextView textView = (TextView) mainView.findViewById(include).findViewById(R.id.card_view_layout_id).findViewById(R.id.category_name);
                textView.setText(categoryNames.name());
                listViewPioniri.setOnItemClickListener(new FragmentListItemListener(getContext(), MainController.getInstance().getDanceCouples(categoryNames,list)));
                listViewPioniri.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        }
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

    public static RateingListFragment newInstance(int mList) {
        RateingListFragment fragment = new RateingListFragment();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
