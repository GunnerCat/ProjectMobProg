package com.example.projectmobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.projectmobprog.login.DatabaseHelper;
import com.example.projectmobprog.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;

    EditText Username, Password;
    Button btnLogin, btnRegister;
    DatabaseHelper dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Login
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        dbHelp = new DatabaseHelper(this);

        btnLogin.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String username = Username.getText().toString();
                        String password = Password.getText().toString();

                        if(username.isEmpty() || password.isEmpty()){//If user doesn't input anything
                            Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_LONG).show();
                        }else{
                            Boolean checkUser = dbHelp.checkUsername(username);
                            Boolean checkPass = dbHelp.checkPassword(username, password);
                            if(!checkUser || !checkPass){//If user doesn't exist or password is wrong
                                Toast.makeText(MainActivity.this, "Please enter the correct username or password", Toast.LENGTH_LONG).show();
                            }else{//Login successfully
                                openHomePage();
                            }
                        }
                    }
                }
        );

        btnRegister.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        openRegisterPage();
                    }
                }
        );
    }

    public void openHomePage(){
        Username = (EditText) findViewById(R.id.Username);
        String username = Username.getText().toString();

        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("username", username);
        startActivity(intent);//redirect to home page
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);//redirect to home page
    }

}