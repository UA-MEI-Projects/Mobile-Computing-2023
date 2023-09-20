package com.example.dialeractivity

import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dialeractivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.let {
            it.mainDialerBackspace.setOnClickListener { backspace() }
            it.mainDialerCall.setOnClickListener { dialNumber(binding.mainDialerNumber.text.toString()) }
            it.mainDialerGridButton0.setOnClickListener{ addNumber("0")}
            it.mainDialerGridButton1.setOnClickListener { addNumber("1")}
            it.mainDialerGridButton2.setOnClickListener{ addNumber("2")}
            it.mainDialerGridButton3.setOnClickListener{ addNumber("3")}
            it.mainDialerGridButton4.setOnClickListener{ addNumber("4")}
            it.mainDialerGridButton5.setOnClickListener{ addNumber("5")}
            it.mainDialerGridButton6.setOnClickListener{ addNumber("6")}
            it.mainDialerGridButton7.setOnClickListener{ addNumber("7")}
            it.mainDialerGridButton8.setOnClickListener{ addNumber("8")}
            it.mainDialerGridButton9.setOnClickListener{ addNumber("9")}
            it.mainDialerGridButtonHashtag.setOnClickListener{ addNumber("#")}
            it.mainDialerGridButtonSymbol.setOnClickListener{ addNumber("+")}
            it.mainDialerGridButtonSymbol.setOnLongClickListener{
                addNumber("*")
                true
            }
        }
    }

    fun dialNumber(number : String){
        val callIntent: Intent = Uri.parse("tel:"+number).let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }

        try {
            startActivity(callIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, e.message.toString())
        }

    }

    fun backspace(){
        binding.mainDialerNumber.text = binding.mainDialerNumber.text.dropLast(1)
    }

    fun addNumber(digit : String){
        binding.mainDialerNumber.text = binding.mainDialerNumber.text.toString() + digit
    }

}