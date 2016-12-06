package com.example.pratigya.hamblaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckForAdminActivity extends AppCompatActivity {
    private static final String TAG = CheckForAdminActivity.class.getSimpleName();
    private VideoView myVideoView;
   // private final int SPLASH_DISPLAY_LENGTH = 3000;
    //  private int position = 0;
    // private ProgressDialog progressDialog;
    private MediaController mediaControls;
    public String message;
    private DatabaseReference databaseReference;
    private int p=0;
   // private DataSnapshot dataSnapshot;
   DataSnapshot singleSnapshot;
    private List<Admin> allAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_for_admin);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("email");
   //     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     //   ActionBar actionBar = getSupportActionBar();
     //   if(null != actionBar){
      //      actionBar.hide();
    //    }
        Log.e(TAG,message);
        allAdmin = new ArrayList<>();
        if (mediaControls == null) {
            mediaControls = new MediaController(CheckForAdminActivity.this);
       }

        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.video_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("admins");
       // Firebase srcRef = new Firebase("https://stackoverflow.firebaseio.com/32108969/users");
       /* databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {
                for (DataSnapshot userSnapshot : usersSnapshot.getChildren()) {
                    Admin admin = userSnapshot.getValue(Admin.class);
                    if(admin.getadmin_email().equals(message))
                    {
                         p=1;
                        Intent in = new Intent(CheckForAdminActivity.this, AdminViewActivity.class);
                         startActivity(in);
                    }


                    //   copyRef.child(userSnapshot.getKey()).setValue(user);
                }

                if(p==0)
                {
                    Intent i= new Intent(CheckForAdminActivity.this,Main2Activity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) { }
        });
        try {
            //set the media controller in the VideoView
          //     myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lecture1));
            myVideoView.start();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
//super.onBackPressed();
    }

 /*   private void getAllTask(DataSnapshot dataSnapshot) {
        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            Admin admin = singleSnapshot.getValue(Admin.class);
           allAdmin.add(new Admin(admin.getadmin_email()));

        }
       // for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
          //   String taskTitle= singleSnapshot.child("admins").child("-KY50XXYnYOwSY8ZONUa").child("admin_email").getValue(String.class);
          //  for (int i = 0; i < allAdmin.size(); i++) {
             //   if (allAdmin.get(i).getadmin_email().equals(message)) {
        //if(taskTitle.equals(message)){
                 //   Intent in = new Intent(CheckForAdminActivity.this, AddAdminActivity.class);
                  //  startActivity(in);
                 //   finish();
            //    }
         //   }
          //  }
           // Intent in1 = new Intent(CheckForAdminActivity.this, Main2Activity.class);
           // startActivity(in1);
      //  }
    }*/
}
