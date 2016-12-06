package com.example.pratigya.hamblaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParolaActivity extends AppCompatActivity {
    private static final String TAG = ParolaActivity.class.getSimpleName();

    private EditText Book_email;
    private EditText Book_phone;

    private EditText Book_title;

    private Button btnAdd;
    private Button btnbuy;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    //  MultiSpinner spinner;
    public String bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parola);
        Book_email = (EditText) findViewById(R.id.book_email);
        Book_phone=(EditText) findViewById(R.id.book_phone);
        Book_title=(EditText) findViewById(R.id.book_title);
        btnbuy = (Button) findViewById(R.id.btn_buy);
        btnAdd = (Button) findViewById(R.id.add);
        // Multi spinner
        //  spinner = (MultiSpinner) findViewById(R.id.mySpinner1);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("books");

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
        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParolaActivity.this, DisplayBooksActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_email = Book_email.getText().toString();
                String book_phone=Book_phone.getText().toString();
                String book_title=Book_title.getText().toString();
                ///   List<String> s = spinner.getSelectedStrings();
                // Log.e("getSelected", s);

                if (TextUtils.isEmpty(bookId)) {
                    createAdmin(book_email,book_phone,book_title);
                } else {
                    updateAdmin(book_email,book_title,book_phone);
                }
                Toast.makeText(ParolaActivity.this, getString(R.string.book_succesfully_added), Toast.LENGTH_LONG).show();
           /*   Fragment fragment = new BookDisplayFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame1, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();*/
             //   Intent startActivity = new Intent(ParolaActivity.this, DisplayBooksActivity.class);
         //  startActivity(startActivity);
            }
        });
      toggleButton();

    }
    private void toggleButton() {
        if (TextUtils.isEmpty(bookId)) {
            btnAdd.setText("Add");
        } else {
            btnAdd.setText("Update");
        }
    }
    /**
     * Creating new user node under 'users'
     */
    private void createAdmin(String book_email,String book_phone,String book_title) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(bookId)) {
            bookId = mFirebaseDatabase.push().getKey();
        }

        Book book = new Book(book_email,book_phone,book_title);

        mFirebaseDatabase.child(bookId).setValue(book);

       addUserChangeListener();
    }


    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(bookId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);

                // Check for null
                if (book== null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + book.book_email );



                Book_email.setText("");
Book_phone.setText("");
                Book_title.setText("");
                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

private void updateAdmin(String book_email ,String book_title,String book_phone) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(book_email))
            mFirebaseDatabase.child(bookId).child("book_email").setValue(book_email);
    if (!TextUtils.isEmpty(book_title))
        mFirebaseDatabase.child(bookId).child("book_title").setValue(book_title);
    if (!TextUtils.isEmpty(book_phone))
        mFirebaseDatabase.child(bookId).child("book_phone").setValue(book_phone);
    }
}
