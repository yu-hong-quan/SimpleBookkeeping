package com.example.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.simplebookkeeping.R;

/*
* 自定义键盘逻辑层
* */
public class KeyBoardUtils {
    private final Keyboard k1;//自定义键盘
    private KeyboardView keyboardView;
    private EditText editText;

    //创建接口
    public interface OnEnsureListener{
        public void onEnsure();
    }
    //接口初始化
    OnEnsureListener onEnsureListener;
    public void setOnEnsureListener(OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }

    public KeyBoardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);//取消弹出系统键盘
        k1 = new Keyboard(this.editText.getContext(), R.xml.key);
        this.keyboardView.setKeyboard(k1);//设置要显示键盘的样式
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(true);
        this.keyboardView.setOnKeyboardActionListener(listener);  //设置键盘按钮被点击了的监听
    }

    //键盘点击监听逻辑层
    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {
        }

        @Override
        public void onRelease(int i) {
        }

        @Override
        public void onKey(int i, int[] ints) {
            Editable editable =  editText.getText();
            int start = editText.getSelectionStart();
            switch (i){
                case Keyboard.KEYCODE_DELETE://点击了删除键
                    if(editable!=null && editable.length()>0){
                        if(start>0){
                            editable.delete(start-1,start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL://点击了清零
                    editable.clear();
                    break;
                case Keyboard.KEYCODE_DONE://点击了完成
                    onEnsureListener.onEnsure();//通过调用接口回调的方法，当点击确定时可以调用这个方法
                    break;
                default://其他的数字直接插入
                    editable.insert(start,Character.toString((char)i));
                    break;
            }
        }

        @Override
        public void onText(CharSequence charSequence) {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeUp() {
        }
    };

    //显示键盘的方法
    public void showKeyboard(){
        int visibility = keyboardView.getVisibility();
        if(visibility == View.INVISIBLE || visibility == View.GONE){
            keyboardView.setVisibility(View.VISIBLE);
        }
    }
    //隐藏键盘的方法
    public void hideKeyboard(){
        int visibility = keyboardView.getVisibility();
        if(visibility == View.VISIBLE || visibility == View.INVISIBLE){
            keyboardView.setVisibility(View.GONE);
        }
    }
}
