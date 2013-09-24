package com.micronixsolutions.livemusicarchive;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.micronixsolutions.api.music.Music;
import com.micronixsolutions.api.music.model.MessagesSearchResponse;

/**
 * Created by juan on 9/20/13.
 */
public class SearchActivity extends Activity implements SearchView.OnQueryTextListener {
    private SearchView mSearchView;
    private String mLastSearchString;
    private SearchResultFragment mSearchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        setTitle(""); // Hide the title

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSearchFragment = new SearchResultFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_result_layout, mSearchFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint(getResources().getString(R.string.search_lma));
        mSearchView.onActionViewExpanded();

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(android.R.id.home):
                finish(); // Kill this activity, and go back
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchView.clearFocus();
        if(!query.equals(mLastSearchString)){
            mSearchFragment.doSearch(query);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 1 && !Character.isWhitespace(newText.charAt(newText.length()-1))){
            mSearchFragment.doSearch(newText);
        }
        mLastSearchString = newText;
        return false;
    }
}
