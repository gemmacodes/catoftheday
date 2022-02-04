package com.switcherette.retrofit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.switcherette.retrofit.R
import com.switcherette.retrofit.adapters.CatAdapter
import com.switcherette.retrofit.data.BreedModel
import com.switcherette.retrofit.repositories.HttpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_list)

        // recycler view (bound to UI)
        val myRecyclerView = findViewById<RecyclerView>(R.id.rv_catlist)

        CoroutineScope(Dispatchers.IO).launch { //run in background

            // repository:  we get data from it
            val myData: List<BreedModel>? = HttpRepository().getAllBreeds()

            withContext(Dispatchers.Main) { // run in foreground (UI)
                // bind recycler View and Adapter
                myRecyclerView.adapter = CatAdapter(myData!!)
            }
        }

        // setLayoutManager
        myRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

}


