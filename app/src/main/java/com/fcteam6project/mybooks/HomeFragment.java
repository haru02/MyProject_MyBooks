package com.fcteam6project.mybooks;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fcteam6project.mybooks.bookdata.BookSearchList;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView tv;
    String TAG = "HOME FRAGMENT";
    String isbnNum = "";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 책 검색 기능 - 제목 입력
        Button btnSearch = (Button) view.findViewById(R.id.btn_main_bookSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookSearchList.class);
                startActivity(intent);
            }
        });

        // 책 검색 기능 - 제목 입력
        Button btnSearchBarcode = (Button) view.findViewById(R.id.btn_main_bookSearchBarcode);
        btnSearchBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(getActivity()).initiateScan();
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
                Intent i = IntentIntegrator.forSupportFragment(HomeFragment.this).createScanIntent();
                startActivityForResult(i, IntentIntegrator.REQUEST_CODE);
            }
        });

        //TODO
        Button btnMybook = (Button) view.findViewById(R.id.btn_main_mybooks);
        btnMybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv = (TextView)view.findViewById(R.id.textView);

        return view;
    }

    private void checkResearchType(String types){

    }

    // 바코드를 스캔한 결과 값을 가져온다
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        tv.setText(result.getContents() + " [" + result.getFormatName() + "]");
        isbnNum = result.getContents();
        Intent intent = new Intent(getActivity(), BookSearchList.class);
        intent.putExtra("ISBN13", isbnNum);
        startActivity(intent);

    }

}
