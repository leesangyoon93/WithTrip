package com.sangyoon.withtrip.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.sangyoon.withtrip.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        logoutButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            logoutButton -> {
                FirebaseAuth.getInstance().signOut()
                (activity as MainActivity).signOut()
            }
        }
    }
}