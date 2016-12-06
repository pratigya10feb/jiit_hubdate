package com.example.pratigya.hamblaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAdminActivity extends AppCompatActivity {
    private static final String TAG = AddAdminActivity.class.getSimpleName();

    private EditText Admin_email;
    private Button btnAdd;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
   MultiSpinner spinner;
    public String adminId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        Admin_email = (EditText) findViewById(R.id.admin_email);

        btnAdd = (Button) findViewById(R.id.add);
        // Multi spinner
       spinner = (MultiSpinner) findViewById(R.id.mySpinner1);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("admins");

        // store app title to 'app_title' node
     //   mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        // app_title change listener
      /*  mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });*/
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin_email = Admin_email.getText().toString();
              String s = spinner.getSelectedItemsAsString();
               // Log.e("getSelected", s);

                if (TextUtils.isEmpty(adminId)) {
                    createAdmin(admin_email,s);
                } else {
                    updateAdmin(admin_email);
                }
               // Intent startActivity = new Intent(AddAdminActivity.this, Main2Activity.class);
              //  startActivity(startActivity);
            }
        });
        toggleButton();

    }
    private void toggleButton() {
        if (TextUtils.isEmpty(adminId)) {
            btnAdd.setText("Add");
        } else {
            btnAdd.setText("Update");
        }
    }

    /**
     * Creating new user node under 'users'
     */
    private void createAdmin(String admin_email,String admin_hubs) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(adminId)) {
            adminId = mFirebaseDatabase.push().getKey();
        }

        Admin admin = new Admin(admin_email,admin_hubs);

        mFirebaseDatabase.child(adminId).setValue(admin);

        addUserChangeListener();
    }


    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(adminId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Admin admin = dataSnapshot.getValue(Admin.class);

                // Check for null
                if (admin== null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + admin.admin_email );



               Admin_email.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateAdmin(String admin_email ) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(admin_email))
            mFirebaseDatabase.child(adminId).child("admin_email").setValue(admin_email);
    }
}
