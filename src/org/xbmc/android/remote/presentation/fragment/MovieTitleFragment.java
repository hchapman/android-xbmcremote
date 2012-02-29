package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.MovieListController;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MovieTitleFragment extends Fragment {
	MovieListController mController;
	View mMessageView;
	Handler mHandler;
	Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		mHandler = new Handler();
		mController = new MovieListController();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		mContext = container.getContext();
		
		View view = inflater.inflate(R.layout.movietitlefragment, container, false);
		mMessageView = view.findViewById(R.id.movie_title_layout);
		mController.onCreate(getActivity(), mHandler, (ListView)view.findViewById(R.id.movietitlelist_list));
		return view;
	}

	
	@Override
	public void onStart() {
		super.onStart();
		mController.findMessageView(mMessageView);
	}

}
