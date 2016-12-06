package com.example.pratigya.hamblaster;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);
        Fragment fragment = new BookDisplayFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame1, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
