package com.example.mydb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;



public class myAdapter extends ArrayAdapter {

    private final int ImageId;

    public myAdapter(Context context, int headImage, List<myBean> obj) {
        super(context, headImage, obj);
        ImageId = headImage;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final myBean myBean = (myBean) getItem(position);
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(ImageId, parent, false);
            viewHolder.fruitImage = view.findViewById(R.id.headimage);
            viewHolder.fruitName = view.findViewById(R.id.headtext);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.fruitImage.setImageResource(myBean.getImageID());
        viewHolder.fruitName.setText(myBean.getText());
        return view;
    }
}
class ViewHolder{
    ImageView fruitImage;
    TextView fruitName;
}
