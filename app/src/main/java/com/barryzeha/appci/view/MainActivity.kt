package com.barryzeha.appci.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barryzeha.appci.R
import com.barryzeha.appci.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }
}