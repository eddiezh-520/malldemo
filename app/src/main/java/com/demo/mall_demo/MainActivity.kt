package com.demo.mall_demo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.demo.mall_library.global.Globalkeys
import com.demo.mall_library.global.Mall
import com.demo.mall_library.net.RestClient
import com.demo.mall_library.net.callback.ISuccess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Mall.getConfiguration<Context>(Globalkeys.APPLICATION_CONTEXT)
        val testIcon = findViewById<TextView>(R.id.test_icon)

        testIcon.setOnClickListener {
            RestClient
                .builder()
                .url("index.php")
                .success(object : ISuccess{
                    override fun onSuccess(response: String) {
//                        Toast.makeText(baseContext, response, Toast.LENGTH_LONG).show()
                        Log.d("Eddie",response)
                    }

                })
                .build()
                .get()
        }


    }
}