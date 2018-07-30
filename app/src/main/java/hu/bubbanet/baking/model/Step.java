/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * STEP Class
 *
 * Android Parcelables made easy through code generation. http://parceler.org
 * Parcelables are a great way to serialize Java Objects between Contexts.
 * Compared with traditional Serialization, Parcelables take on the order of 10x less time
 * to both serialize and deserialize.
 * EXAMPLE: https://www.sitepoint.com/transfer-data-between-activities-with-android-parcelable/
 * IMPORTANT: The order in which you write the values in Parcel is important.
 * When collecting these values later you will need to collect them in the same order!
 */
package hu.bubbanet.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    //creator - used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Step createFromParcel(Parcel parcel) {
            return new Step(parcel);
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    //attributes of the class
    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

    //constructor used for parcel
    //read and set saved values from parcel
    public Step(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoUrl = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //writeToParcel: write object values to parcel for storage
    //write all properties to the parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.id);
        parcel.writeString(this.shortDescription);
        parcel.writeString(this.description);
        parcel.writeString(this.videoUrl);
        parcel.writeString(this.thumbnailUrl);
    }
}

