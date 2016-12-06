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

public class RecipeCategoriesFragment extends Fragment {
    private static final String TAG = RecipeCategoriesFragment.class.getSimpleName();
    private RecyclerView recipeRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private RecipeAdapter mRecipeAdapter;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    public RecipeCategoriesFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_categories, container, false);
        getActivity().setTitle(getString(R.string.recipe_categories));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recipeRecyclerview = (RecyclerView)view.findViewById(R.id.recipe_categories);
        recipeRecyclerview.setHasFixedSize(true);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("events");
        mRecipeAdapter = new RecipeAdapter(Event.class, R.layout.recipe_category_list, RecipeHolder.class, childRef, getContext());
        recipeRecyclerview.setLayoutManager(linearLayoutManager);
        recipeRecyclerview.setAdapter(mRecipeAdapter);
        return view;
    }
}