package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.presentation.controller.ActorListController;

import android.os.Bundle;

public class MovieActorFragment extends LibraryFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mController = new ActorListController(ActorListController.TYPE_MOVIE);
	}
}
