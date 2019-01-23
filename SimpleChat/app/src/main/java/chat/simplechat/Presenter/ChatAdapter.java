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

    public ChatAdapter(Context context, int layout, ArrayList<ChatVO> list) {
        this.context = context;
        this.layout = layout;
        this.chatData = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            viewHolder.tv_id =(TextView)convertView.findViewById(R.id.chat_id);
            viewHolder.tv_text = (TextView)convertView.findViewById(R.id.chat_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.tv_text.setText(chatData.get(position).getText());
        viewHolder.tv_id.setText(chatData.get(position).getId());


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
        TextView tv_id;
        TextView tv_text;
    }
}