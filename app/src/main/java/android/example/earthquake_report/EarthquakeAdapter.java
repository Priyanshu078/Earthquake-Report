package android.example.earthquake_report;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> words){
        super(context,0,words);
//        mColorResourceId = colorResourceId;
    }

    private String formatDate(Date object){
        SimpleDateFormat dateformat = new SimpleDateFormat("LLL dd, yyyy");
        return dateformat.format(object);
    }

    private String formatTime(Date object){
        SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
        return timeformat.format(object);
    }
    private String getLocation(String string){
        String formattedString = "";
        if(string.contains("of")){
            formattedString = string.substring(0,string.indexOf("of")+2);
        }
        else{
            formattedString = "Near the";
        }
        return formattedString;
    }
    private String getPlace(String string){
        String formattedString = "";
        if (string.contains("of")) {
            formattedString = string.substring(string.indexOf("of")+2);
        }
        else{
            formattedString = string;
        }
        return formattedString;
    }

    private String getMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    public int getMagnitudeColor(double mag){
        int magnitudeColorResourceId = (int) Math.floor(mag);
        switch (magnitudeColorResourceId){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list

        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView  magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
//        DecimalFormat formatter = new DecimalFormat("0.0");
//        String mag = formatter.format(currentEarthquake.getMagnitude());
        magnitude.setText(getMagnitude(currentEarthquake.getMagnitude()));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView location = (TextView) listItemView.findViewById(R.id.Location);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        String string = currentEarthquake.getPlace();
        String location_Part = getLocation(string);
        location.setText(location_Part);

        TextView place = (TextView) listItemView.findViewById(R.id.Place);
        String Place_Part = getPlace(string);
        place.setText(Place_Part);

        Date dateObject = new Date(currentEarthquake.getDateTime());
        TextView date = (TextView) listItemView.findViewById(R.id.Date);
        String formatDate = formatDate(dateObject);
        date.setText(formatDate);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView

        TextView time = (TextView) listItemView.findViewById(R.id.Time);
        String formatTime = formatTime(dateObject);
        time.setText(formatTime);


        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        int color = getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(color);

        return listItemView;
    }
}