package ru.maxden.gallery.ui.albums.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ru.maxden.gallery.R;
import ru.maxden.gallery.data.entity.PhotoModel;

public class PhotosAdapter extends ArrayAdapter<PhotoModel> {

    private ArrayList<PhotoModel> mArrayList;
    private Context mContext;

    public PhotosAdapter(@NonNull Context context, ArrayList<PhotoModel> arrayList) {
        super(context, 0, arrayList);
        this.mArrayList = arrayList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PhotoModel model = getItem(position);
        if (model != null) {
            Glide.with(getContext())
                    .load(model.getUrl())
                    .into(holder.mImageView);
            holder.mTitle.setText(model.getTitle());
        }
        return convertView;
    }

    public class ViewHolder{
        private ImageView mImageView;
        private TextView mTitle;

        public ViewHolder (View view) {
            mImageView = view.findViewById(R.id.iv_photo);
            mTitle = view.findViewById(R.id.tv_title);
        }
    }
}
