package com.example.a2501974391_uts_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2501974391_uts_mcs.Database.GlobalData;
import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.User;

import java.util.Vector;


//Login Page
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainDatabase db;
    EditText usernameEdtx, passwordEdtx;
    Button loginBtn, toRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        db = MainDatabase.getDb(this);

        addInitData();

        usernameEdtx = findViewById(R.id.login_edtx_username);
        passwordEdtx = findViewById(R.id.login_edtx_password);
        loginBtn = findViewById(R.id.login_button_login);
        toRegisterBtn = findViewById(R.id.login_button_toRegister);

        loginBtn.setOnClickListener(this);
        toRegisterBtn.setOnClickListener(this);
    }

    void addInitData(){
        if(db.userTableIsEmpty()){
            db.insertUser("admin", "admin.com", "admin");
            db.insertArticle("MCSol advances to Europa League final with thrilling comeback against MdExa", "01-02-2023", R.drawable.news_a, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. \n Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
            db.insertArticle("Teo Valensi dominates MCSol in Champions League final", "01-02-2023", R.drawable.news_b,"Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? \n At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio \n Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.");
            db.insertTicketType("Regular", 25000);
            db.insertTicketType("VIP", 35000);
            db.insertTicketType("VVIP", 50000);
            db.insertTicket("MCS League", "GBK Stadium", "19.00", "Friday, 13 June 2023");
            db.insertTicket("Data Match", "Jakarta International Stadium", "13.00", "Monday, 18 June 2023");
            db.insertTicket("UTS Tournament", "Gelora Bung Tomo Stadium", "08.00", "Tuesday, 04 August 2023");
            db.insertTicket("Data Match", "Manahan Stadium", "11.00", "Sunday, 29 December 2023");
            db.insertHistory("29-04-2023", 2, 50000, 1,1, 1);
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn){
            if(isFilled()){
                if(isExist()){
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent toHomePage = new Intent(this, HomePage.class);
                    startActivity(toHomePage);
                    usernameEdtx.setText("");
                    passwordEdtx.setText("");
                }
            }
        }else if(v == toRegisterBtn){
            Intent toRegisterPage = new Intent(this, RegisterPage.class);
            startActivity(toRegisterPage);
        }
    }

    boolean isFilled(){
        if(usernameEdtx.getText().toString().isEmpty()){
            Toast.makeText(this, "Username must be filled", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordEdtx.getText().toString().isEmpty()) {
            Toast.makeText(this, "password must be filled", Toast.LENGTH_SHORT).show();
            return false;
        } else{
            return true;
        }
    }


    boolean isExist(){

        String username = usernameEdtx.getText().toString();

        if(!db.checkUserExistbyUsername(username)){
            Toast.makeText(this, "User not exist", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            User userFound = db.getUserbyUsername(username);
            if(!passwordEdtx.getText().toString().equals(userFound.getPassword())){
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                GlobalData.getData().currentUserId = userFound.getId();
                return true;
            }
        }

    }


}