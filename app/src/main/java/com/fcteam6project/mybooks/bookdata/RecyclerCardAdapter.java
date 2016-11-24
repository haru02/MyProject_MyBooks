package com.fcteam6project.mybooks.bookdata;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.fcteam6project.mybooks.R;

import java.util.ArrayList;

/**
 * Created by froze on 2016-11-13.
 */

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>{

    String TAG = "RecylerApapter";
    ArrayList<Item> datas = new ArrayList<>();
    int itemLayout;
    Context context;
    static RecyclerViewClickListener itemListener;


    public RecyclerCardAdapter(ArrayList<Item> datas, int itemLayout, Context context){
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    // view 를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    // listView getView 를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item data = datas.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvAuthor.setText(data.getAuthor_t());
        //holder.tvPubdate.setText(data.getPub_date());

        String url = data.getCover_s_url();
        Glide.with(context).load(url).bitmapTransform(new CenterCrop(context)).into(holder.ivBookCover);

        holder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardView.setCardBackgroundColor(Color.YELLOW);
                setAnimationCard(holder.cardView, position);

                holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BookDetail.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("position", data);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });

            }
        });

        setAnimation(holder.cardView, position);
    }

    int lastPosition = -1;

    public void setAnimationCard(View view, int position){
        ObjectAnimator ani = ObjectAnimator.ofFloat(view, "translationX", -210);
        ani.setDuration(1000);
        ani.start();

        ObjectAnimator ani2 = ObjectAnimator.ofFloat(view, "translationX", 0);
        ani2.setStartDelay(5000);
        ani2.setDuration(1000);
        ani2.start();
    }

    public void setAnimation(View view, int position){
        if(position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvAuthor;
        CardView cardView;
        ImageView ivBookCover;
        RelativeLayout rlayout;
        ImageButton btnFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_research);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_research_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_research_author);
            ivBookCover = (ImageView)itemView.findViewById(R.id.iv_research_bookcover);
            rlayout = (RelativeLayout)itemView.findViewById(R.id.activity_intro);
            btnFavorite = (ImageButton) itemView.findViewById(R.id.imgBtn_research_favorite);

            //itemView.setOnClickListener(this);
        }

/*        @Override
        public void onClick(View v) {
            //itemListener.recyclerViewListClicked(v, this.getAdapterPosition());
            int position = this.getAdapterPosition();
        }*/
    }

    public interface RecyclerViewClickListener
    {
        public void recyclerViewListClicked(View v, int position);
    }
}
