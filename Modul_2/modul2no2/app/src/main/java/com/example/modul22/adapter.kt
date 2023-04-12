package com.example.modul22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class adapter (private val listgalaxy:ArrayList<galaxy>): RecyclerView.Adapter<adapter.CardViewViewHolder>(){
    inner class CardViewViewHolder (itemview: View):RecyclerView.ViewHolder(itemview) {
        var imgphoto: ImageView = itemview.findViewById(R.id.img1)
        var txtjudul: TextView = itemview.findViewById(R.id.judulnya)
        var deskrp: TextView = itemview.findViewById(R.id.item_desk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        TODO("Not yet implemented")
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_galaksi,parent,false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return listgalaxy.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        TODO("Not yet implemented")
        val galaksi = listgalaxy[position]

        Glide.with(holder.itemView.context)
            .load(galaksi.photo)
            .apply(RequestOptions())
            .into(holder.imgphoto)

        holder.txtjudul.text = galaksi.judul
        holder.deskrp.text = galaksi.deskripsi

        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih"+listgalaxy[holder.adapterPosition].judul,Toast.LENGTH_SHORT).show() }
    }
}