package com.example.dialerappcompose

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dialerappcompose.ui.theme.DialerAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialerAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(::dialNumber)
                }
            }
        }
    }

    private fun dialNumber(number : String){
        val callIntent: Intent = Uri.parse("tel:"+number).let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }

        try {
            startActivity(callIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e(ContentValues.TAG, e.message.toString())
        }

    }
}

@Composable
fun Greeting(callFun : (String) -> Unit) {
    val symbols = arrayOf(arrayOf("1", "2", "3"), arrayOf("4", "5", "6"), arrayOf("7", "8", "9"), arrayOf("+/*", "0", "#" ))
    var number by remember { mutableStateOf("") }

    @Composable
    fun DialerRow(symbolSet : Array<String>){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for(symbol in symbolSet){
                Button(onClick = { number += symbol }) {
                    Text(symbol)
                }
            }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(number, style = MaterialTheme.typography.bodyLarge)
        for(symbolSet in symbols){
            DialerRow(symbolSet = symbolSet)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.add_person),
                contentDescription = "Add Person"
            )
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Call",
                modifier = Modifier.clickable { callFun(number) }
            )
            Image(
                painter = painterResource(id = R.drawable.backspace),
                contentDescription = "Backspace",
                modifier = Modifier.clickable { number = number.dropLast(1) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DialerAppComposeTheme {
        Greeting(callFun = {})
    }
}