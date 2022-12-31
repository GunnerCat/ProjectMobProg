package com.example.projectmobprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText regFullName, regUsername, regPassword, regConfirmPass;
    Button regBtnRegister;
    DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Register
        regFullName = (EditText) findViewById(R.id.regFullName);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPassword = (EditText) findViewById(R.id.regPassword);
        regConfirmPass = (EditText) findViewById(R.id.regConfirmPass);
        regBtnRegister = (Button) findViewById(R.id.regBtnRegister);
        dbHelp = new DatabaseHelper(this);

        regBtnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fullname = regFullName.getText().toString();
                        String username = regUsername.getText().toString();
                        String password = regPassword.getText().toString();
                        String confirmpassword = regConfirmPass.getText().toString();

                        if(fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){//If field is empty
                            Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        }else if(!password.equals(confirmpassword)){//If password and confirm password don't match
                            Toast.makeText(RegisterActivity.this, "Please input the fields correctly", Toast.LENGTH_SHORT).show();
                        }else{
                            Boolean checkUser = dbHelp.checkUsername(username);
                            if(!checkUser){//No username in database (no account yet created)
                                Boolean insertData = dbHelp.insertData(username, fullname, password);
                                if(insertData){//If insertData successful
                                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    openHomePage();
                                }else{//If boolean insertData returns false
                                    Toast.makeText(RegisterActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{//Username existed in database
                                Toast.makeText(RegisterActivity.this, "Username already used, try a different one or try logging in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    public void openHomePage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);//redirect to home page
    }
}