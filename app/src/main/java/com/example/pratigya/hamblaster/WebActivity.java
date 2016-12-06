package com.example.pratigya.hamblaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class WebActivity extends AppCompatActivity {

  //  private ListView listView;
    private String names[] = {
            "codechef","hackerrank"//,"hackerearth","topcoder"

    };

    private String desc[] = {
            "",
            ""
        //    "",
           // ""

    };


    private Integer imageid[] = {
            R.drawable.codechefmod,
            R.drawable.hackerrank

         //   R.drawable.hackerearth,
           // R.drawable.topcoder,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        CustomList customList = new CustomList(this, names, desc, imageid);

        //Create ListView object and cast the listView
        ListView listView = (ListView) findViewById(R.id.listView);

        //create array adapter then set the adapter with listView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String friend = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(), "Hello " + friend + " !", Toast.LENGTH_SHORT).show();

                if (i == 0) {
                    Intent s = new Intent(view.getContext(), Webview.class);
                    startActivity(s);
                }

                //another way to go to the activity
                if (i == 1) {
                    Intent n = new Intent(view.getContext(), webview2.class);
                    startActivity(n);
                }

             //   if (i == 3) {
                    /*Intent s = new Intent(view.getContext(), webview3.class);
                    startActivity(s);
                }

                //another way to go to the activity
                if (i == 4) {
                    Intent n = new Intent(view.getContext(), webview4.class);
                    startActivity(n);
                }*/

            }
        });
    }
}
