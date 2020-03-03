package com.mudassirkhan.hackernewsapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.databinding.ActivityMainBinding
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    fun showProgress(isLoading:Boolean) {
        if(isLoading)
            progressMain.visibility = View.VISIBLE
        else
            progressMain.visibility = View.GONE
    }
}
