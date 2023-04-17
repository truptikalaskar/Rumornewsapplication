package com.PackageNews.rumorapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.PackageNews.rumorapp.Models.User;
import com.PackageNews.rumorapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAccountActivity extends AppCompatActivity {

    TextView txtName,txtBio,txtEmail,txtTotalUsers;
    private FirebaseAuth mAuth;
    private DatabaseReference usersReference;
    private String mCurrentUserId;
    AppCompatButton btnLogout;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
        usersReference = FirebaseDatabase.getInstance().getReference();

        txtName = findViewById(R.id.txtProfileName);
        txtEmail= findViewById(R.id.txtProfileEmail);
        txtBio = findViewById(R.id.txtProfileBio);
        txtTotalUsers = findViewById(R.id.txtTotalUsers);
        btnLogout = findViewById(R.id.btn_logout);
        btnBack = findViewById(R.id.btnMoveBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        usersReference.child("users").child(mCurrentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);
                txtName.setText(user.getName());
                txtEmail.setText(user.getEmail());
                txtBio.setText(user.getBio());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        CountTotalUsers();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();

                mAuth.signOut();
                Toast.makeText(UserAccountActivity.this, user.getEmail()+ " Sign out!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(UserAccountActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    public void CountTotalUsers(){

        usersReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                int counter = (int) dataSnapshot.getChildrenCount();

                //Convert counter to string
                String userCounter = String.valueOf(counter);

                //Showing the user counter in the textview
                txtTotalUsers.setText(userCounter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}