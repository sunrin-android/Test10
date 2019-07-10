package com.example.test10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomHolder {
    ImageView profileIcon, call;
    TextView name, date;

    CustomHolder(View convertview) {
        profileIcon = convertview.findViewById(R.id.profileIcon);
        name = convertview.findViewById(R.id.name);
        date = convertview.findViewById(R.id.date);
        call = convertview.findViewById(R.id.call);
    }
}
