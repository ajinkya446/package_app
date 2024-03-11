package com.ajinkya.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajinkya.myapplication.R;
import com.ajinkya.myapplication.model.ChatResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageList extends RecyclerView.Adapter<MessageList.MessageListViewHolder> {
    ArrayList<Map<String, Object>> chatMessageList;
    public Context context;

    public MessageList(ArrayList<Map<String, Object>> chatMessageList, Context context) {
        this.chatMessageList = chatMessageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListViewHolder holder, int position) {
        boolean senderFlag = false;
        ChatResponse chatResponse = null;
        Map<String, Object> mapData = new HashMap<>();
        mapData = chatMessageList.get(position);
        Log.d("TAG", "onBindViewHolder: "+String.valueOf(position)+"  "+chatMessageList.get(position));
//        list.get("sender");

//        for (Map<String, Object> entry : chatMessageList) {
        senderFlag = (boolean) mapData.get("sender");
        chatResponse = (ChatResponse) mapData.get("user");

        if (senderFlag) {
            holder.cardViewSender.setVisibility(View.VISIBLE);
            holder.cardViewReceiver.setVisibility(View.INVISIBLE);
            assert chatResponse != null;
            holder.textViewSender.setText(chatResponse.getQuestion().toString());
        } else {
            holder.cardViewSender.setVisibility(View.INVISIBLE);
            holder.cardViewReceiver.setVisibility(View.VISIBLE);
            assert chatResponse != null;
            holder.textViewReceiver.setText(chatResponse.getAnswer().toString());
        }
//        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public class MessageListViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewSender, cardViewReceiver;
        TextView textViewSender, textViewReceiver;

        public MessageListViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewReceiver = itemView.findViewById(R.id.receiverMessage);
            cardViewSender = itemView.findViewById(R.id.senderMessage);
            textViewSender = itemView.findViewById(R.id.senderText);
            textViewReceiver = itemView.findViewById(R.id.receiverText);
        }
    }
}
