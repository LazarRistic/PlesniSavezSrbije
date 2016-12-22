package com.overswayit.plesnisavezsrbije;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.overswayit.plesnisavezsrbije.adapters.TabsAdapter;
import com.overswayit.plesnisavezsrbije.controllers.MainController;
import com.overswayit.plesnisavezsrbije.domain.enums.Activities;
import com.overswayit.plesnisavezsrbije.domain.enums.List;
import com.overswayit.plesnisavezsrbije.fragments.FavoritesListFragment;
import com.overswayit.plesnisavezsrbije.fragments.RateingListFragment;
import com.overswayit.plesnisavezsrbije.fragments.dialog.FilterDialogFragment;
import com.overswayit.plesnisavezsrbije.listeners.SearchViewOnQueryTextListener;
import com.overswayit.plesnisavezsrbije.listeners.customSearchViewListener;
import com.overswayit.plesnisavezsrbije.properties.MainProperties;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity implements RateingListFragment.OnFragmentInteractionListener, FilterDialogFragment.FilterDialogListiner {

    //For ViewPager
    private ViewPager viewPager;
    private TabsAdapter mTabsAdapter;

    //Fragments For ViewPager
    private RateingListFragment rateingListFragment;

    //List of Fragments
    private ArrayList<Fragment> fragments;

    //ToDo: Make it static in a special file for this example properties
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "list";

    //Navigation Drawer
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    //SearchView on Toolbar
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation of SharedPreferences, Temporary CheckBoxes...
        MainController.getInstance().firstInitialisation(getApplicationContext());

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        //For viewPager and brining first screen on
        bringFirstScreen();

    }

    /**
     * Decideing what screen to show and what fragments to put on screen
     */
    private void bringFirstScreen() {
        Activities activities = MainController.getInstance().getFirstScreen(getApplicationContext());
        summonViewPager(activities);
    }

    private ArrayList<NavigationTabBar.Model> initialiseFavoritesFragments(ArrayList<List> arrayList, ArrayList<NavigationTabBar.Model> models) {
        //Initialise Fragments and Get Models
        for (List list :
                arrayList) {
            initialiseFreagmentFavorites(list);
            models.add(createModel(list));
        }
        return models;
    }

    private FavoritesListFragment initialiseFreagmentFavorites(List list) {
        FavoritesListFragment fragment = new FavoritesListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_LIST, list.getInt());
        fragment.setArguments(bundle);
        this.fragments.add(fragment);
        return fragment;
    }

    private ArrayList<NavigationTabBar.Model> initialiseNonFavoritesFragments(ArrayList<List> arrayList, ArrayList<NavigationTabBar.Model> models) {
        //Initialise Fragments and Get Models
        for (List list :
                arrayList) {
            initialiseFreagment(list);
            models.add(createModel(list));
        }
        return models;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.filter_menu:
                MainController.getInstance().showFilterDialog(getFragmentManager());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Switch between PointsList and RateingList TabBar and Favorites
     * @param activities
     */
    private void summonViewPager(Activities activities) {

        fragments = new ArrayList<Fragment>();

        //CreatingModels for TabBar
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.clear();

        //Initialise Fragments and get Models
        switch (activities) {
            case BODOVNA_LISTA:
                models = initialiseNonFavoritesFragments(MainProperties.getEnumsPointList(), models);
                break;
            case REJTING_LISTA:
                models = initialiseNonFavoritesFragments(MainProperties.getEnumsRateingList(), models);
                break;
            case OMILJENI:
                models = initialiseFavoritesFragments(MainProperties.getEnumFavoritesList(), models);
                break;
        }

        //Set Fragments for adapter
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mTabsAdapter.setPageList(fragments);

        //seting Adapter for ViewPager
        viewPager = (ViewPager) findViewById(R.id.include_home_layout).findViewById(R.id.view_pager);
        viewPager.setAdapter(mTabsAdapter);

        initialiseTabBar(models);

        initialiseSearchViewToolBar();

        mTabsAdapter.notifyDataSetChanged();

    }

    /**
     * Creating Model for NAvigation TabBat
     * @param list
     * @return
     */
    private NavigationTabBar.Model createModel(List list) {
        switch (list) {
            case PointLatinoList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.la_nav_tab_bar),R.color.colorPrimary).title("LA").badgeTitle("NTB").build();
            case PointStandardList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.st_nav_tab_bar),R.color.colorPrimary).title("ST").badgeTitle("NTB").build();
            case RatingLatinoList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.la_nav_tab_bar),R.color.colorPrimary).title("LA").badgeTitle("NTB").build();
            case RatingStandardList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.st_nav_tab_bar),R.color.colorPrimary).title("ST").badgeTitle("NTB").build();
            case Rating10DanceList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.km_nav_tab_bar),R.color.colorPrimary).title("KM").badgeTitle("NTB").build();
            case FavoritesPointList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.bodovna_xl_lista_side_menu),R.color.colorPrimary).title("BL").badgeTitle("NTB").build();
            case FavoritesRatingList:
                return new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.rejting_xl_lista_side_menu),R.color.colorPrimary).title("RL").badgeTitle("NTB").build();
        }
        return null;
    }

    private void initialiseSearchViewToolBar() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchViewOnQueryTextListener());
        searchView.setOnSearchViewListener(new customSearchViewListener());
        searchView.setCursorDrawable(R.drawable.search_view_shape);
    }

    /**
     * Puting Models in TabBar
     * @param models
     */
    private void initialiseTabBar(ArrayList<NavigationTabBar.Model> models) {

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.include_home_layout).findViewById(R.id.ntb);

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 5);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(false);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBadgeSize(10);
        navigationTabBar.setIconSizeFraction(Float.parseFloat("0.5"));

        /*
        model.setTitle("Here some title to model");
        model.hideBadge();
        model.showBadge();
        model.toggleBadge();
        model.updateBadgeTitle("Here some title like NEW or some integer value");
         */

    }

    /**
     *Initialise fragment and sending it Enum for filtering Dance Couples
     * @param list Enum
     * @return
     */
    private RateingListFragment initialiseFreagment(List list) {
        RateingListFragment fragment = new RateingListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_LIST, list.getInt());
        fragment.setArguments(bundle);
        this.fragments.add(fragment);
        return fragment;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void setupDrawerContent(NavigationView navigationView) {
        Integer res = null;
        switch (MainController.getInstance().getFirstScreen(getApplicationContext())) {
            case BODOVNA_LISTA:
                res = R.id.nav_first_fragment;
                break;
            case REJTING_LISTA:
                res = R.id.nav_second_fragment;
                break;
            case OMILJENI:
                res = R.id.nav_third_fragment;
                break;
        }
        navigationView.setCheckedItem(res);
        MainController.getInstance().setSelectedScreen(res);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    /**
     * What happeneds when item in navigation drawer is selected
     * @param menuItem
     */
    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                summonViewPager(Activities.BODOVNA_LISTA);
                Toast.makeText(getApplicationContext(), "Bodovna Lista", Toast.LENGTH_SHORT).show();
                MainController.getInstance().setSelectedScreen(R.id.nav_first_fragment);
                menuItem.setChecked(true);
                break;
            case R.id.nav_second_fragment:
                summonViewPager(Activities.REJTING_LISTA);
                Toast.makeText(getApplicationContext(), "Rejting Lista", Toast.LENGTH_SHORT).show();
                MainController.getInstance().setSelectedScreen(R.id.nav_second_fragment);
                menuItem.setChecked(true);
                break;
            case R.id.nav_third_fragment:
                summonViewPager(Activities.OMILJENI);
                Toast.makeText(getApplicationContext(), "Omiljeni", Toast.LENGTH_SHORT).show();
                MainController.getInstance().setSelectedScreen(R.id.nav_third_fragment);
                menuItem.setChecked(true);
                break;
            case R.id.nav_choose_first_screen:
                navigationDrawerCheckItem();
                summonChooseFirstScreenAlert();
                break;
            case R.id.nav_sorting:
                //fragmentClass = ThirdFragment.class;
                //ToDo: What happends when clicked
                navigationDrawerCheckItem();
                Toast.makeText(getApplicationContext(), "Sortiranje", Toast.LENGTH_SHORT).show();
                break;
            case R.id.naV_filter:
                navigationDrawerCheckItem();
                MainController.getInstance().showFilterDialog(getFragmentManager());
                break;
        }
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private void navigationDrawerCheckItem() {
        Integer res = MainController.getInstance().getSelectedScreen();
        if (res == null) {
            Activities activities = MainController.getInstance().getFirstScreen(getApplicationContext());
            switch (activities) {
                case BODOVNA_LISTA:
                    res = R.id.nav_first_fragment;
                    break;
                case REJTING_LISTA:
                    res = R.id.nav_second_fragment;
                    break;
                case OMILJENI:
                    res = R.id.nav_third_fragment;
                    break;
            }
        }
        nvDrawer.setCheckedItem(res);
    }

    private void summonChooseFirstScreenAlert() {
        final CharSequence[] screens = {Activities.BODOVNA_LISTA.name(), Activities.REJTING_LISTA.name(), Activities.OMILJENI.name()};
        Activities activities = MainController.getInstance().getFirstScreen(getApplicationContext());
        int id = 0;
        if (activities == Activities.OMILJENI) {
            id = 2;
        } else if (activities == Activities.REJTING_LISTA) {
            id = 1;
        } else {
            id = 0;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Izaberite prvi ekran");
        builder.setSingleChoiceItems(screens, id, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //ToDo: When is clocked
                if (screens[i].equals(Activities.BODOVNA_LISTA.name())) {
                    MainController.getInstance().setFirstScreen(Activities.BODOVNA_LISTA, getApplicationContext());
                    navigationDrawerCheckItem();
                    dialogInterface.cancel();
                } else if (screens[i].equals(Activities.REJTING_LISTA.name())) {
                    MainController.getInstance().setFirstScreen(Activities.REJTING_LISTA, getApplicationContext());
                    navigationDrawerCheckItem();
                    dialogInterface.cancel();
                } else {
                    MainController.getInstance().setFirstScreen(Activities.OMILJENI, getApplicationContext());
                    navigationDrawerCheckItem();
                    dialogInterface.cancel();
                }
            }
        }).show();
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     *
     * @param fragments
     */
    public void pushFragmentToScreen(ArrayList<Fragment> fragments) {

    }

    @Override
    public void onDialogPositivClick(DialogFragment dialog, Context context) {
        MainController.getInstance().updateCheckBoxes(context);
        navigationDrawerCheckItem();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, Context context) {
        MainController.getInstance().cancleTemporaryCheckBoxes(context);
        navigationDrawerCheckItem();
    }



}
