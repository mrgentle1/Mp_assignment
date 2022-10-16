package com.example.mp_assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        Intent intent = getIntent();
        setTitle("3페이지 (상품리스트 화면)");

        Button userinfo = (Button) findViewById(R.id.btn_userinfo);

        userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent.hasExtra("signed")){
                    String userdata = intent.getStringExtra("signed");
                    // [0]: id, [1]: pwd, [2]: name [3]: number [4]: address
                    String[] userarray = userdata.split(" ");
                    Context context = getApplicationContext();
                    CharSequence text = "로그인중인 아이디: "+userarray[0];

                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                }
                else{

                }
            }
        });

    }
}
