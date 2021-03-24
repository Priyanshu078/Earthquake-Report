package android.example.earthquake_report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    public static final String LOG_TAG = MainActivity.class.getName();
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Create an ArrayList of AndroidFlavor objects
    ArrayList<Earthquake> earthQuakes = QueryUtils.extractEarthquakes();
//    earthQuakes.add(new Earthquake("7.2", "San Francisco","Feb 2,2016"));
//    earthQuakes.add(new Earthquake("6.1", "London","July 20,2015"));
//    earthQuakes.add(new Earthquake("3.9", "Tokyo","Nov 10,2014"));
//    earthQuakes.add(new Earthquake("5.4", "Mexico City","May 3,2014"));
//    earthQuakes.add(new Earthquake("2.8", "Moscow","Jan 31,2013"));
//    earthQuakes.add(new Earthquake("4.9", "Rio de Janeiro","August 19,2012"));
//    earthQuakes.add(new Earthquake("1.6", "Paris","October 30,2011"));

    // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
    // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
    // in the list.
    EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthQuakes);

    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Find the current earthquake that was clicked on
            Earthquake currentEarthquake = adapter.getItem(position);

            // Convert the String URL into a URI object (to pass into the Intent constructor)
            Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

            // Create a new intent to view the earthquake URI
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

            // Send the intent to launch a new activity
            startActivity(websiteIntent);
        }
    });
}
}