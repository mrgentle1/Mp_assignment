package com.example.mp_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1페이지 (로그인 화면)");

        EditText id = (EditText) findViewById(R.id.input_id);
        EditText password = (EditText) findViewById(R.id.input_password);

        Button main_button = (Button) findViewById(R.id.main_button);
        Button login_button = (Button) findViewById(R.id.login_button);
        Button register_button = (Button) findViewById(R.id.register_button);
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ListActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String user_id = id.getText().toString();
                String user_pwd = password.getText().toString();

//                Log.v("test", user_id);
//                Log.v("test", user_pwd);

                if (user_id.isEmpty() || user_pwd.isEmpty()){
                    return;
                }

                String idx = Integer.toString(preferences.getInt(user_id, -1));

                if (idx.equals("-1")){
                    //TODO: 아이디가 없는 경우
                    Log.v("test", "아이디 없음!");
                    id.setError("User not found");
                }
                else{
                    String userdata = preferences.getString(idx, " ");
                    // [0]: id, [1]: pwd, [2]: name [3]: number [4]: address
                    String[] userarray = userdata.split(" ");
                    if(user_pwd.equals(userarray[1])){
                        //TODO: password가 일치하는 경우
                        Context context = getApplicationContext();
                        CharSequence text = "로그인 성공!";

                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                        Log.v("test", "로그인 성공!");

                        Intent intent = new Intent(getApplicationContext(),
                                ListActivity.class);
                        intent.putExtra("signed", userdata);
                        startActivity(intent);
                    }
                    else{
                        //TODO: password가 틀렸을 경우
                        Log.v("test", "비밀번호 틀림!");
                        password.setError("incorrect password");
                    }
               }

                Log.v("test", "test:" + id.getText());
                Log.v("test", "password:" + password.getText());
                Log.v("test", "sharedP:" + preferences.getString("idx", ""));
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


}