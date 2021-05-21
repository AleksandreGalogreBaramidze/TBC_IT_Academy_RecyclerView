package com.example.recyclerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerapplication.databinding.ActivityMainBinding
import com.example.recyclerapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        listeners()
    }

    fun init(){
        val carType = intent.extras?.getString("carType","default")
        val status = intent.extras?.getString("status", "sold")
        val profilePicture = intent.extras?.getInt("profile")
        binding.txtType.text = carType
        binding.txtProfileStatus.text = status
        binding.imgProfileCar.setImageResource(profilePicture!!)
    }

    fun listeners(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}