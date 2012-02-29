/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xbmc.android.widget;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

/**
 * Demonstrates combining the action bar with a ViewPager to implement a tab UI
 * that switches between tabs and also allows the user to perform horizontal
 * flicks to move between the tabs.
 */
public class ActionBarTabsPager extends FragmentActivity {
    ViewPager  mViewPager;
    TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", getSupportActionBar().getSelectedNavigationIndex());
    }

    public ActionBar.Tab newTab(String tag, String label, int resIcon) {
    	return getSupportActionBar().newTab().setTag(tag).setText(label).setIcon(resIcon);
    }
    
    /**
     * This is a helper class that implements the management of tabs and all
     * details of connecting a ViewPager with associated TabHost.  It relies on a
     * trick.  Normally a tab host has a simple API for supplying a View or
     * Intent that each tab will show.  This is not sufficient for switching
     * between pages.  So instead we make the content part of the tab host
     * 0dp high (it is not shown) and the TabsAdapter supplies its own dummy
     * view to show as the tab content.  It listens to changes in tabs, and takes
     * care of switch to the correct paged in the ViewPager whenever the selected
     * tab changes.
     */
    public static class TabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, ActionBar.TabListener {
        private final Context mContext;
        private final ActionBar mActionBar;
        private final ViewPager mViewPager;
        private final ArrayList<String> mTabs = new ArrayList<String>();

        public TabsAdapter(FragmentActivity activity, ActionBar actionBar, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mActionBar = actionBar;
            mViewPager = pager;
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

        public void addTab(ActionBar.Tab tab, Class<?> clss) {
            mTabs.add(clss.getName());
            mActionBar.addTab(tab.setTabListener(this));
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mContext, mTabs.get(position), null);
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            mActionBar.setSelectedNavigationItem(position);
        }

        public void onPageScrollStateChanged(int state) {
        }

    	public void onTabSelected(Tab tab, FragmentTransaction ft) {
    		mViewPager.setCurrentItem(tab.getPosition());
    	}

    	public void onTabReselected(Tab tab, FragmentTransaction ft) {
    	}

    	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	}
    }
}
