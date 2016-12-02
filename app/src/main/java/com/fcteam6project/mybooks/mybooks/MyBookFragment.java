package com.fcteam6project.mybooks.mybooks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcteam6project.mybooks.R;
import com.fcteam6project.mybooks.bookdata.BookSearchList;
import com.fcteam6project.mybooks.bookdata.Item;
import com.fcteam6project.mybooks.bookdata.RecyclerCardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookFragment extends Fragment {

    RecyclerCardAdapter_Mybook readapter;
    ArrayList<Item> datas;
    String TAG = "MyBookFragment";
    View view;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myFavBook = database.getReference("user1").child("favorite");

    public MyBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_book, container, false);

        //firebase로부터 favoritebook data를 가지고 온다
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datas = new ArrayList<Item>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    datas.add(item);
                }
                readapter = new RecyclerCardAdapter_Mybook(datas, R.layout.maincardview_item, getActivity());
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_mybookfra);
                recyclerView.setAdapter(readapter);

                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                readapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        myFavBook.addValueEventListener(postListener);

        return view;
    }
}

class MyFavBook  implements Serializable {

    String title;
    String author_t;
    String description;
    String category;
    String cover_s_url;
    String cover_l_url;
    String isbn13;
    String pub_date;

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public MyFavBook(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_t() {
        return author_t;
    }

    public void setAuthor_t(String author_t) {
        this.author_t = author_t;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCover_s_url() {
        return cover_s_url;
    }

    public void setCover_s_url(String cover_s_url) {
        this.cover_s_url = cover_s_url;
    }

    public String getCover_l_url() {
        return cover_l_url;
    }

    public void setCover_l_url(String cover_l_url) {
        this.cover_l_url = cover_l_url;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
