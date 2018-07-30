/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * RECIPE Class
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

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    //creator - used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Recipe createFromParcel(Parcel parcel) {

            return new Recipe(parcel);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    //attributes of the class
    private int id;
    private String name;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;
    private String servings;
    private String imageStr;

    public Recipe(int id, String name, List<Ingredient> ingredientList, List<Step> stepList, String servings, String imageStr) {
        this.id = id;
        this.name = name;
        this.ingredientList = ingredientList;
        this.stepList = stepList;
        this.servings = servings;
        this.imageStr = imageStr;
    }

    //constructor used for parcel
    //read and set saved values from parcel
    public Recipe(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        ingredientList = new ArrayList<>();
        parcel.readList(ingredientList, Ingredient.class.getClassLoader());
        stepList = new ArrayList<>();
        parcel.readList(stepList, Step.class.getClassLoader());
        this.servings = parcel.readString();
        this.imageStr = parcel.readString();
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public String getServings() {
        return servings;
    }

    public String getImageStr() {
        return imageStr;
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
        parcel.writeString(this.name);
        parcel.writeList(this.ingredientList);
        parcel.writeList(this.stepList);
        parcel.writeString(this.servings);
        parcel.writeString(this.imageStr);
    }

} 