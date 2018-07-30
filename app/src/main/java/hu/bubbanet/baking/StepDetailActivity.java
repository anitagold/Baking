/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * STEPDETAILACTIVITY Class
 *
 */
package hu.bubbanet.baking;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import hu.bubbanet.baking.model.Step;

public class StepDetailActivity extends AppCompatActivity {

    Step actualStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().hasExtra("actualStep")) {

            actualStep = getIntent().getParcelableExtra("actualStep");

            setTitle(actualStep.getShortDescription());

            if (savedInstanceState == null) {
                DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(actualStep);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, descriptionFragment)
                        .commit();
            }

        }

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600 &&
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Intent intent = new Intent(this, RecipeDetailActivity.class);
            intent.putExtra("actualStep", actualStep);
            startActivity(intent);

            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}