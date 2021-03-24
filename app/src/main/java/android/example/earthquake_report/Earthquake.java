package android.example.earthquake_report;

public class Earthquake {
    private double magnitude;

    private String place;

    private long timeInMiliSeconds;

    private String url;

    public Earthquake(double Magnitude, String Place, long TimeInMiliSeconds, String Url)
    {
        this.magnitude = Magnitude;
        this.place = Place;
        this.timeInMiliSeconds = TimeInMiliSeconds;
        this.url = Url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getDateTime(){
        return timeInMiliSeconds;
    }

    public String getUrl() {
        return url;
    }
}
