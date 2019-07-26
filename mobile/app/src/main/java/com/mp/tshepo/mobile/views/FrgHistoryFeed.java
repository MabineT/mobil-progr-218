package com.mp.tshepo.mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mp.tshepo.mobile.R;
import com.mp.tshepo.mobile.realm.Feed;
import com.mp.tshepo.mobile.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Tshepo on 07/08/2017.
 */

public class FrgHistoryFeed extends Fragment {
    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private Realm realm;
    private RealmHelper helper;
    private List<Feed> items;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_history_feed, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.action_history);
        historyRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_history);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), OrientationHelper.VERTICAL, false));
        helper = new RealmHelper();
        items = new ArrayList<>();
        realm = helper.initialize("feedsDB");
        items = helper.read(realm);
        historyAdapter = new HistoryAdapter(getContext(),items);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


