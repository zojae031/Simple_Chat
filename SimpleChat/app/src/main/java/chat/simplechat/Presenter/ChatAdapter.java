package chat.simplechat.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import chat.simplechat.Model.ChatVO;
import chat.simplechat.R;

class ChatAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ChatVO> chatData;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    private String userId;
    public ChatAdapter(Context context, int layout, ArrayList<ChatVO> list,String userid) {
        this.context = context;
        this.layout = layout;
        this.chatData = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userId = userid;
    }

    @Override
    public int getCount() {
        return chatData.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.user_id =(TextView)convertView.findViewById(R.id.user_id);
            viewHolder.target_id = (TextView)convertView.findViewById(R.id.target_id);
            viewHolder.user_msg = (TextView)convertView.findViewById(R.id.user_msg);
            viewHolder.target_msg = (TextView)convertView.findViewById(R.id.target_msg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if(chatData.get(position).getId().equals(userId)){ //내 아이디라면
            viewHolder.user_id.setText(chatData.get(position).getId());
            viewHolder.user_msg.setText(chatData.get(position).getText());
            viewHolder.target_msg.setVisibility(View.INVISIBLE);
            viewHolder.target_id.setVisibility(View.INVISIBLE);
        }
        else{
            viewHolder.target_id.setText(chatData.get(position).getId());
            viewHolder.target_msg.setText(chatData.get(position).getText());
            viewHolder.user_id.setVisibility(View.INVISIBLE);
            viewHolder.user_msg.setVisibility(View.INVISIBLE);
        }



        return convertView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return chatData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView user_id;
        TextView target_id;
        TextView user_msg;
        TextView target_msg;
    }
}