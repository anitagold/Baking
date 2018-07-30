/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * RECIPEDETAILACTIVITY Class
 *
 */

package hu.bubbanet.baking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import hu.bubbanet.baking.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    public static Recipe recipe;
    public static boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent.hasExtra("selected_recipe")) {
            Bundle bundle = intent.getExtras();
            recipe = bundle.getParcelable("selected_recipe");
        }

        setTitle(recipe.getName());

        DescriptionFragment descriptionFragment = new DescriptionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getResources().getBoolean(R.bool.isTablet)) {
            twoPane = true;

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, descriptionFragment)
                    .commit();

        } else {
            twoPane = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}