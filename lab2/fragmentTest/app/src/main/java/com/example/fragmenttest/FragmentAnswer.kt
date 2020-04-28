package com.example.fragmenttest

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_fragment_answer.*
import kotlinx.android.synthetic.main.fragment_fragment_answer.view.*


class FragmentAnswer : Fragment() {
    var message: String? = ""
    var color: String? = ""

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_fragment_answer,container, false)
        message = arguments?.getString("message")
        color = arguments?.getString("color")
        root.right_answer_string.text = message
        root.right_answer_string.setTextColor(Color.parseColor(color))
        return root


    }


}
