package com.example.pratigya.hamblaster;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class RecyclerBookAdapter extends FirebaseRecyclerAdapter<Book, RecyclerBookHolder> {
    private static final String TAG = RecyclerBookAdapter.class.getSimpleName();
    private Context context;
    public RecyclerBookAdapter(Class<Book> modelClass, int modelLayout, Class<RecyclerBookHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }
    @Override
    protected void populateViewHolder(RecyclerBookHolder viewHolder, Book model, int position) {
        viewHolder.book_email.setText("Email:  "+model.getbook_email());
         viewHolder.book_title.setText("Book Title:   "+model.getbook_title());
          viewHolder.book_phone.setText("Phone:   "+model.getbook_phone());
        //viewHolder.selectedDate.setText("Date:   "+model.getSelectedDate());
        //Glide.with(context).load(model.getdownload()).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.eventImage);

    }
}