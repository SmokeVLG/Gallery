package ru.maxden.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;


public class GalleryActivity extends AppCompatActivity {
    public static final String LOG_TAG = GalleryActivity.class.getSimpleName();
    public static final String EXTRA_NAME = "images";
    public static final int COUNT_IMAGES_LOAD_IN_MEMORY = 6;

    private ArrayList<String> mImages;

    ViewPager mPager;
    LinearLayout mLinearLayout;
    ImageButton mCloseButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mPager = findViewById(R.id.pager);
        mLinearLayout = findViewById(R.id.miniature);
        mCloseButton = findViewById(R.id.btn_close);

        Intent intent = getIntent();
        if(intent!=null){

            mImages = (ArrayList<String>) intent.getSerializableExtra(EXTRA_NAME);
        }


        GalleryPagerAdapter mAdapter = new GalleryPagerAdapter(this);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(COUNT_IMAGES_LOAD_IN_MEMORY); // how many images to load into memory

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Закрытие.");
                finish();
            }
        });
    }

    class GalleryPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mInflater;

        GalleryPagerAdapter(Context context) {
            mContext = context;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            View itemView = mInflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);

            // Получить размер границы вокруг каждого изображения
            int borderSize = mLinearLayout.getPaddingTop();

            // Получить размер миниатюры
            int thumbnailSize = ((FrameLayout.LayoutParams)
                    mPager.getLayoutParams()).bottomMargin - (borderSize * 2);

            // Установка параметров миниатюры
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(thumbnailSize, thumbnailSize);
            params.setMargins(0, 0, borderSize, 0);

            final ImageView thumbView = new ImageView(mContext);
            thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            thumbView.setLayoutParams(params);
            thumbView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG, "Нажатие на миниатуюру снизу");
                    // Установка позиции pager, когда нажата миниатюра
                    mPager.setCurrentItem(position);
                }
            });
            mLinearLayout.addView(thumbView);

            final SubsamplingScaleImageView imageView =
                    itemView.findViewById(R.id.image);

            // Асинхронная загрузка миниатюр и установка текущей картинки
            Glide.with(mContext)
                    .asBitmap()
                    .load(mImages.get(position))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                            imageView.setImage(ImageSource.bitmap(bitmap));
                            thumbView.setImageBitmap(bitmap);
                        }
                    });


            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
