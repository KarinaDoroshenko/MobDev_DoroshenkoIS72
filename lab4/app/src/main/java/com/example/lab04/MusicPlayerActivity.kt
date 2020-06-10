package com.example.lab04


import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_music_player.*
import java.lang.StringBuilder

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer;
    private var total_dur_secs: Int = 0;
    private var total_dur_mins: Int = 0;
    private var id: Int = 0;
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        id = getIntent().getStringExtra("id").toInt()

        if(id == 0) {
            mp = MediaPlayer.create(this, R.raw.ludovico_einaudi_una_mattina);
            title = "Ludovico Einaudi - Una Mattina"
        } else if  (id == 1 ) {
            mp = MediaPlayer.create(this, R.raw.beethoven_sonata);
            title = "Beethoven - Sonata #14"
        } else {
            mp = MediaPlayer.create(this, R.raw.beethoven_symphony5);
            title = "Beethoven - Symphony #5"
        }

        music_name.setText(title)
        mp.isLooping = false;
        total_dur_mins = mp.duration / 60 / 1000;
        total_dur_secs = (mp.duration % 60000) / 1000

        duration_label.setText(total_dur_mins.toString() + ":" + total_dur_secs.toString())
//        mp.setVolume(0.5f,0.5f);


        positionBar.max = mp.duration
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                   if(fromUser) mp.seekTo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )

        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)

                } catch (e: InterruptedException) {

                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what
            positionBar.progress = currentPosition
            var elapsedTime = createTimeLable(currentPosition)
            elapsedTimeLabel.setText(elapsedTime)
        }
    }

    fun createTimeLable(time: Int): String {
        var timelable = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timelable = "$min:"
        if(sec < 10 ) timelable += "0"
        timelable+= sec
        return timelable
    }

    fun playMusic(v: View) {

        if(mp.isPlaying) {
            mp.pause();
            pp_music_btn.setBackgroundResource(R.drawable.play)
        }
        else {
            mp.start();
            pp_music_btn.setBackgroundResource(R.drawable.pause)
        }
    }

    fun stopMusic(v: View) {
        mp.stop();
        if(id == 0) {
            mp = MediaPlayer.create(this, R.raw.ludovico_einaudi_una_mattina);
        } else if  (id == 1 ) {
            mp = MediaPlayer.create(this, R.raw.beethoven_sonata);
        } else {
            mp = MediaPlayer.create(this, R.raw.beethoven_symphony5);
        }
        pp_music_btn.setBackgroundResource(R.drawable.play)
    }

    override fun onBackPressed() {
        mp.stop();
        super.onBackPressed();
        this.finish()
    }
}
