package com.example.fragmenttest

import android.app.Activity
import android.content.Context
import android.content.Intent

import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_main.*
import kotlinx.android.synthetic.main.fragment_fragment_main.view.*


class FragmentMain : Fragment() {
    lateinit var translator: Translator;
    var StorageActivity: Activity = com.example.fragmenttest.StorageActivity()

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_fragment_main,container, false)
        translator = activity as Translator

        root.check_btn.setOnClickListener() {
            if(!root.first_var.isChecked && !root.second_var.isChecked && !root.third_var.isChecked && !root.forth_var.isChecked) {
                translator.passData("Choose your variant first")
            }
        }

        root.radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{ group, id ->
                root.check_btn.setOnClickListener() {

                    if(root.first_var.isChecked || root.second_var.isChecked || root.third_var.isChecked || root.forth_var.isChecked) {
                        val radio: RadioButton = view!!.findViewById(id)
                        val radio_text = radio.text
                        translator.passData(radio_text as String)

                    } else {
                        translator.passData("Choose your variant first")
                    }
                }
            })
        root.clear_btn.setOnClickListener() {
            root.radio_group.clearCheck();
            translator.passData("remove_fragment")
        }
        root.storage_btn.setOnClickListener() {
            val intent = Intent(context, StorageActivity::class.java)
            startActivity(intent)
        }

        return root

    }


}
