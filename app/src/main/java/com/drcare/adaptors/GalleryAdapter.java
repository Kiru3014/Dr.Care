package com.drcare.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scanning.drcare.R;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    LayoutInflater inflater = null;
    Context context;
    OnItemClickListener mOnItemClickListener;
    String[] images;


    public GalleryAdapter(Context applicationContext, String[] images) {
        this.context = applicationContext;
        this.images=images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.grid_single, parent, false);
        int bannerheight = (int) (this.context.getResources().getDisplayMetrics().widthPixels / 3.3);
        SimpleDraweeView img = view.findViewById(R.id.my_image_view);
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.height = bannerheight;
        img.setLayoutParams(params);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, @SuppressLint("RecyclerView") final int position) {

        vh.banner.setImageURI(images[position]);

        vh.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.galleryclick(position);
            }
        });

    }

    public interface OnItemClickListener {
        void galleryclick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView banner;

        public ViewHolder(View view) {
            super(view);
            banner = view.findViewById(R.id.my_image_view);

        }
    }
}
