package com.example.sharedpreference;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mbtnRequestPermission;
    static final int REQUEST_CODE =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnRequestPermission = findViewById(R.id.btnrequest);

        mbtnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] permission= {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this,permission, REQUEST_CODE );

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_GRANTED){
            showtoast("Both permission granted");
        }else if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_DENIED){
            showtoast("Camera granted but storage denied");
        }else if(grantResults[0]== PackageManager.PERMISSION_DENIED && grantResults[1]== PackageManager.PERMISSION_GRANTED){
            showtoast("Camera denied but storage granted");
        }else {
            showtoast("Both the permissions are denied");

        }
    }
    private void showtoast(String Message){
        Toast.makeText(this,Message,Toast.LENGTH_SHORT).show();
    }
}