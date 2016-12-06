package com.example.pratigya.hamblaster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
public class RecyclerViewAdapterAddReminder extends RecyclerView.Adapter<RecyclerViewHoldersAddReminder> {
    private List<Event> Eventlist;
    protected Context context;
    public RecyclerViewAdapterAddReminder(Context context, List<Event> Eventlist) {
        this.Eventlist = Eventlist;
        this.context = context;
    }
    @Override
    public RecyclerViewHoldersAddReminder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHoldersAddReminder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_user, parent, false);
        viewHolder = new RecyclerViewHoldersAddReminder(layoutView, Eventlist);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHoldersAddReminder holder, int position) {
        holder.categoryTitle.setText(Eventlist.get(position).getevent_name());
    }
    @Override
    public int getItemCount() {
        return this.Eventlist.size();
    }
}