package com.ajinkya.myapplication;

import android.content.Context;
import android.content.Intent;

public class Maths {

    public int addition(int a, int b) {
        return a + b;
    }

    public void loadChatBot(Context context) {
        Intent intent = new Intent(context, ChatScreen.class);
        context.startActivity(intent);
    }
}
