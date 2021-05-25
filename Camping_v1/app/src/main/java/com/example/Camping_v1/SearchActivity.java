package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    ListView listView = null;          // 검색을 보여줄 리스트변수
    private EditText editTextFilter;        // 검색어를 입력할 Input 창
    private ListViewControl adapter;      // 리스트뷰에 연결할 아답터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new ListViewControl();
        listView = (ListView) findViewById(R.id.listView);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get TextView's Text
                String strText = (String)parent.getItemAtPosition(position);

            }
        });

        editTextFilter = (EditText) findViewById(R.id.editTextFilter);
        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString() ;
                ((ListViewControl)listView.getAdapter()).getFilter().filter(filterText);
            }
        });

        // 검색에 사용할 데이터을 미리 저장한다.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping1),
                "Sam Smith", "I'm not the only one.\r\nStay with me.\r\n"); ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping2),
                "Bryan Adams", "heaven.\r\nI do it for you.") ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping3),
                "Eric Clapton", "Tears in heaven.\r\nChange the world.") ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping3),
                "Gary Moore", "Still got the blues.\r\nOne day.") ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping4),
                "Helloween", "A tale that wasn't right.\r\nI want out.") ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping1),
                "Adele", "Hello.\r\nSomeone like you.") ;

    }

}