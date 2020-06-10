package com.example.lab04

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_video_player.*
import java.nio.file.Path

class VideoPlayerActivity : AppCompatActivity() {

    var id: Int = 0
    var path: String = ""
    var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        var vw = findViewById<View>(R.id.video_player_view) as VideoView

        id = intent.getStringExtra("id").toInt()

        if(id == 3) {
            path = "android.resource://" + packageName + "/raw/" + R.raw.sheep
            title = "Sheep's song"
        } else {
            path = "android.resource://" + packageName + "/raw/" + R.raw.eagles
            title = "Eagle accident"
        }

        video_label.setText(title)
        vw.setMediaController(MediaController(this))
        vw.setVideoURI(Uri.parse(path))

    }

    fun playVideo(v: View) {
        var vw = findViewById<View>(R.id.video_player_view) as VideoView
        if(vw.isPlaying) {
            vw.pause();
            pp_video_btn.setBackgroundResource(R.drawable.play)
        } else {
            vw.start();
            pp_video_btn.setBackgroundResource(R.drawable.pause)
        }

    }

    fun stopVideo(v: View ) {
        var vw = findViewById<View>(R.id.video_player_view) as VideoView
        vw.stopPlayback()
        pp_video_btn.setBackgroundResource(R.drawable.play)
        if(id == 3) {
            path = "android.resource://" + packageName + "/raw/" + R.raw.sheep
        } else {
            path = "android.resource://" + packageName + "/raw/" + R.raw.eagles
        }
        vw.setVideoURI(Uri.parse(path))
    }

    override fun onBackPressed() {
        var vw = findViewById<View>(R.id.video_player_view) as VideoView
        vw.stopPlayback();
        super.onBackPressed();
        this.finish();
    }

}
