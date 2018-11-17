package com.example.yechankim.teamwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.security.SecureRandom;

import java.util.ArrayList;

class DictItem {
    int key;
    int value;

    DictItem(int k, int v) {
        key = k;
        value = v;
    }
}

public class ScreenActivity2 extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
    private int i;

    private LinearLayout leftcode1, rightcode1;
    private int[] mixedNumbersSet; //ArrayList<Integer> mixedNumbersSet;

    private String program;
    private int codesLength;

    private ArrayList<DictItem> studentAnswerKit;

    /* 김예찬 정의 함수 */

    public String getProgramFromSQL() {
        // 추후에 SQL 연동 코드를 작성해야 합니다. - yckim

        String str =    "def fib(n):\n" +
                        "     a, b = 0, 1\n" +
                        "     while a < n:\n" +
                        "         print(a, end=' ')\n" +
                        "         a, b = b, a+b\n" +
                        "     print()";

        return str;
    }

    public String[] getSplicedCodeByNewLine(String program) {
        return program.split("\n");
    }

    public void makeMixedNumbersSet() {
        mixedNumbersSet = new int[this.codesLength];
        for (int i = 0 ; i < this.codesLength ; i += 1) {
            mixedNumbersSet[i] = i;
        }

        for (int first = 0 ; first < this.codesLength ; first += 1) {
            int second = new SecureRandom().nextInt(codesLength);

            int temp = mixedNumbersSet[first];
            mixedNumbersSet[first] = mixedNumbersSet[second];
            mixedNumbersSet[second] = temp;
        }

        /*
        rightCodeSequence = new DictItem[codesLength];
        for (int i = 0 ; i < codesLength ; i += 1) {
            rightCodeSequence[i] = new DictItem(i, mixedNumbersSet[i]);
        } */
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BlueTheme);
        setTitle("코드 빈칸 채우기");

        // 상단 상태 바 없애기 -> 하단 코드
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_screen2);

        leftcode1 = (LinearLayout) findViewById(R.id.leftcode1);
        rightcode1 = (LinearLayout) findViewById(R.id.rightcode1);

        program = getProgramFromSQL();
        String[] codes = getSplicedCodeByNewLine(program);
        codesLength = codes.length;
        makeMixedNumbersSet();

        studentAnswerKit = new ArrayList<>();

        for (i = 0 ; i < codes.length ; i++) {
            TextView tempTV = new TextView(this);
            tempTV.setId(mixedNumbersSet[i]);
            tempTV.setText(codes[mixedNumbersSet[i]]);
            tempTV.setOnTouchListener(this);
            tempTV.setBackgroundColor(getColor(R.color.yechanDarkBlue));
            tempTV.setPadding(20,20,20,20);
            tempTV.setWidth(leftcode1.getMeasuredWidth() - 100);

            leftcode1.addView(tempTV);
        }

        for (i = 0 ; i < codes.length ; i++) {
            TextView tempTV = new TextView(this);
            tempTV.setId(i);
            tempTV.setText("Drag Listener test " + i);
            tempTV.setOnDragListener(this);
            tempTV.setBackgroundColor(getColor(R.color.yechanDarkBlue));
            tempTV.setPadding(20,20,20,20);
            tempTV.setWidth(rightcode1.getMeasuredWidth() - 100);

            rightcode1.addView(tempTV);
        }

        //
        TextView t = new TextView(this);
        rightcode1.addView(t);

        Button b = new Button(this);
        rightcode1.addView(b);

        b.setText("테스트");
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String var = "";

                for(int i = 0 ; i < codesLength ; i++) {
                    studentAnswerKit;
                    // [i] = [i];
                }

                //var += "";

                //t.setText();
            }
        });

        //ar.get(2).setOnTouchListener(this);

        /*
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);

        // Setting touch and drag listeners
        text1.setOnTouchListener(this);
        text2.setOnTouchListener(this);
        text3.setOnTouchListener(this);

        text4.setOnTouchListener(this);
        text5.setOnTouchListener(this);
        text6.setOnTouchListener(this);

        text4.setOnDragListener(this);
        text5.setOnDragListener(this);
        text6.setOnDragListener(this);
        */

    }

    //When touched text gets dropped into either text4 or text5 or text6 then this method will be called
    @Override
    public boolean onDrag(View v, DragEvent event) {
        if ( event.getAction() == DragEvent.ACTION_DROP ) {
            // 타겟 뷰에 드롭 오버된 드래그된 뷰를 핸들링 => 하단 2개 코드
            TextView dropped = (TextView) event.getLocalState();
            TextView dropTarget = (TextView) v;

            dropped.setVisibility(View.INVISIBLE);

            //

            dropTarget.setText(dropped.getText());
            dropTarget.setBackgroundColor(Color.RED);
        }

        return true;
    }

    // 드래그가 시작되면, 그 trace를 화면에 print하는 메소드.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);

            return true;
        }

        return false;
    }


}

        /*
            package com.example.yechankim.teamwork;

            import android.graphics.Color;
            import android.support.v7.app.AppCompatActivity;
            import android.os.Bundle;
            import android.view.DragEvent;
            import android.view.MotionEvent;
            import android.view.View;
            import android.widget.TextView;
            import android.widget.Toast;

            public class ScreenActivity2 extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
                private TextView text1, text2, text3, text4, text5, text6;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    text1 = (TextView) findViewById(R.id.text1);
                    text2 = (TextView) findViewById(R.id.text2);
                    text3 = (TextView) findViewById(R.id.text3);
                    text4 = (TextView) findViewById(R.id.text4);
                    text5 = (TextView) findViewById(R.id.text5);
                    text6 = (TextView) findViewById(R.id.text6);

                    // Setting touch and drag listeners
                    text1.setOnTouchListener(this);
                    text2.setOnTouchListener(this);
                    text3.setOnTouchListener(this);
                    text4.setOnDragListener(this);
                    text5.setOnDragListener(this);
                    text6.setOnDragListener(this);
                }

                //When touched text gets dropped into either text4 or text5 or text6 then this method will be called
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    if ( event.getAction() == DragEvent.ACTION_DROP ) {
                        // 타겟 뷰에 드롭 오버된 드래그된 뷰를 핸들링 => 하단 2개 코드
                        TextView dropped = (TextView)event.getLocalState();
                        TextView dropTarget = (TextView) v;

                        // 드롭된 뷰에 대한 디스플레이 중단 => 하단 1개 코드
                        dropped.setVisibility(View.INVISIBLE);

                        // if an item has already been dropped here, there will be different string
                        String text=dropTarget.getText().toString();
                        //if there is already an item here, set it back visible in its original place
                        if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
                        else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
                        else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);

                        //update the text and color in the target view to reflect the data being dropped
                        dropTarget.setText(dropped.getText());
                        dropTarget.setBackgroundColor(Color.BLUE);
                    }

                    return true;
                }

                //When text1 or text2 or text3 gets clicked or touched then this method will be called
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction()==MotionEvent.ACTION_DOWN)
                    {
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(null, shadowBuilder, v, 0);
                        return true;
                    }
                    else return false;
                }

            }

         */