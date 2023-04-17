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
import android.widget.Toast;

import com.PackageNews.rumorapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password,name;
    private String uemail, pass, uname;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.edt_email);
        password = (EditText) findViewById(R.id.edt_password);
        name = (EditText) findViewById(R.id.edt_name);
        Button registerButton = (Button) findViewById(R.id.btn_register);
        Button loginLink = (Button) findViewById(R.id.link_login);

        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("/users");

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uemail = email.getText().toString();
                pass = password.getText().toString();
                uname = name.getText().toString();
                if (!TextUtils.isEmpty(uemail) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(uname)) {
                    pd.setMessage("Loading..");
                    pd.show();
                    mAuth.createUserWithEmailAndPassword(uemail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                pd.dismiss();
                                Toast.makeText(RegisterActivity.this, "FAILED! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                pd.dismiss();
                                FirebaseUser tempUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (tempUser != null) {
                                    HashMap<String,Object> map = new HashMap<>();
                                    map.put("name",uname);
                                    map.put("email",uemail);
                                    map.put("bio","My bio");
                                    map.put("phone","0123456");
                                    map.put("password",pass);

                                    userRef.child(tempUser.getUid()).setValue(map);
                                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        });


    }
}