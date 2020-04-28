package com.example.fragmenttest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class StorageActivity : AppCompatActivity() {

    var MainActivity: Activity = com.example.fragmenttest.MainActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        var filename = "storage.txt"
        var file = File(filesDir.toString() + "/" + filename)
        var fos: FileOutputStream
        if(!file.exists()) {
            fos = openFileOutput(filename, Context.MODE_PRIVATE)
        }

        fun displayStorage() {
            var fis = openFileInput(filename)
            var isr = InputStreamReader(fis)
            var br = BufferedReader(isr)
            var sb = StringBuilder()
            var text: String = br.readText()
            sb.append(text)
            if(text == "") {
                storage_context.setText("Storage is empty")
            } else {
                storage_context.setText(sb)
            }
        }

        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        clear_storage_btn.setOnClickListener {
            fos = openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write("".toByteArray(charset("utf-8")))
            fos.close()
            storage_context.setText("Storage is empty")
        }

        displayStorage()
    }
}
