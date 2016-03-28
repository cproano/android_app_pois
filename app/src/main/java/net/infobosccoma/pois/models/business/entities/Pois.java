package net.infobosccoma.pois.models.business.entities;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carmita c g on 14/03/2016.
 */
@Parcel
public class Pois implements Parcelable {

    String name, latitude, longitude, city;

    public Pois(String name, String latitude, String longitude, String city) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Pois() {

    }

    protected Pois(android.os.Parcel in) {
        name = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        city = in.readString();
    }

    public static final Creator<Pois> CREATOR = new Creator<Pois>() {
        @Override
        public Pois createFromParcel(android.os.Parcel in) {
            return new Pois(in);
        }

        @Override
        public Pois[] newArray(int size) {
            return new Pois[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(city);
    }

    @SuppressWarnings("serial")
    @Parcel
    public static class Llista extends ArrayList<Pois> implements Parcelable {

        protected Llista(android.os.Parcel in) {

        }

        public Llista(){

        }
        public static final Creator<Llista> CREATOR = new Creator<Llista>() {
            @Override
            public Llista createFromParcel(android.os.Parcel in) {
                return new Llista(in);
            }

            @Override
            public Llista[] newArray(int size) {
                return new Llista[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
        }
    }
}
