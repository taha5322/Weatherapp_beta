package com.example.stormy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stormy.R;
import com.example.stormy.databinding.HourlyListItemBinding;
import com.example.stormy.weather.Hour;

import java.util.List;

// Created by Taha Siddiqui
// 2020-04-05
public class HourlyAddapter extends RecyclerView.Adapter<HourlyAddapter.ViewHolder> {

    private List<Hour> hours;
    private Context context;
    HourlyListItemBinding binding;

    public HourlyAddapter(List<Hour> hours, Context context) {
        this.hours = hours;
        this.context = context;
    }

    @NonNull
    @Override
    //called when adapter created, initialises the viewholder
    public HourlyAddapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflates the binding layoutt and creates the newly created binding for that layout
        binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()), R.layout.hourly_list_item, parent, false);

        return new ViewHolder(binding);
    }

    //Where data is passed to the view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("Error ", "This is the number "+position);
        Hour hour = hours.get(position);
        if(holder == null){
            Log.e("HOLDER IS NULL", "HOLDER IS NULL");
        }
        if(hour==null){
            Log.e("HOUR IS NULL", "HOUR IS NULL");
        }
        if(holder.hourlyListItemBinding == null){
            Log.e("ITEM BINDING IS EMPTY", "ITEM BINDING EMPTY");

        }

        holder.hourlyListItemBinding.setHour(hour);
    }

    //returns the size of the collection of items to display
    //used by layout manager
    @Override
    public int getItemCount() {
        //returns size of length
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //binding vars
        public HourlyListItemBinding hourlyListItemBinding;

        //Constructor does view lookups for each view
        public ViewHolder (HourlyListItemBinding hourlyListItemBinding){
            super(hourlyListItemBinding.getRoot());
            this.hourlyListItemBinding = hourlyListItemBinding;
        }


    }
}
