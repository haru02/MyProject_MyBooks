package com.fcteam6project.mybooks.bookdata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fcteam6project.mybooks.R;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BookSearchList extends AppCompatActivity {

    EditText et_booksearchlist_booktitle;
    Button btn_booksearchlist_Save;
    String bookName;
    ArrayList<Item> datas = new ArrayList<Item>();
    RecyclerCardAdapter readapter;
    String isbn13="";

    final String TAG = "BookSearchList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_list);

        et_booksearchlist_booktitle = (EditText)findViewById(R.id.et_booksearchlist_booktitle);
        btn_booksearchlist_Save = (Button)findViewById(R.id.btn_booksearchlist_Save);

        btn_booksearchlist_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookName = et_booksearchlist_booktitle.getText().toString().trim();
                callOpenApi(bookName);

            }
        });

        readapter = new RecyclerCardAdapter(datas, R.layout.maincardview_item, BookSearchList.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerCardView);
        recyclerView.setAdapter(readapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(BookSearchList.this);
        recyclerView.setLayoutManager(manager);

        // Homefragment에서 Barcode로 입력했을 때 검색 요청하는 함수
        if(getIntent() != null && getIntent().getExtras() != null) {
            isbn13 = getIntent().getStringExtra("ISBN13");
            callOpenApi(isbn13);
        }
    }

    public void callOpenApi(final String bookName){
        new AsyncTask<Void, Void, String>(){
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(BookSearchList.this);
                progress.setTitle("Download");
                progress.setMessage("downloading");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";
                //안드로이드에서 GET방식 URL에 데이터를 쓸때 데이터를 전달받은 jsp 서블릿에서 받은 데이터중 한글이 깨지는 형상 해결을 위해 UTF-8로 인코딩
                String bookNames_UTF8 = "";
                try {
                    bookNames_UTF8 = URLEncoder.encode(bookName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    String url = "https://apis.daum.net/search/book?apikey=7409be2030b47b7b30acc5b952fddd6b&q="+bookNames_UTF8+"&output=json";
                    result = Remote.getData(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                StringBuffer sb = new StringBuffer();

                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject topObject = json.getJSONObject("channel");
                    JSONArray item = topObject.getJSONArray("item");
                    int rows_count = item.length();
                    Log.i(TAG, "item length" + rows_count);
                    for (int i = 0; i < rows_count; i++) {
                        Item itemdata = new Item();
                        JSONObject result = (JSONObject) item.get(i);
                        String parseTitle = StringEscapeUtils.unescapeHtml4(result.getString("title"));
                        parseTitle = StringEscapeUtils.unescapeXml(parseTitle);
                        itemdata.setTitle(parseTitle);
                        itemdata.setAuthor_t(result.getString("author_t"));
                        itemdata.setDescription(result.getString("description"));
                        itemdata.setCategory(result.getString("category"));
                        itemdata.setCover_s_url(result.getString("cover_s_url"));
                        itemdata.setCover_l_url(result.getString("cover_l_url"));
                        itemdata.setIsbn13(result.getString("isbn13"));
                        datas.add(itemdata);
                    }
                    readapter.notifyDataSetChanged();
                }catch(Exception e){
                    e.printStackTrace();
                }
                progress.dismiss();
            }
        }.execute();
    }

    public String parseTitle(String s){
        String result = "";
        int idx = s.indexOf('&');
        if(idx == -1){
            return s;
        }else{
            while(idx<s.length()) {
                StringBuilder sb = new StringBuilder();
                String temp = s.substring(0, idx);
                sb.append(temp);
                temp = s.substring(idx);
            }
        }

        return result;
    }
}
