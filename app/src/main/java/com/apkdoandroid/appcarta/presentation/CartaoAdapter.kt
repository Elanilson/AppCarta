package com.apkdoandroid.appcarta.presentation

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apkdoandroid.appcarta.data.model.Carta
import com.apkdoandroid.appcarta.databinding.ItemRvCartaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat

import com.squareup.picasso.Picasso

class CartaoAdapter : Adapter<CartaoAdapter.CartaoViewHolder>() {

    inner class CartaoViewHolder(
        private val binding: ItemRvCartaBinding
    ) : ViewHolder(binding.root){

        fun bind( carta: Carta){

            with(binding){

                textNome.text = carta.name

                Log.i("mensagem_api_cartas", "url: ${carta.img} \n")
                Picasso.get()
                    //.load( "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/9f1b15d759f1ddad40c9fabf8a6bccbabb525708891241bfd3053515dd6d2dc6.png" )
                    .load( carta.img )
                    //.load( "https://via.placeholder.com/150/92c952" )
                    .into( imageCarta )

            }

        }

    }

    private var listaCartas = emptyList<Carta>()
    fun atualizarLista( lista: List<Carta> ){
        listaCartas = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaoViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemRvCartaBinding.inflate(
            inflater, parent, false
        )

        return CartaoViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: CartaoViewHolder, position: Int) {
        val carta = listaCartas[position]
        holder.bind( carta )
    }

    override fun getItemCount(): Int {
        return listaCartas.size
    }

}