package edu.upc.dsa.minim2_examen_dsa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Repo> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView repoName;
        public TextView repoLanguage;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            repoName = v.findViewById(R.id.repoNameTv);
            repoLanguage = v.findViewById(R.id.repoLanguageTv);
        }
    }


    public void add(int position, Repo item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerAdapter(List<Repo> myDataset) {
        values = myDataset;
        LayoutInflater inflater;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final String name = values.get(position).getName();
        final String language = values.get(position).getLanguage();
        holder.repoName.setText(name);
        holder.repoLanguage.setText(language);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
