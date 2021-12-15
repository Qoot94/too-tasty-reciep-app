package com.example.too_tasty_reciep_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var menu = ArrayList<RecipeCard>()
    lateinit var myRV: RecyclerView
    lateinit var rvAdapter: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up UI
        val errorText = findViewById<TextView>(R.id.tvError)

        //set up recyclerView
        myRV = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(menu)
        myRV.adapter = rvAdapter
        myRV.layoutManager = LinearLayoutManager(this)

        //set up retrofit json call :@GET
        val apiInterface = APIClient().getClient()?.create(APInterface::class.java)
        val call: Call<ArrayList<reciepeItem>>? = apiInterface?.getRecipes()
        call?.enqueue(object : Callback<ArrayList<reciepeItem>>{
            override fun onResponse(call: Call<ArrayList<reciepeItem>>, response: Response<ArrayList<reciepeItem>>) {
                try {
                    //retrieve all data needed
                    val response = response.body()!!
                    Log.d("GET-success", "${response}")

                    for (i in 0 until response!!.size){
                        val titleAPI = response[i].title
                    val authorAPI = response[i].author
                    val ingAPI = response[i].ingredients
                    val instAPI = response[i].instructions
                        val recipeObject = RecipeCard(titleAPI, authorAPI, ingAPI, instAPI)
                        menu.add(recipeObject)
                        rvAdapter.notifyDataSetChanged()
                        errorText.visibility = View.INVISIBLE
                    }
                    Log.d("GET-success", "${response?.size}")
                } catch (e: Exception) {
                    errorText.visibility = View.VISIBLE
                    Log.d("GET-error", "$e")
                }
            }

            override fun onFailure(call: Call<ArrayList<reciepeItem>>, t: Throwable) {
                Log.d("GET-error2", "$t")
            }

        })



        //UI interactions
        val addButton = findViewById<Button>(R.id.btAdd)
        addButton.setOnClickListener {
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}