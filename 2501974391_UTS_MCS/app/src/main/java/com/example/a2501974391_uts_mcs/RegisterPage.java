package com.example.a2501974391_uts_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.User;

import java.util.Vector;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    private MainDatabase db;
    EditText usernameEdtx, emailEdtx, passwordEdtx, confirmPasswordEdtx;
    Button registerBtn, toLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().hide();

        db = MainDatabase.getDb(this);

        usernameEdtx = findViewById(R.id.register_edtx_username);
        emailEdtx = findViewById(R.id.register_edtx_email);
        passwordEdtx = findViewById(R.id.register_edtx_password);
        confirmPasswordEdtx = findViewById(R.id.register_edtx_confirmpassword);

        registerBtn = findViewById(R.id.register_button_register);
        toLoginBtn = findViewById(R.id.register_button_toLogin);
        registerBtn.setOnClickListener(this);
        toLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == registerBtn){
            if(isFilled()){
                if (isConditionFullfilled()){
                    if(isExisted()){
                        String username = usernameEdtx.getText().toString();
                        String email = emailEdtx.getText().toString();
                        String password = passwordEdtx.getText().toString();

                        db.insertUser(username, email, password);
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        } else if (v == toLoginBtn) {
            finish();
        }
    }

    boolean isFilled(){
        if(usernameEdtx.getText().toString().isEmpty() || emailEdtx.getText().toString().isEmpty() || passwordEdtx.getText().toString().isEmpty() || confirmPasswordEdtx.getText().toString().isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    boolean isConditionFullfilled(){
        if(usernameEdtx.getText().toString().length() < 5){
            Toast.makeText(this, "Name must be at least 5 characters", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!emailEdtx.getText().toString().endsWith(".com")){
            Toast.makeText(this, "Email must ends with '.com'", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!passwordEdtx.getText().toString().contentEquals(confirmPasswordEdtx.getText().toString())){
            Toast.makeText(this, "Password and confirm password must be the same", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    boolean isExisted(){
        if(db.checkUserExistbyUsername(usernameEdtx.getText().toString())){
            Toast.makeText(this, "username already used", Toast.LENGTH_SHORT).show();
            return false;
        }else if(db.checkUserExistbyEmail(emailEdtx.getText().toString())){
            Toast.makeText(this, "email already used", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}