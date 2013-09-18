package com.micronixsolutions.livemusicarchive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

/**
 * Created by juan on 9/17/13.
 */
public class LibraryFragment extends Fragment {
    private int mCurrentTabIndex = 1;
    private ViewPager mPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mCurrentTabIndex = savedInstanceState.getInt("currentTabIndex", 1); // Default to the artists tab
        }

        View rootView = inflater.inflate(R.layout.library_fragment, container, false);
        // Set the pager with an adapter
        ViewPager mPager = (ViewPager) rootView.findViewById(R.id.library_pager);
        mPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Fragment tab_content_fragment;
                switch (i) {
                    case 0:
                        tab_content_fragment = new ArtistsGridFragment();
                        break;
                    case 1:
                        //tab_content_fragment = ShowsGridFragment();
                        tab_content_fragment = new ArtistsGridFragment();
                        break;
                    case 2:
                        //tab_content_fragment = SongsGridFragment();
                        tab_content_fragment = new ArtistsGridFragment();
                        break;
                    case 3:
                        //tab_content_fragment = SongsGridFragment();
                        tab_content_fragment = new ArtistsGridFragment();
                        break;
                    default:
                        tab_content_fragment = new ArtistsGridFragment();
                        break;
                }
                return tab_content_fragment;
            }

            @Override
            public int getCount() {
                //String[] tab_strings = getResources().getStringArray(R.array.library_tab_strings);
                //return tab_strings.length;
                return 4;
            }

            public String getPageTitle(int position) {
                return getResources().getStringArray(R.array.library_tab_strings)[position].toUpperCase();
            }
        });

        // Bind the widget to the adapter
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.library_tabs);
        tabs.setViewPager(mPager);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentTabIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mPager.setCurrentItem(mCurrentTabIndex);
        // Update the action bar title
        getActivity().getActionBar().setTitle(getResources().getString(R.string.library_string));
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save the currently selected tab (genres, artists, shows, etc)
        super.onSaveInstanceState(outState);
        outState.putInt("currentTabIndex", mCurrentTabIndex);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
