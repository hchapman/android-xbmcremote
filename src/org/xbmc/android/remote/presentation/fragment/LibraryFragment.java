package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.ActorListController;
import org.xbmc.android.remote.presentation.controller.ListController;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public abstract class LibraryFragment extends Fragment {
	ListController mController;
	View mMessageView;
	Handler mHandler;
	Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		mHandler = new Handler();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		mContext = container.getContext();
		
		View view = inflater.inflate(R.layout.moviefragment, container, false);
		mMessageView = view.findViewById(R.id.movie_layout);
		ListView list = (ListView)view.findViewById(R.id.movielist_list);
		mController.onCreate(getActivity(), mHandler, list);
		mController.setListView(list);
		mController.findMessageView(mMessageView);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mController.onActivityResume(getActivity());
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mController.onActivityPause();
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}

}
