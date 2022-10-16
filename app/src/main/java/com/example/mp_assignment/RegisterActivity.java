package com.example.mp_assignment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {

    private SharedPreferences preferences;
    TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("2페이지 (회원가입 화면)");

        terms = findViewById(R.id.textview_terms);
        Boolean[] validate = {false, false};
        final String PWPATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$";
        Pattern pw_pt = Pattern.compile(PWPATTERN);

        Button idcheck = (Button) findViewById(R.id.btn_check_id);
        Button register = (Button) findViewById(R.id.register_done);

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        int current_idx = preferences.getInt("current_idx", -1);
        if (current_idx == -1){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("current_idx", 0);
        }

        EditText id = (EditText) findViewById(R.id.input_id);
        EditText password = (EditText) findViewById(R.id.input_password);
        EditText name = (EditText) findViewById(R.id.input_name);
        EditText number = (EditText) findViewById(R.id.input_number);
        EditText address = (EditText) findViewById(R.id.input_address);

        RadioButton accept = (RadioButton) findViewById(R.id.radio_accept);

        idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idx = preferences.getInt(id.getText().toString(), -1);
                if(idx != -1){
                    validate[0] = false;
                    Context context = getApplicationContext();
                    CharSequence text = "중복된 아이디입니다.";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
                else{
                    validate[0] = true;
                    Context context = getApplicationContext();
                    CharSequence text = "사용가능한 아이디입니다!";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 회원가입 정보 저장하는 부분 추가

                // 비밀번호 유효성 검사 (8자 이상 + 영문, 숫자 조합)
                validate[1] = pw_pt.matcher(password.getText().toString()).matches();

                if (!(validate[0] && validate[1] && accept.isChecked())){
                    Context context = getApplicationContext();
                    CharSequence text = "다시 확인해주세요.";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    return;
                }

                int current_idx = preferences.getInt("current_idx", -1);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(id.getText().toString(), current_idx);

                ArrayList user = new ArrayList<>();
                user.add(id.getText().toString());
                user.add(password.getText().toString());
                user.add(name.getText().toString());
                user.add(number.getText().toString());
                user.add(address.getText().toString());
                String userdata = String.join(" ", user);

                editor.putString(Integer.toString(current_idx), userdata);
                editor.putInt("current_idx", ++current_idx);
//                editor.putString("name", name.getText().toString());
//                editor.putString("number", number.getText().toString());
//                editor.putString("address", address.getText().toString());

                Log.v("test", "id:" + id.getText());
                editor.commit();
//                getPreferences();

                finish();
            }
        });
    }

    private void getPreferences(){
        //getString(KEY,KEY값이 없을때 대체)
        terms.setText("USERID = " + Integer.toString(preferences.getInt("current_idx",0))
                + "\n USERPWD = " + preferences.getString("0"," "));
    }
}
