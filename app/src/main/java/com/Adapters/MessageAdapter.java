package com.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.models.Chat;
import com.seesame.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {


    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Chat> mChat;
    private String imageurl, senderId;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat, String senderId) {
        this.mChat = mChat;
        this.mContext = mContext;
        this.senderId = senderId;
    }


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {


        Chat chat = mChat.get(position);


        if (chat.getMessage().contains("SeeSame")) {
            holder.profile_image.setVisibility(View.GONE);
            holder.show_message.setText(chat.getMessage());
            holder.show_message.setBackgroundResource(R.drawable.btnsmall_bg);
            holder.show_message.setGravity(Gravity.CENTER);
            holder.txt_seen.setVisibility(View.GONE);
            holder.show_message.setTextColor(Color.parseColor("#FFFFFF"));

        } else {
            holder.show_message.setText(chat.getMessage());
        }



     /*   if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }
*/
        if (position == mChat.size() - 1) {
            if (chat.isIsseen()) {
                holder.txt_seen.setText("Seen");
            } else {
                holder.txt_seen.setText("Delivered");
            }
        } else {
            holder.txt_seen.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {

        //  Toast.makeText(mContext, "Count " + mChat.size(), Toast.LENGTH_SHORT).show();
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;

        public ViewHolder(View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            txt_seen = itemView.findViewById(R.id.txt_seen);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }


    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        //fuser.getUid())

        if (mChat.get(position).getSender().equals(senderId)) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

}
