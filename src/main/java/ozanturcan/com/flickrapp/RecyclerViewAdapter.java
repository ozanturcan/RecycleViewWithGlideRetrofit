package ozanturcan.com.flickrapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ozanturcan.com.flickrapp.Models.Photo;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Photo> photoList;
    private boolean loading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    public RecyclerViewAdapter(Context mContext, List<Photo> photoList) {
        this.mContext = mContext;
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_holder,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ph_title.setText(photoList.get(position).getTitle());
//        holder.ph_thumbnail_img.setImageURI( GetImageBitmapFromUrl(photoList.get(position).getUrlM()));
        Glide.with(mContext.getApplicationContext()).load(photoList.get(position).getUrlM()).into(holder.ph_thumbnail_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,PhotoActivity.class);

                // passing data to the book activity
                intent.putExtra("Title", photoList.get(position).getTitle());
                intent.putExtra("Description", photoList.get(position).getDescription().content);
                intent.putExtra("Thumbnail", photoList.get(position).getUrlM());
                // start the activity
                mContext.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return photoList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ph_title;
        ImageView ph_thumbnail_img;
        CardView cardView ;
        public ViewHolder(View ItemView){
            super(ItemView);
            ph_title = (TextView) itemView.findViewById(R.id.photo_title_id);
            ph_thumbnail_img = (ImageView) itemView.findViewById(R.id.photo_thumb_id);
            cardView  = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
    public void setLoad(){
        loading = false;
    }
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }
    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    public void setLoaded() {
        loading = false;
    }
    public void clear() {
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
    public void addAll() {
        notifyDataSetChanged();
    }
}
