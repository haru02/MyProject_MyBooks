package com.fcteam6project.mybooks.bookdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.fcteam6project.mybooks.R;

public class BookDetail extends AppCompatActivity {

    ImageView bookcover;
    TextView title;
    TextView author;
    TextView pubdate;
    TextView pubname;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookcover = (ImageView)findViewById(R.id.iv_BookDetail_bookcover);
        title = (TextView)findViewById(R.id.tv_BookDetail_title);
        author = (TextView)findViewById(R.id.tv_BookDetail_author);
        pubdate = (TextView)findViewById(R.id.tv_BookDetail_pub_date);
        pubname = (TextView)findViewById(R.id.tv_BookDetail_pub_nm);
        description = (TextView)findViewById(R.id.tv_BookDetail_description);

        Intent intent = this.getIntent();
        Item item = (Item)intent.getExtras().get("position");

        if(intent!=null){
            title.setText(Html.fromHtml(item.getTitle()));
            author.setText(item.getAuthor_t());
            pubdate.setText(item.getPub_date());
            pubname.setText(item.getIsbn13());
            description.setText(item.getDescription());
            String url = item.getCover_l_url();
            Glide.with(BookDetail.this).load(url).bitmapTransform(new CenterCrop(BookDetail.this)).into(bookcover);
        }
    };


}
