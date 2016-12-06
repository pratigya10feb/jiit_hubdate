package com.example.pratigya.hamblaster;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
public class RecyclerBookHolder extends RecyclerView.ViewHolder {
    private static final String TAG = RecyclerBookHolder.class.getSimpleName();

    public TextView book_email;
    public TextView book_title;
    public TextView book_phone;


    public RecyclerBookHolder(View itemView) {
        super(itemView);
        book_email = (TextView)itemView.findViewById(R.id.book_email);
          book_title = (TextView)itemView.findViewById(R.id.book_title);
        book_phone = (TextView)itemView.findViewById(R.id.book_phone);
        // selectedDate = (TextView)itemView.findViewById(R.id.selectedDate);


    }
}