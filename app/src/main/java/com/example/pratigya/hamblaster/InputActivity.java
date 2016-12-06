package com.example.pratigya.hamblaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 234;
    // private DatePicker datePicker;
    //  private Calendar calendar;
    //   private TextView dateView;
    //  private int year, month, day;
    //Buttons
    private Button buttonChoose;
    private Button buttonUpload;
    private String download;
    //ImageView
    private ImageView imageView;
 //   private ImageView imageView1;
    private Uri downloadUrl;
    //a Uri object to store file path
    private Uri filePath;

    //firebase storage reference
    private StorageReference storageReference;
    private static final String TAG = InputActivity.class.getSimpleName();
    private String selectedDate;
    private EditText Time,Event_name,Event_venue,Date1,Hub;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    //private DatePicker datepick;
    public String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Displaying toolbar icon
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);

        imageView = (ImageView) findViewById(R.id.imageView);
      //   dateView = (TextView) findViewById(R.id.textView3);
        //  calendar = Calendar.getInstance();
      //  imageView1 = (ImageView) findViewById(R.id.imageView4);
        //attaching listener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        //getting firebase storage reference
        storageReference = FirebaseStorage.getInstance().getReference();
        Event_name = (EditText) findViewById(R.id.eventname);
        Event_venue = (EditText) findViewById(R.id.eventvenue);
        Time = (EditText) findViewById(R.id.time);
        Date1=(EditText) findViewById(R.id.dateselect);
        Hub=(EditText) findViewById(R.id.hub);
        //  datepick=(DatePicker) findViewById((R.id.datePicker));
        btnSave = (Button) findViewById(R.id.btn_save);
        ///@SuppressWarnings("deprecation") final
        // String selectedDate = DateFormat.getDateInstance().format(datepick.getCalendarView().getDate());
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("events");

        // store app title to 'app_title' node
      //  mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event_name = Event_name.getText().toString();
                String event_venue = Event_venue.getText().toString();
                String event_time=Time.getText().toString();
                String event_date=Date1.getText().toString();
                String hub=Hub.getText().toString();

                if (TextUtils.isEmpty(eventId)) {
                    createEvent(event_name, event_venue,event_time,downloadUrl.toString(),event_date,hub);
                } else {
                //    updateEvent(event_name, event_venue,event_time,event_date);
                }
                Intent startActivity = new Intent(InputActivity.this, Main2Activity.class);
                startActivity(startActivity);
            }
        });


     //   toggleButton();

    }

    // Changing button text
  /*  private void toggleButton() {
        if (TextUtils.isEmpty(eventId)) {
            btnSave.setText("Save");
        } else {
            btnSave.setText("Update");
        }
    }*/

    /**
     * Creating new user node under 'users'
     */
    private void createEvent(String event_name, String event_venue,String event_time,String download,String event_date,String hub) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(eventId)) {
            eventId = mFirebaseDatabase.push().getKey();
        }

        Event event = new Event(event_name, event_venue,event_time,download,event_date,hub);

        mFirebaseDatabase.child(eventId).setValue(event);

        addUserChangeListener();
    }


    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(eventId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);
                //  download_path=Uri.parse(event.getdownload());
                // Check for null
                if (event == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + event.event_name + ", " + event.event_venue);



                Event_name.setText("");
                Event_venue.setText("");
                Time.setText("");
               // toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

   /*private void updateEvent(String event_name, String event_venue,String event_time,String event_date ) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(event_name))
            mFirebaseDatabase.child(eventId).child("event_name").setValue(event_name);

        if (!TextUtils.isEmpty(event_venue))
            mFirebaseDatabase.child(eventId).child("event_venue").setValue(event_venue);
        if (!TextUtils.isEmpty(event_time))
            mFirebaseDatabase.child(eventId).child("event_time").setValue(event_time);
        if (!TextUtils.isEmpty(event_date))
            mFirebaseDatabase.child(eventId).child("event_date").setValue(event_date);




    }*/
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //this method will upload the file
    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
//displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child(""+filePath.getLastPathSegment());
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            downloadUrl = taskSnapshot.getDownloadUrl();

                            downloadUrl.toString();
//AddEventActivity.sendtoevent(downloadUrl.toString());
                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //Intent i=new Intent(getApplicationContext(),AddEventActivity.class);
                            //  startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }

    @Override
    public void onClick(View view) {
        //if the clicked button is choose
        if (view == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (view == buttonUpload) {
            uploadFile();
        }
    }

}
