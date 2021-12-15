package com.example.too_tasty_reciep_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //set up UI
        val addToReciptButton = findViewById<Button>(R.id.btAddToApi)
        val authorText = findViewById<EditText>(R.id.etAuthor)
        val titleText = findViewById<EditText>(R.id.etTitle)
        val ingText = findViewById<EditText>(R.id.etIngredients)
        val instText = findViewById<MultiAutoCompleteTextView>(R.id.etInstructions)


        //set up retrofit json call :@POST
        addToReciptButton.setOnClickListener {
            val apiInterface = APIClient().getClient()?.create(APInterface::class.java)
            apiInterface?.addRecipes(
                reciepeItem(
                    authorText.text.toString(),
                    ingText.text.toString(),
                    instText.text.toString(),
                    0,
                    titleText.text.toString()
                )
            )?.enqueue(object : Callback<reciepeItem> {
                override fun onResponse(
                    call: Call<reciepeItem>,
                    response: Response<reciepeItem>
                ) {

                    Toast.makeText(
                        applicationContext,
                        "recipe added to api",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(MainActivity2(), MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<reciepeItem>, t: Throwable) {
                    Log.d("post-error2", "$t")
                }
            })
        }
    }
}