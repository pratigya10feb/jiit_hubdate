package com.example.pratigya.hamblaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EntryActivity extends AppCompatActivity {
    private static final String TAG = EntryActivity.class.getSimpleName();
   private FirebaseAuth mAuth;
    public String activity;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
     /*   mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user != null) {
                    // User is signed in
                  Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                   activity = CheckForAdminActivity.class.getName();
                     Intent i=new Intent(EntryActivity.this,CheckForAdminActivity.class);
                   i.putExtra("email", user.getEmail());
                     startActivity(i);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    activity=ChooserActivity.class.getName();*/
                    Intent i=new Intent(EntryActivity.this,ChooserActivity.class);
                  // i.putExtra("email", user.getEmail());
                    startActivity(i);/*
                }

            }
       };
finish();
*/

    }



}
