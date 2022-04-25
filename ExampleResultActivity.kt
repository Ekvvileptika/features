package com.example.myapplication.activityResult

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.sealedExample.MainActivity
import java.util.jar.Manifest

class ExampleResultActivity: AppCompatActivity(R.layout.activity_main) {
    //permission result
    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        validatePermission(it)
    }

    //get activity result
    private val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        it.data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView()

        //permissions request
        requestPermissions.launch(android.Manifest.permission.CAMERA)

        //activity result request
        val newActivity = Intent(this, MainActivity::class.java)
        activityResult.launch(newActivity)
    }


    private fun validatePermission(status: Boolean){
        if(status){
            //Granted
        } else {
            //No access
        }
    }
}