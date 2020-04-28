package com.example.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), Translator {

    var manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        showMainFragment()
        showEmptyFragment()
    }

    override fun passData(message: String) {
        val bundle = Bundle()
        val transaction = this.supportFragmentManager.beginTransaction()


        var color_red = "#D8300C"
        var color_green = "#239B56"
        if (message !== "remove_fragment") {
            if (message == "Choose your variant first") {
                bundle.putString("message", message)
                bundle.putString("color", color_red)
            } else if (message == "In 2010") {
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
        } else {
            val fragment = FragmentBlank()
            transaction.replace(R.id.fragment_wrap, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}