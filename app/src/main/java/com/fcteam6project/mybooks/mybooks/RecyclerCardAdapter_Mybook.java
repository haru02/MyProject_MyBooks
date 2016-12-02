package com.fcteam6project.mybooks.mybooks;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.fcteam6project.mybooks.MainActivity;
import com.fcteam6project.mybooks.R;
import com.fcteam6project.mybooks.bookdata.BookDetail;
import com.fcteam6project.mybooks.bookdata.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by froze on 2016-11-13.
 */

public class RecyclerCardAdapter_Mybook extends RecyclerView.Adapter<RecyclerCardAdapter_Mybook.ViewHolder>{

    String TAG = "RecylerApapter";
    ArrayList<Item> datas = new ArrayList<>();
    int itemLayout;
    Context context;
    Item data;
    int mposition=0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myFavBook = database.getReference("user1").child("favorite");

    public RecyclerCardAdapter_Mybook(ArrayList<Item> datas, int itemLayout, Context context){
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mposition = position;
        data = datas.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvAuthor.setText(data.getAuthor_t());
        holder.tvTitle.setText(Html.fromHtml(data.getTitle()));

        String url = data.getCover_s_url();
        Glide.with(context).load(url).bitmapTransform(new CenterCrop(context)).into(holder.ivBookCover);

        // 카드를 클릭하면 카드가 이동하면서 detail 버튼, favorite 버튼을 보여준다
        holder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardView.setCardBackgroundColor(Color.YELLOW);
                setAnimationCard(holder.cardView, mposition);

                holder.btnUnFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnUnFavorite.setVisibility(View.INVISIBLE);
                        holder.btnFavorite.setVisibility(View.VISIBLE);
                        DatabaseReference myFabBooktemp = myFavBook.child(data.getIsbn13());
                        myFabBooktemp.setValue(data);
                    }
                });

                holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnFavorite.setVisibility(View.INVISIBLE);
                        holder.btnUnFavorite.setVisibility(View.VISIBLE);
                        myFavBook.child(data.getIsbn13()).removeValue();
                    }
                });

                holder.tvdetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), BookDetail.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("position", data);
                        intent.putExtras(bundle);
                        v.getContext().startActivity(intent);
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
        ImageButton btnFavorite, btnUnFavorite;
        TextView tvdetail;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_research);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_research_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_research_author);
            ivBookCover = (ImageView)itemView.findViewById(R.id.iv_research_bookcover);
            rlayout = (RelativeLayout)itemView.findViewById(R.id.activity_intro);
            btnFavorite = (ImageButton) itemView.findViewById(R.id.imgBtn_research_favorite);
            btnUnFavorite = (ImageButton) itemView.findViewById(R.id.imgBtn_research_unfavorite);
            tvdetail = (TextView) itemView.findViewById(R.id.tv_maincardview_todetail);
        }
    }
}
