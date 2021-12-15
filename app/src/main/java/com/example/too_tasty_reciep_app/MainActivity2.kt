package com.example.too_tasty_reciep_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        val authorText = findViewById<EditText>(R.id.etAuthor).text.toString()
        val titleText = findViewById<EditText>(R.id.etTitle).text.toString()
        val ingText = findViewById<EditText>(R.id.etIngredients).text.toString()
        val instText = findViewById<EditText>(R.id.etInstructions).text.toString()

//val reciepeItemObj = ArrayList<reciepeItem>(reciepeItem(authorText,ingText,instText,pk=0,titleText))

        //set up retrofit json call :@POST
        val apiInterface = APIClient().getClient()?.create(APInterface::class.java)
        var recip = reciepeItem(
            authorText,ingText,instText,0,titleText
        )

        var pasRecipe = apiInterface?.addRecipes(recip)

        addToReciptButton.setOnClickListener {
            pasRecipe?.enqueue(object :Callback<ArrayList<reciepeItem>>{
                override fun onResponse(
                    call: Call<ArrayList<reciepeItem>>,
                    response: Response<ArrayList<reciepeItem>>
                ) {
                    response.body()
                    Toast.makeText(
                            applicationContext,
                            "recipe added to api",
                            Toast.LENGTH_SHORT
                        ).show()
                    Log.d("post-error2", "${response.isSuccessful}")
                }

                override fun onFailure(call: Call<ArrayList<reciepeItem>>, t: Throwable) {
                    Log.d("post-error2", "$t")
                    Toast.makeText(
                        applicationContext,
                        "recipe failed added to api",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }


//        var  = apiInterface?.addRecipes(recip)
//        addToReciptButton.setOnClickListener {
//            val call1: Call<ArrayList<reciepeItem>>? =
//                apiInterface?.addRecipes(reciepeItemObj)
//            call1!!.enqueue(object : Callback<s> {
//
//                override fun onResponse(call: Call<reciepeItem>, response: Response<ArrayList<reciepeItem>>) {
//                    try {
//                       // val responseObj = response.body()!!
//                        Toast.makeText(
//                            applicationContext,
//                            "recipe added to api",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        Log.d("POST-success", "")
//                    } catch (e: Exception) {
//                        Log.d("POST-errror1", "")
//                    }
//                }
//
//                override fun onFailure(call: Call<reciepeItem>, t: Throwable) {
//                    Log.d("POST-error2", "$t")
//                }
//            })
//
//        }
    }
}