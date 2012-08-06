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
import org.xbmc.android.remote.presentation.controller.MediaIntentController;
import org.xbmc.android.remote.presentation.controller.RemoteController;
import org.xbmc.android.util.KeyTracker;
import org.xbmc.android.util.KeyTracker.Stage;
import org.xbmc.android.util.OnLongPressBackKeyTracker;
import org.xbmc.api.business.IEventClientManager;
import org.xbmc.api.type.ThumbSize;
import org.xbmc.eventclient.ButtonCodes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class JustintvLoaderActivity extends Activity implements OnClickListener {

	private EditText mJtvUsername;
	private Button mJtvSubmit;

	private MediaIntentController mMediaIntentController;
	private ConfigurationManager mConfigurationManager;
	private KeyTracker mKeyTracker;

	private boolean mMonitorMode = false;

	private static final int MENU_REMOTE = 303;
	private static final int MENU_MONITOR_MODE = 304;
	
	public JustintvLoaderActivity() {
		if(Integer.parseInt(VERSION.SDK) < 5) {
			mKeyTracker = new KeyTracker(new OnLongPressBackKeyTracker() {
	
				@Override
				public void onLongPressBack(int keyCode, KeyEvent event,
						Stage stage, int duration) {
					onKeyLongPress(keyCode, event);
				}
		
				@Override
				public void onShortPressBack(int keyCode, KeyEvent event,
						Stage stage, int duration) {
//					callSuperOnKeyDown(keyCode, event);
				}
				
			});
		};
	}	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set display size
		final Display display = getWindowManager().getDefaultDisplay(); 
		ThumbSize.setScreenSize(display.getWidth(), display.getHeight());	
		
		setContentView(R.layout.justintv);

		mJtvUsername = (EditText) findViewById(R.id.jtvUsername);
		mJtvSubmit = (Button) findViewById(R.id.jtvSubmit);
		
		mMediaIntentController = new MediaIntentController(this, new Handler());
		
		mJtvSubmit.setOnClickListener(this);

		// remove nasty top fading edge
		FrameLayout topFrame = (FrameLayout) findViewById(android.R.id.content);
		topFrame.setForeground(null);

		mConfigurationManager = ConfigurationManager.getInstance(this);
	}

	public void clear() {
	}
//
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		menu.clear();
//		menu.add(0, MENU_REMOTE, 0, "Remote control").setIcon(R.drawable.menu_remote);
//		menu.add(0, MENU_MONITOR_MODE, 0, "Monitor mode").setIcon(R.drawable.menu_view);
//		return super.onPrepareOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//		switch (item.getItemId()) {
//		case MENU_REMOTE:
//			final Intent intent;
//			if (getSharedPreferences("global", Context.MODE_PRIVATE).getInt(RemoteController.LAST_REMOTE_PREFNAME, -1) == RemoteController.LAST_REMOTE_GESTURE) {
//				intent = new Intent(this, GestureRemoteActivity.class);
//			} else {
//				intent = new Intent(this, RemoteActivity.class);
//			}
//			intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
//			startActivity(intent);
//			return true;
//		case MENU_MONITOR_MODE:
//			switchMonitorMode();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		IEventClientManager client = ManagerFactory.getEventClientManager(mMediaIntentController);
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_VOLUME_UP:
//			client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_PLUS, false, true, true, (short) 0, (byte) 0);
//			return true;
//		case KeyEvent.KEYCODE_VOLUME_DOWN:
//			client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_MINUS, false, true, true, (short) 0, (byte) 0);
//			return true;
//		case KeyEvent.KEYCODE_SEARCH:
//			switchMonitorMode();
//			return true;
///*		case KeyEvent.KEYCODE_PAGE_UP:
//			switchMonitorMode();
//			return true;*/
//		}
//		client.setController(null);
//		boolean handled = (mKeyTracker != null) ? mKeyTracker.doKeyDown(keyCode, event) : false;
//		return handled || super.onKeyDown(keyCode, event);
//	}
//
//	private void switchMonitorMode() {
//		mMonitorMode = !mMonitorMode;
//		handleLayout();
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		mMediaIntentController.onActivityResume(this);
//		mConfigurationManager.onActivityResume(this);
//		handleLayout();
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//		mMediaIntentController.onActivityPause();
//		mConfigurationManager.onActivityPause();
//	}
//
//	private void handleLayout() {
//		if (mMonitorMode) {
//			// TODO
//		} else {
//			// TODO
//		}
//	}
//
//	protected void callSuperOnKeyDown(int keyCode, KeyEvent event) {
//		onBackPressed();
//		super.onKeyDown(keyCode, event);
//	}
//
//	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//		Intent intent = new Intent(JustintvLoaderActivity.this, HomeActivity.class);
//		intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//		return true;
//	}
//
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		boolean handled = (mKeyTracker != null) ? mKeyTracker.doKeyUp(keyCode, event) : false;
//		return handled || super.onKeyUp(keyCode, event);
//	}
//	
//	@Override
//	public void onBackPressed() {
//		if (isTaskRoot()) {
//			Intent intent = new Intent(JustintvLoaderActivity.this, HomeActivity.class );
//			JustintvLoaderActivity.this.startActivity(intent);
//		}
//		
//		finish();
//		
//	    return;
//	}

	public void onClick(View v) {
		if (mJtvUsername.getText().length() != 0)
			//return;
			mMediaIntentController.playUrl("plugin://plugin.video.jtv.archives/?url=&mode=2&name=" + mJtvUsername.getText());
	}
}