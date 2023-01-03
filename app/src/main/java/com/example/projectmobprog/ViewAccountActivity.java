package com.example.projectmobprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectmobprog.login.DatabaseHelper;

public class ViewAccountActivity extends AppCompatActivity {

    TextView tvUsername, tvFullName;
    DatabaseHelper dbHelp;
    Button btnUpdateViewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        tvUsername = findViewById(R.id.tvUsername);
        tvFullName = findViewById(R.id.tvFullName);
        btnUpdateViewAccount = (Button) findViewById(R.id.btnUpdateViewAccount);
        Intent getIntent = getIntent();
        String username = getIntent.getStringExtra("username");
        dbHelp = new DatabaseHelper(this);
        String fullName = dbHelp.getFullName(username);
        tvUsername.setText(username);
        tvFullName.setText(fullName);

        btnUpdateViewAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openUpdatePage(username);
                    }
                }
        );

    }

    public void openUpdatePage(String username){
        Intent intent = new Intent(this, UpdateAccountActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}