/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * INGREDIENT Class
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

import android.os.Parcelable;
import android.os.Parcel;

public class Ingredient implements Parcelable {

    //creator - used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    //attributes of the class
    private String quantity;
    private String measure;
    private String ingredient;

    //constructor used for parcel
    //read and set saved values from parcel
    public Ingredient(Parcel parcel) {
        this.quantity = parcel.readString();
        this.measure = parcel.readString();
        this.ingredient = parcel.readString();
    }

    public Ingredient(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    //getters
    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //writeToParcel: write object values to parcel for storage
    //write all properties to the parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.quantity);
        parcel.writeString(this.measure);
        parcel.writeString(this.ingredient);
    }
} 