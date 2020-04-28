package com.example.fragmenttest

import android.content.Context
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




class FragmentBlank : Fragment() {
    private var root: View? = null


    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater!!.inflate(R.layout.fragment_fragment_blank,container, false)
        return root
    }
}


