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

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.MyViewHolder> {
    private Context context;
    private List<Feed> items;

    public RelatedAdapter(Context context, List<Feed> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_related, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Feed item = items.get(position);

        holder.name.setText(item.getTitle());
        holder.details.setText(item.getOutput());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, details;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.related_title);
            details = view.findViewById(R.id.related_details);
        }
    }
}