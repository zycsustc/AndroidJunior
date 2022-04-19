package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.fragments.FragmentAndroid
import com.example.myapplication.fragments.FragmentJava
import com.example.myapplication.utils.UIUtils

class MyFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)

        initUI()
    }

    private fun initUI() {
        UIUtils.replaceFragmentAndAddToBackTrace(
            supportFragmentManager,
            FragmentAndroid(),
            R.id.content,
            FragmentAndroid.id
        )

        val btnAndroid: Button = findViewById(R.id.androidButton)
        btnAndroid.setOnClickListener {
            UIUtils.replaceFragmentAndAddToBackTrace(
                supportFragmentManager,
                FragmentAndroid(),
                R.id.content,
                FragmentAndroid.id
            )
        }

        val btnJava: Button = findViewById(R.id.javaButton)
        btnJava.setOnClickListener {
            UIUtils.replaceFragmentAndAddToBackTrace(
                supportFragmentManager,
                FragmentJava(),
                R.id.content,
                FragmentJava.id
            )
        }
    }
}