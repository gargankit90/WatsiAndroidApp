package codepath.watsiapp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import codepath.watsiapp.R;
import codepath.watsiapp.adapters.HomeFeedAdapter;
import codepath.watsiapp.models.NewsItem;

import com.parse.ParseException;
import com.parse.ParseQueryAdapter.OnQueryLoadListener;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class PatientFeedFragment extends Fragment {

	private HomeFeedAdapter patientFeedAdapter;
	private eu.erikw.PullToRefreshListView listView;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_patient_feed, container,
				false);

		progressBar=(ProgressBar)v.findViewById(R.id.pf_progressBar);
		patientFeedAdapter = new HomeFeedAdapter(getActivity());
		patientFeedAdapter.addOnQueryLoadListener(new OnQueryLoadListener<NewsItem>() {

			@Override
			public void onLoaded(List<NewsItem> newsItems, Exception exp) {
				progressBar.setVisibility(View.INVISIBLE);
				listView.onRefreshComplete();
				if(exp == null) {
					try {
						ArrayList<NewsItem> list = new ArrayList<NewsItem>();
						for (NewsItem newsItem : newsItems) {
							if (newsItem != null) {
								list.add(newsItem);
							}
						}
						NewsItem.pinAll(list);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else {
					// TODO:  Failure Toast
				}
			}

			@Override
			public void onLoading() {
				progressBar.setVisibility(View.VISIBLE);
			}
		});

		listView = (PullToRefreshListView) v.findViewById(R.id.patient_feed_list);
		listView.setAdapter(patientFeedAdapter);
		listView.setOnScrollListener(new OnScrollListener() 
		{
			int mLastFirstVisibleItem = 0;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {   }           

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) 
			{   
				if (view.getId() == listView.getId()) 
				{
					final int currentFirstVisibleItem = listView.getFirstVisiblePosition();

					if (currentFirstVisibleItem > mLastFirstVisibleItem) 
					{
						getActivity().getActionBar().hide();
					} 
					else if (currentFirstVisibleItem < mLastFirstVisibleItem) 
					{
						getActivity().getActionBar().show();
					}

					mLastFirstVisibleItem = currentFirstVisibleItem;
				}               
			}
		});
		setupIintialViews();
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupIintialViews();
	}

	private void setupIintialViews() {
		listView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				patientFeedAdapter.clear();
				patientFeedAdapter.loadObjects();
			}
		});

	}

	public static PatientFeedFragment newInstance() {
		PatientFeedFragment fragment = new PatientFeedFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}
}
