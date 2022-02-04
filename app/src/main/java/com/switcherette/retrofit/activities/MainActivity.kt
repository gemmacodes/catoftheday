package com.switcherette.retrofit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.switcherette.retrofit.R
import com.switcherette.retrofit.repositories.HttpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var myText: TextView
    private lateinit var myWiki: TextView
    private lateinit var myBreed: TextView
    private lateinit var myImage: ImageView
    private lateinit var myAgainButton: Button
    private lateinit var myAllButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myText = findViewById<TextView>(R.id.tv_api)
        myBreed = findViewById<TextView>(R.id.tv_api_breed)
        myWiki = findViewById<TextView>(R.id.tv_api_wiki)
        myImage = findViewById<ImageView>(R.id.iv_api)
        myAgainButton = findViewById<Button>(R.id.btn_again)
        myAllButton = findViewById<Button>(R.id.btn_all)

        coroutine()

        myAgainButton.setOnClickListener {
            coroutine()
        }

        myAllButton.setOnClickListener {
            startActivity(Intent(this, CatListActivity::class.java))
        }
    }

    private fun coroutine() {
        CoroutineScope(Dispatchers.IO).launch { //run in background
            val repo = HttpRepository()
            val allBreeds = repo.getAllBreeds()
            val breedIndex = (0..(allBreeds?.size ?: 0)).random()
            val image = allBreeds?.get(breedIndex)?.image?.url
            val oneBreed = repo.getBreed(allBreeds?.get(breedIndex)?.id)?.get(0)?.breeds?.get(0)

            withContext(Dispatchers.Main) { // run in foreground (UI)
                myText.text = "Out of ${allBreeds?.size} breeds, today you got a..."
                myBreed.text = "${allBreeds?.get(breedIndex)?.name}"
                myWiki.text = "${oneBreed?.wikipedia_url}"
                Glide.with(this@MainActivity).load(image).into(myImage);
            }
        }
    }


}