package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    private Button saveInfo;
    private EditText user_name, user_status;
    private CircleImageView circleImageView;
    private String currentUserId;
    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveInfo = (Button) findViewById(R.id.save_user_info);
        user_name = (EditText) findViewById(R.id.set_user_name);
        user_status = (EditText) findViewById(R.id.set_user_status);
        circleImageView = (CircleImageView) findViewById(R.id.profile_image);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();

        user_name.setVisibility(View.INVISIBLE);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateInformation();
            }
        });

        retrieveUserInformation();
    }

    private void UpdateInformation()
    {
        String setName = user_name.getText().toString();
        String setStatus = user_status.getText().toString();

        if(TextUtils.isEmpty(setName))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(setStatus))
        {
            Toast.makeText(this, "Введите cтатус", Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", currentUserId);
            profileMap.put("name", setName);
            profileMap.put("status", setStatus);

            rootRef.child("Users").child(currentUserId).setValue(profileMap).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SettingsActivity.this, "Информация обновлена", Toast.LENGTH_SHORT).show();

                                Intent mainIntent = new Intent(SettingsActivity.this,MainActivity.class);
                                startActivity(mainIntent);
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toast.makeText(SettingsActivity.this, "Произошла ошибка: " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void retrieveUserInformation()
    {
        rootRef.child("Users").child(currentUserId).
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists() && snapshot.hasChild("name"))
                        {
                            String retrieveUserName = snapshot.child("name").getValue().toString();
                            String retrieveStatus = snapshot.child("status").getValue().toString();

                            user_name.setText(retrieveUserName);
                            user_status.setText(retrieveStatus);
                        }
                        else
                        {
                            user_name.setVisibility(View.VISIBLE);
                            Toast.makeText(SettingsActivity.this, "Введите свое имя", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}