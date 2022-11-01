package com.example.mp_assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                    AlertDialog.Builder ad = new AlertDialog.Builder(ListActivity.this);
                    ad.setIcon(R.mipmap.ic_launcher);
                    ad.setTitle("제목");
                    ad.setMessage("로그인이 필요합니다. 회원가입을 하시겠습니까?");

                    ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.v("test", "예 클릭");
                            Intent intent = new Intent(getApplicationContext(),
                                    RegisterActivity.class);
                            startActivity(intent);
                            dialogInterface.dismiss();
                        }
                    });

                    ad.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.v("test", "아니오 클릭");
                            dialogInterface.dismiss();
                        }
                    });

                    ad.show();
                }
            }
        });

    }
}
