package com.mp.tshepo.mobile.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mp.tshepo.mobile.R;
import com.mp.tshepo.mobile.realm.Feed;

import java.util.List;

/**
 * Created by Tshepo on 30/04/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private Context context;
    private List<Feed> items;

    public HistoryAdapter(Context context, List<Feed> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feed, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Feed item = items.get(position);

        holder.name.setText(item.getTitle());
        holder.details.setText(item.getOutput());
        holder.time.setText(item.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, details;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.calculation_title);

            details = view.findViewById(R.id.calculation_details);
            time = view.findViewById(R.id.calculation_time);
        }
    }
}