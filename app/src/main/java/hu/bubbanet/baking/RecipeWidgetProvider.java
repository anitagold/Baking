/**
 * Created by Anita Goldpergel (anita.goldpergel@gmail.com) on 2018.07.26.
 *
 * * * RECIPEWIDGETPROVIDER Class
 *
 */

package hu.bubbanet.baking;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("selected_ingredients", 0);
        CharSequence widgetText = sharedPreferences.getString("ingredientsToWidget", "You haven't added any ingredients list yet.");
        CharSequence recipeName = sharedPreferences.getString("recipeName", " ");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        remoteViews.setTextViewText(R.id.appwidget_text, widgetText);
        remoteViews.setTextViewText(R.id.appwidget_title, recipeName);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}

