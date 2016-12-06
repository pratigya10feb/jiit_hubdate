package com.example.pratigya.hamblaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminViewActivity extends AppCompatActivity {
private Button delete;
    private Button add;
    private Button viewuser;
    private Button ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        add=(Button)findViewById(R.id.button7);
        add.setOnClickListener(new View.OnClickListener()
                                 {
                                     public void onClick(View v)
                                     {
                                         Intent i=new Intent(getApplicationContext(),AddAdminActivity.class);
                                         startActivity(i);
                                     }
                                 }
        );
        ad=(Button)findViewById(R.id.button8);
        ad.setOnClickListener(new View.OnClickListener()
                               {
                                   public void onClick(View v)
                                   {
                                       Intent i=new Intent(getApplicationContext(),InputActivity.class);
                                       startActivity(i);
                                   }
                               }
        );
        delete=(Button)findViewById(R.id.button4);
        delete.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(getApplicationContext(),DeleteEventActivity.class);
                                      startActivity(i);
                                  }
                              }
        );
        viewuser=(Button)findViewById(R.id.button6);
        viewuser.setOnClickListener(new View.OnClickListener()
                                  {
                                      public void onClick(View v)
                                      {
                                          Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                                          startActivity(i);
                                      }
                                  }
        );
    }
}
