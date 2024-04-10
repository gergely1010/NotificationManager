package com.example.notificationmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "simplified_coding";
    private static final int NOTIFICATION_ID1 = 101;
    private static final int NOTIFICATION_ID2 = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button notifyButton = findViewById(R.id.btn_notification1);
        Button notifyButton2 = findViewById(R.id.btn_notification2);
        notifyButton.setOnClickListener(v -> {
            DisplayNotifications(NOTIFICATION_ID1);
        });

        notifyButton2.setOnClickListener(v -> {
            DisplayNotifications(NOTIFICATION_ID2);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Channel 1";
            String description = "This is a test notification channel 2";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class) ;
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void DisplayNotifications(int notification_id) {

        Intent intent=new Intent(getApplicationContext(), this.getClass());
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,
                CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_pest_control_rodent_24)
                .setContentTitle("Go to work you lazy monkey")
                .setContentText("Message call to work")

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Actions", pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notification_id, mBuilder.build());
    }


}