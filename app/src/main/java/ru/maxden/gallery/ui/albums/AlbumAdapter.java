package ru.maxden.gallery.ui.albums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import ru.maxden.gallery.R;
import ru.maxden.gallery.data.entity.AlbumModel;

public class AlbumAdapter extends ArrayAdapter<AlbumModel> {

    AlbumAdapter(@NonNull Context context, ArrayList<AlbumModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AlbumModel models = getItem(position);
        if (models != null) {
            holder.mUserId.setText(String.valueOf(models.getUserId()));
            holder.mTitle.setText(models.getTitle());
        }
        return convertView;
    }

    public class ViewHolder{
        private TextView mUserId, mTitle;

        ViewHolder(View view){
            mUserId = view.findViewById(R.id.user_id);
            mTitle = view.findViewById(R.id.title);
        }
    }
}
