package com.example.projectmobprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmobprog.login.DatabaseHelper;

public class UpdateAccountActivity extends AppCompatActivity {

    EditText updateUsername, updateFullName, updatePassword, updateConfirmPass;
    Button btnUpdate;
    DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        updateUsername = (EditText) findViewById(R.id.updateUsername);
        updateFullName = (EditText) findViewById(R.id.updateFullName);
        updatePassword = (EditText) findViewById(R.id.updatePassword);
        updateConfirmPass = (EditText) findViewById(R.id.updateConfirmPass);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        dbHelp = new DatabaseHelper(this);

        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent getIntent = getIntent();
                        String oldUsername = getIntent.getStringExtra("username");

                        String username = updateUsername.getText().toString();
                        String fullname = updateFullName.getText().toString();
                        String password = updatePassword.getText().toString();
                        String confirmpassword = updateConfirmPass.getText().toString();

                        if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                            Toast.makeText(UpdateAccountActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else if (!password.equals(confirmpassword)) {//If password and confirm password don't match
                            Toast.makeText(UpdateAccountActivity.this, "Please input the fields correctly", Toast.LENGTH_SHORT).show();
                        } else {
                            dbHelp.updateAccount(oldUsername, username, fullname, password);
                            Toast.makeText(UpdateAccountActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                            openHomePage(username);
                        }
                    }
                }
        );

    }

    public void openHomePage(String newUsername){
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("username", newUsername);
        startActivity(intent);
    }
}