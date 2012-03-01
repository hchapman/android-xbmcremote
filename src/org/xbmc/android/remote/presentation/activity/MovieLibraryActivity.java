/*
 *      Copyright (C) 2005-2009 Team XBMC
 *      http://xbmc.org
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XBMC Remote; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

package org.xbmc.android.remote.presentation.activity;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.business.ManagerFactory;
import org.xbmc.android.remote.presentation.fragment.MovieActorFragment;
import org.xbmc.android.remote.presentation.fragment.MovieFilesFragment;
import org.xbmc.android.remote.presentation.fragment.MovieGenreFragment;
import org.xbmc.android.remote.presentation.fragment.MovieTitleFragment;
import org.xbmc.android.widget.ActionBarTabsPager;
import org.xbmc.api.business.IEventClientManager;
import org.xbmc.eventclient.ButtonCodes;

import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;

public class MovieLibraryActivity extends ActionBarTabsPager implements ViewTreeObserver.OnGlobalLayoutListener {

	private TabsAdapter mTabsAdapter;
	private ViewPager mViewPager;
	
	private static final int MENU_NOW_PLAYING = 301;
	private static final int MENU_UPDATE_LIBRARY = 302;
	private static final int MENU_REMOTE = 303;

	private static final String PREF_REMEMBER_TAB = "setting_remember_last_tab";
	private static final String LAST_MOVIE_TAB_ID = "last_movie_tab_id";
	
    private ConfigurationManager mConfigurationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movielibrary);
		
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager)findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(this, getSupportActionBar(), mViewPager);
        
		// add the tabs
		mTabsAdapter.addTab(newTab("tab_movies", "Movies", R.drawable.st_movie_on), MovieTitleFragment.class);
		mTabsAdapter.addTab(newTab("tab_actors", "Actors", R.drawable.st_actor_on), MovieActorFragment.class);
		mTabsAdapter.addTab(newTab("tab_genres", "Genres", R.drawable.st_genre_on), MovieGenreFragment.class);
		mTabsAdapter.addTab(newTab("tab_files", "File Mode", R.drawable.st_filemode_on), MovieFilesFragment.class);
//		
		//mTabsAdapter.getViewTreeObserver().addOnGlobalLayoutListener(this);

		// assign the gui logic to each tab
		//mHandler = new Handler();
//		mMovieController = new MovieListController();
//		mMovieController.findTitleView(findViewById(R.id.movielist_outer_layout));
//		mMovieController.findMessageView(findViewById(R.id.movielist_outer_layout));
//
//		mActorController = new ActorListController(ActorListController.TYPE_MOVIE);
//		mActorController.findTitleView(findViewById(R.id.actorlist_outer_layout));
//		mActorController.findMessageView(findViewById(R.id.actorlist_outer_layout));
//
//		mGenresController = new MovieGenreListController(MovieGenreListController.TYPE_MOVIE);
//		mGenresController.findTitleView(findViewById(R.id.genrelist_outer_layout));
//		mGenresController.findMessageView(findViewById(R.id.genrelist_outer_layout));
//
//		mFileController = new FileListController(MediaType.VIDEO);
//		mFileController.findTitleView(findViewById(R.id.filelist_outer_layout));
//		mFileController.findMessageView(findViewById(R.id.filelist_outer_layout));
//		
//		mTabsAdapter.setOnTabChangedListener(new OnTabChangeListener() {
//			public void onTabChanged(String tabId) {
//				
//				initTab(tabId);
//				
//				final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//				if (prefs.getBoolean(PREF_REMEMBER_TAB, false)) {
//					getSharedPreferences("global", Context.MODE_PRIVATE).edit().putString(LAST_MOVIE_TAB_ID, tabId).commit();
//				}
//			}
//		});
		mConfigurationManager = ConfigurationManager.getInstance(this);
        if (savedInstanceState != null) {
        	getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt("index"));
        }
	}
	
	public void onGlobalLayout() {
        //getViewTreeObserver().removeGlobalOnLayoutListener(this);
		
//		String lastTab = "tab_movies";
//		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
//		if (prefs.getBoolean(PREF_REMEMBER_TAB, false)) {
//			lastTab = (getSharedPreferences("global", Context.MODE_PRIVATE).getString(LAST_MOVIE_TAB_ID, "tab_movies"));
//			//mTabsAdapter.selectTabByTag(lastTab);
//		}
	}
	
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		menu.clear();
//		menu.add(0, MENU_NOW_PLAYING, 0, "Now playing").setIcon(R.drawable.menu_nowplaying);
//		switch (mTabsAdapter.getCurrentTab()) {
//			case 0:
//				mMovieController.onCreateOptionsMenu(menu);
//				break;
//			case 1:
//				mActorController.onCreateOptionsMenu(menu);
//				break;
//			case 2:
//				mGenresController.onCreateOptionsMenu(menu);
//				break;
//			case 3:
//				mFileController.onCreateOptionsMenu(menu);
//				break;
//		}
//		menu.add(0, MENU_UPDATE_LIBRARY, 0, "Update Library").setIcon(R.drawable.menu_refresh);
//		menu.add(0, MENU_REMOTE, 0, "Remote control").setIcon(R.drawable.menu_remote);
//		return super.onPrepareOptionsMenu(menu);
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		
//		// first, process individual menu events
//		switch (mTabsAdapter.getCurrentTab()) {
//		case 0:
//			mMovieController.onOptionsItemSelected(item);
//			break;
//		case 1:
//			mActorController.onOptionsItemSelected(item);
//			break;
//		case 2:
//			mGenresController.onOptionsItemSelected(item);
//			break;
//		case 3:
//			mFileController.onOptionsItemSelected(item);
//			break;
//		}
//		
//		// then the generic ones.
//		switch (item.getItemId()) {
//			case MENU_REMOTE:
//				final Intent intent;
//				if (getSharedPreferences("global", Context.MODE_PRIVATE).getInt(RemoteController.LAST_REMOTE_PREFNAME, -1) == RemoteController.LAST_REMOTE_GESTURE) {
//					intent = new Intent(this, GestureRemoteActivity.class);
//				} else {
//					intent = new Intent(this, RemoteActivity.class);
//				}
//				intent.addFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
//				startActivity(intent);
//				return true;
//			case MENU_UPDATE_LIBRARY:
//				mMovieController.refreshMovieLibrary(this);
//				return true;
//			case MENU_NOW_PLAYING:
//				startActivity(new Intent(this,  NowPlayingActivity.class));
//				return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//		super.onCreateContextMenu(menu, v, menuInfo);
//		switch (mTabsAdapter.getCurrentTab()) {
//			case 0:
//				mMovieController.onCreateContextMenu(menu, v, menuInfo);
//				break;
//			case 1:
//				mActorController.onCreateContextMenu(menu, v, menuInfo);
//				break;
//			case 2:
//				mGenresController.onCreateContextMenu(menu, v, menuInfo);
//				break;
//			case 3:
//				mFileController.onCreateContextMenu(menu, v, menuInfo);
//				break;
//		}
//	}
//	
//	@Override
//	public boolean onContextItemSelected(MenuItem item) {
//		switch (mTabsAdapter.getCurrentTab()) {
//		case 0:
//			mMovieController.onContextItemSelected(item);
//			break;
//		case 1:
//			mActorController.onContextItemSelected(item);
//			break;
//		case 2:
//			mGenresController.onContextItemSelected(item);
//			break;
//		case 3:
//			mFileController.onContextItemSelected(item);
//			break;
//		}
//		return super.onContextItemSelected(item);
//	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		IEventClientManager client = ManagerFactory.getEventClientManager(mMovieController);
//		switch (keyCode) {
//			case KeyEvent.KEYCODE_VOLUME_UP:
//				client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_PLUS, false, true, true, (short)0, (byte)0);
//				return true;
//			case KeyEvent.KEYCODE_VOLUME_DOWN:
//				client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_MINUS, false, true, true, (short)0, (byte)0);
//				return true;
//		}
//		client.setController(null);
		return super.onKeyDown(keyCode, event);
	}
	

	@Override
	protected void onResume() {
		super.onResume();
//		mMovieController.onActivityResume(this);
//		mActorController.onActivityResume(this);
//		mGenresController.onActivityResume(this);
//		mFileController.onActivityResume(this);
		mConfigurationManager.onActivityResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		mMovieController.onActivityPause();
//		mActorController.onActivityPause();
//		mGenresController.onActivityPause();
//		mFileController.onActivityPause();
		mConfigurationManager.onActivityPause();
	}
}
