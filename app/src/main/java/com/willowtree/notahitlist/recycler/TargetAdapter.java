package com.willowtree.notahitlist.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willowtree.notahitlist.TargetProto;

/**
 * Created by WillowTree, Inc on 6/8/16.
 */

public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.SimpleViewHolder> {
    TargetProto.Targets targets;

    public TargetAdapter(TargetProto.Targets targets) {
        this.targets = targets;
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
    @Override
    public TargetAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null));
    }

    @Override
    public void onBindViewHolder(TargetAdapter.SimpleViewHolder holder, int position) {
        holder.text.setText(targets.targets[position].name);
    }

    @Override
    public int getItemCount() {
        return targets.targets.length;
    }
}
