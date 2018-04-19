package com.example.dell.photoeditor.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.photoeditor.Data.Filter;
import com.example.dell.photoeditor.R;

import java.util.ArrayList;

/**
 * Created by dell on 4/19/2018.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private ArrayList<Filter> list = new ArrayList<>();
    public FilterAdapter(ArrayList<Filter> list){
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_filter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Filter filter = list.get(position);
        holder.tvName.setText(filter.getName());
        holder.iconFilter.setImageResource(filter.getIcon());
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(filter.getIcon());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconFilter;
        TextView tvName;
        LinearLayout line;
        public ViewHolder(View itemView) {
            super(itemView);
            iconFilter = itemView.findViewById(R.id.iconFilter);
            tvName = itemView.findViewById(R.id.tvNameFilter);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(int icon);
    }

    public OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
