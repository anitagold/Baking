/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * MAINACTIVITY Class
 *
 */

package hu.bubbanet.baking;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bubbanet.baking.model.Recipe;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeItemClickListener {

    final static String BAKING_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @BindView(R.id.error_message)
    TextView errorMessage;
    @BindView(R.id.recipes_recyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecipeAdapter recipeAdapter;
    boolean isInTabletLandscape;
    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        isInTabletLandscape = getResources().getBoolean(R.bool.isTabletLandscape);

        new RecipesAsyncTask(this).execute();
    }

    @Override
    public void onListItemClick(int actualItemIndex) {

        Recipe actualRecipe = recipeList.get(actualItemIndex);

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra("selected_recipe", actualRecipe);
        startActivity(intent);

    }

    public class RecipesAsyncTask extends AsyncTask<Void, Void, List<Recipe>> {

        private Context context;

        RecipesAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<Recipe> doInBackground(Void... voids) {

            URL recipeUrl = null;

            try {
                recipeUrl = new URL(BAKING_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                String recipeJson = JsonHelper.getResponseFromHttpUrl(recipeUrl);
                recipeList = JsonHelper.extractFeatureFromJson(recipeJson);
                return recipeList;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {

            if (recipeList != null) {

                errorMessage.setVisibility(View.INVISIBLE);

                if (isInTabletLandscape) {
                    gridLayoutManager = new GridLayoutManager(context, 3);
                    recyclerView.setLayoutManager(gridLayoutManager);

                } else {
                    linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }

                recipeAdapter = new RecipeAdapter(recipeList, MainActivity.this);
                recyclerView.setAdapter(recipeAdapter);


            } else {
                errorMessage.setVisibility(View.VISIBLE);
                errorMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new RecipesAsyncTask(MainActivity.this).execute();
                    }
                });

            }


        }
    }


}
