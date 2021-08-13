package com.example.simplebookkeeping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //记一笔点击事件
    public void editClick(View view) {
        switch(view.getId()){
            case R.id.main_iv_search:
                break;
            case R.id.main_btn_edit:
                Intent it1 = new Intent(this,RecordActivity.class);//跳转界面
                startActivity(it1);
                break;
            case R.id.main_btn_more:
                break;
        }
    }

    //更多点击事件
    public void  moreClick(View view) {

    }

    //搜索点击事件
    public void  searchClick(View view) {

    }
}