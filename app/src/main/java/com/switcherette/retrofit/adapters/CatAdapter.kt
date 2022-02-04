package com.switcherette.retrofit.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.switcherette.retrofit.data.BreedModel
import com.switcherette.retrofit.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatAdapter (private val dataSet: List<BreedModel>) :
        RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

        class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val catPhoto: ImageView
            val catBreed: TextView
            val catLink: TextView

            init {
                catPhoto = view.findViewById(R.id.iv_item)
                catBreed = view.findViewById(R.id.tv_itembreed)
                catLink = view.findViewById(R.id.tv_itemlink)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cat, parent, false)

            return CatViewHolder(view)
        }

        override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
                // get dataset
                val currentData: BreedModel = dataSet[position]
                holder.catBreed.text = currentData.name
                holder.catLink.text = currentData.wikipedia_url

                Glide.with(holder.itemView.context)
                    .load(currentData.image.url)
                    .into(holder.catPhoto);

        }

        override fun getItemCount(): Int {
            return dataSet.size
        }


}