package com.PackageNews.rumorapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.PackageNews.rumorapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private String user, pass;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.edt_email_login);
        password = (EditText) findViewById(R.id.edt_password_login);
        Button loginButton = (Button) findViewById(R.id.btn_login);
        Button registerLink = (Button) findViewById(R.id.link_register);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = email.getText().toString();
                pass = password.getText().toString();
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                    pd.setMessage("Logging in....");
                    pd.show();
                    mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                pd.dismiss();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "FAILED" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


    }
}