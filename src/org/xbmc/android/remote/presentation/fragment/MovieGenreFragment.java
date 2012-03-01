package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.MovieGenreListController;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MovieGenreFragment extends LibraryFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mController = new MovieGenreListController(MovieGenreListController.TYPE_MOVIE);
	}
}
