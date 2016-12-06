package com.example.pratigya.hamblaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookDisplayFragment extends Fragment {
    private static final String TAG = BookDisplayFragment.class.getSimpleName();
    private RecyclerView recipeRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerBookAdapter mRecipeAdapter;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    public BookDisplayFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_display, container, false);
        getActivity().setTitle(getString(R.string.buy_books));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recipeRecyclerview = (RecyclerView)view.findViewById(R.id.buy_books);
        recipeRecyclerview.setHasFixedSize(true);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("books");
        mRecipeAdapter = new RecyclerBookAdapter(Book.class, R.layout.book_category_list, RecyclerBookHolder.class, childRef, getContext());
        recipeRecyclerview.setLayoutManager(linearLayoutManager);
        recipeRecyclerview.setAdapter(mRecipeAdapter);
        return view;
    }
}
