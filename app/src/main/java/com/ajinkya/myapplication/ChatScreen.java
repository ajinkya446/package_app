package com.ajinkya.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ajinkya.myapplication.adapters.MessageList;
import com.ajinkya.myapplication.databinding.ActivityChatScreenBinding;
import com.ajinkya.myapplication.model.ChatResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatScreen extends AppCompatActivity {
    ActivityChatScreenBinding activityChatScreenBinding;
    ArrayList<Map<String, Object>> chatMessageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat_screen);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("eKrushi AI");
        activityChatScreenBinding.indicator.setVisibility(View.INVISIBLE);
        activityChatScreenBinding.sendBtn.setOnClickListener(v -> {
            if (activityChatScreenBinding.textField.getText().toString().equals("") || activityChatScreenBinding.textField.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Message...", Toast.LENGTH_SHORT).show();
            } else {
                activityChatScreenBinding.indicator.setVisibility(View.VISIBLE);
                activityChatScreenBinding.recyclerChatView.setVisibility(View.INVISIBLE);
                ChatResponse chatResponse = new ChatResponse("", "", "English", activityChatScreenBinding.textField.getText().toString(), "");
                Map<String, Object> mapList = new HashMap<>();
                mapList.put("user", chatResponse);
                mapList.put("sender", true);
                chatMessageList.add(mapList);
                MessageList messageList = new MessageList(chatMessageList, this);
                activityChatScreenBinding.recyclerChatView.setLayoutManager(
                        new LinearLayoutManager(this));
                activityChatScreenBinding.recyclerChatView.setAdapter(messageList);


                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api1.kissangpt.com/").addConverterFactory(GsonConverterFactory.create()).build();
                APIService apiService = retrofit.create(APIService.class);

                Call<ChatResponse> apiCall = apiService.getChatResponse("English", activityChatScreenBinding.textField.getText().toString());
                apiCall.enqueue(new Callback<ChatResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ChatResponse> call, @NonNull Response<ChatResponse> response) {
                        if (response.isSuccessful()) {
                            Map<String, Object> mapList = new HashMap<>();
                            mapList.put("user", response.body());
                            mapList.put("sender", false);
                            chatMessageList.add(mapList);
                            MessageList messageList1 = new MessageList(chatMessageList, ChatScreen.this);
                            activityChatScreenBinding.recyclerChatView.setAdapter(messageList1);
                            activityChatScreenBinding.textField.setText("");
                            activityChatScreenBinding.indicator.setVisibility(View.INVISIBLE);
                            activityChatScreenBinding.recyclerChatView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ChatResponse> call, Throwable t) {
                        activityChatScreenBinding.indicator.setVisibility(View.INVISIBLE);
                        activityChatScreenBinding.recyclerChatView.setVisibility(View.VISIBLE);
                        Toast.makeText(ChatScreen.this, "Task Failed while processing...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}