package com.example.daniele.oossatuday1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

public class AlarmWidgetOss extends AppWidgetProvider {

    private Intent setAlarmIntent() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 8);

        Log.i(this.getClass().getSimpleName(), calendar.toString());

        Intent setAlarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        setAlarmIntent.putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR));
        setAlarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, Calendar.MINUTE);
        setAlarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Sveglia OSS");
        setAlarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

        return setAlarmIntent;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];
            // Create an Intent to launch ExampleActivity
            Intent intent = setAlarmIntent();
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.alarm_widget);
            views.setOnClickPendingIntent(R.id.widget_icon, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }
}
