package com.example.lab04

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player_btn.setOnClickListener {
            var id = spinner.selectedItemId
            var intent: Intent

            if(id >= 0 && id <= 2) {
                intent = Intent(this, MusicPlayerActivity::class.java)

            } else {
                intent = Intent(this, VideoPlayerActivity::class.java)
            }

            intent.putExtra("id", id.toString())
            startActivity(intent)
        }

    }


}
