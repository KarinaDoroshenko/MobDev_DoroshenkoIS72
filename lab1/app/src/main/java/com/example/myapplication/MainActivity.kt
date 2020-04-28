package com.example.myapplication

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.RadioButton
import android.widget.RadioGroup


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        check_btn.setOnClickListener() {
            if(!first_var.isChecked && !second_var.isChecked && !third_var.isChecked && !forth_var.isChecked) {
                test_view.setTextColor(Color.parseColor("#D8300C"))
                test_view.text = "Choose your variant"
            }
        }


        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{group, id ->

                check_btn.setOnClickListener() {
                    val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    @Suppress("DEPRECATION")
                    vibratorService.vibrate(100)

                    if(first_var.isChecked == true || second_var.isChecked == true || third_var.isChecked == true || forth_var.isChecked == true ) {
                        val radio: RadioButton = findViewById(id);

                        if (radio.text == "In 2010") {
                            test_view.setTextColor(Color.parseColor("#239B56"))
                            test_view.text = "'" + radio.text + "' is correct!";
                        } else {
                            test_view.setTextColor(Color.parseColor("#D8300C"))
                            test_view.text = "Answer '" + radio.text + "' is incorrect";
                        }

                    } else {
                        test_view.setTextColor(Color.parseColor("#D8300C"))
                        test_view.text = "Choose your variant"

                    }

                }

        })

        clear_btn.setOnClickListener() {

            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            @Suppress("DEPRECATION")
            vibratorService.vibrate(100)

            radio_group.clearCheck();
            test_view.text = "";
        }

    }
}
