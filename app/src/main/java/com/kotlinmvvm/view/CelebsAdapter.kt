package com.kotlinmvvm.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinmvvm.R
import com.kotlinmvvm.data.CelebsModel
import de.hdodenhof.circleimageview.CircleImageView

class CelebsAdapter(var listofcelebs: ArrayList<CelebsModel>) :
    RecyclerView.Adapter<CelebsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_celebs, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listofcelebs[position])
    }

    override fun getItemCount(): Int {
        return listofcelebs.let { listofcelebs.size }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val celebspics = view.findViewById<CircleImageView>(R.id.img_celebs)
        private val celebsName = view.findViewById<TextView>(R.id.txt_celebs_name)
        private val celebsGender = view.findViewById<TextView>(R.id.txt_celebs_gender)

        fun bind(celebsModel: CelebsModel) {
            celebsName.text = celebsModel.name
            celebsGender.text = celebsModel.gender

            Glide.with(itemView.context)
                .load(celebsModel.photo)
                .into(celebspics)
        }
    }

    fun updateCelebsList(newlistofcelebs: List<CelebsModel>) {
        listofcelebs.clear()
        listofcelebs.addAll(newlistofcelebs)
        notifyDataSetChanged()
    }
}


