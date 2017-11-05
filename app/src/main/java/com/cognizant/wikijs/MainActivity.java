package com.cognizant.wikijs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;
import com.wikitude.common.permission.PermissionManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ArchitectView architectView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            architectViewCall();
        }
        else{
           if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
               Toast.makeText(this, "Need camera for an AR experience", Toast.LENGTH_LONG);
           }
           requestPermissions(new String[] {Manifest.permission.CAMERA}, 0);
           architectViewCall();
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 0){
//            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                architectViewCall();
//            }
//            else {
//                Toast.makeText(this, "Cannot start experience due to camera permission failure",Toast.LENGTH_LONG);
//            }
//        }
//    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        architectView.onPostCreate();
        ARCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();

        architectView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        architectView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        architectView.onDestroy();
    }

    //Sets the architecht view from wikitude on mobile display @bhomitb
    protected void architectViewCall(){
        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("uc+B0eWZTXFQ3uNyOHLYqrcZYTvdqN61oSd41zrWdwni0IRlJBTofJdBB3dQygvuG7XtWFhRUO74Z5B4UAHDiw/PU16D1/08jpfntPlp+zf2fz5MwhgO81pF8iGlPblyLL5evNuavwgUpHcf/7N1yEQBqR285tp7WzOSTZ7kfyRTYWx0ZWRfX3sO19scfXwbiCo1fplTKMHssjEdlBbQty5Ix0Ud+gdQmfTMXj228IiVoMM9w8bb566QZ+ttoJVKRq4ZbPp/T6MJ3KgskDYsH5xDHgPhZRCM0THhJHKQvnPVA2B+xgYxXckd6+gowRuRbpim2sAl/5BjUe3S40R+Z2mqXY5FPqFTXuj0yUZ3PNwt/rsbkNHzIBlORp9bGHHIVOpfvOLPfrT8hyIdFbbvWwX80nqaVY5HZgfJB0UpqJT7k7RpPQIb9wZMwuu/yxCCER99YL7h0k4ApQK8LUFRwkLnU/6QeFC6U3UFRSIfqmho6lWMnP+WjdCz0PWGFvssXhhNw/4YjmptyskT0uTIAGANiJfoJUwTYjb+xiPh+5KIf54t0794gZ2NnPW4Liym1Skk9IDunIQhGZITiSFvVg1BhwVWbX1g53MwlmVY6TMI6HGX5vO8B6aa94SqgwWTx8J3474R9APoqBzJ56fb/8mfC3a9Sn6ItwNutDDyw5k=");
        this.architectView.onCreate( config );
    }

    protected void ARCreate(){
        try{
            this.architectView.load("file:///android_asset/SimpleVideo/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
