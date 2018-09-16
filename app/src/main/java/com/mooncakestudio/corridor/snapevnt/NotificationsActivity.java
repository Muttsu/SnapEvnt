package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationsActivity extends Activity {

    ListView notificationsList;
    Button addNotificationBtn;
    EditText notificationETxt;

    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    NotificationCompat.Builder notification;
    private static final int uniqueID = 2468;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        notificationsList = (ListView) findViewById(R.id.notificationsList);
        addNotificationBtn = (Button) findViewById(R.id.addChoiceBtn);
        notificationETxt = (EditText) findViewById(R.id.choiceETxt);

        names.add("Dinner with friends [6:15 PM]");
        names.add("Movie night [8:00 PM]");
        names.add("Meet up at park [3:00 PM]");
        names.add("Group study [10:30 AM]");
        names.add("Drinks tonight [11:00 PM]");

        // SET THE ADAPTER
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, names);
        notificationsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        notificationsList.setAdapter(adapter);

        // SET SELECTED ITEM
        notificationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notifyFirst(names.get(position));
                notificationProcess();
            }
        });

        addNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = notificationETxt.getText().toString();
                if(!s.isEmpty() && s.length() > 0){
                    // ADD
                    adapter.add(s);

                    // REFRESH
                    adapter.notifyDataSetChanged();
                    notificationETxt.setText("");


                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a subject", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }

    // --------------  NOTIFICATION POCESSING  --------------
    public void notificationProcess(){
        Calendar calendar = Calendar.getInstance();

        /*calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);*/

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void notifyFirst(String msg){
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setTicker("SnapEvnt");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("HACKER TROUP");
        notification.setContentText(msg);

        Intent intent = new Intent(this, NotificationsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
    }
}
