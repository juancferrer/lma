package com.micronixsolutions.livemusicarchive;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class LiveMusicArchive extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private int mCurrentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mCurrentSection = savedInstanceState.getInt("currentSection", 0);  //Default to library section
        }

        setContentView(R.layout.live_music_archive);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.library_string);

        // Set up the action bar navdrawer toggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View drawerView){
                // Maybe they didn't click anything in the drawer
                // Make sure the title is set back to the app section name even if they didn't click on the drawer
                actionBar.setTitle(getResources().getStringArray(R.array.drawer_strings)[mDrawerList.getCheckedItemPosition()]);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                //When the drawer is open, always use the app name as the title
                actionBar.setTitle(R.string.app_name);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //Setup the drawer content  (list view with all the sections in the app)
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, R.id.drawer_item, getResources().getStringArray(R.array.drawer_strings)));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        if(savedInstanceState == null){
            // First launch, with no saved instance
            selectFragment(mCurrentSection); // Load default app section
            // else do nothing, android loads fragment on restore by itself
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentSection", mCurrentSection); // Save the currently selected tab
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.library, menu);
        // Add the searchable conf to the searchview
        //SearchView searchView = (SearchView) menu.findItem(R.id.music_search).getActionView();
        //searchView.setIconified(false);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        switch (item.getItemId()){
            case R.id.music_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Swaps fragments in the main content view */
    private void selectFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new LibraryFragment();
                break;
            case 1:
                fragment = new PlaylistFragment();
                break;
            default:
                fragment = new LibraryFragment();
                break;
        }
        Bundle args = new Bundle();
        //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        getActionBar().setTitle(getResources().getStringArray(R.array.drawer_strings)[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            mCurrentSection = position;
            selectFragment(position);
        }
    }


}
