package com.example.fragmenttest

import android.content.Context

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import java.io.*
import java.lang.StringBuilder


class MainActivity : AppCompatActivity(), Translator {

    var manager = supportFragmentManager

    fun showMainFragment() {
        val trans = manager.beginTransaction();
        val fragment = FragmentMain();
        trans.replace(R.id.fragment_main_wrap, fragment)
        trans.addToBackStack(null);
        trans.commit();
    }

    fun showEmptyFragment() {
        val trans = manager.beginTransaction();
        val fragment = FragmentBlank();
        trans.replace(R.id.fragment_wrap, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var filename = "storage.txt"
        var file = File(filesDir.toString() + "/" + filename)

        if(!file.exists()) {
            var fos: FileOutputStream
            fos = openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write("".toByteArray(charset("utf-8")))
            fos.close()
        } else {
            
        }
        showMainFragment()
        showEmptyFragment()
    }


    override fun passData(message: String) {
        var fos: FileOutputStream
        var filename: String = "storage.txt"
        var color_red = "#D8300C"
        var color_green = "#239B56"
        var bundle = Bundle()
        val transaction = this.supportFragmentManager.beginTransaction()

        fun saveToStorage() {
            var fis = openFileInput(filename)
            var isr = InputStreamReader(fis)
            var br = BufferedReader(isr)
            var sb = StringBuilder()
            var text: String = br.readText()
            fos = openFileOutput(filename, Context.MODE_PRIVATE)
            val temp: String = sb.append(text).append("\n").append(message).toString()
            fos.write(temp.toByteArray(charset("utf-8")))
            Toast.makeText(this, "Saved to" + filesDir + "/" + filename, Toast.LENGTH_SHORT).show()
            fos.close()
        }

        if (message == "remove_fragment") {
            val fragment = FragmentBlank()
            transaction.replace(R.id.fragment_wrap, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        } else if (message == "Choose your variant first") {
            bundle.putString("message", message)
            bundle.putString("color", color_red)
            val fragment = FragmentAnswer()
            fragment.arguments = bundle
            transaction.replace(R.id.fragment_wrap, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        } else {
            saveToStorage()
            if (message == "In 2010") {
                bundle.putString("color", color_green)
                bundle.putString("message", "$message is correct variant!")
            } else {
                bundle.putString("color", color_red)
                bundle.putString("message", "$message is incorrect variant!")
            }
            val fragment = FragmentAnswer()
            fragment.arguments = bundle
            transaction.replace(R.id.fragment_wrap, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


}