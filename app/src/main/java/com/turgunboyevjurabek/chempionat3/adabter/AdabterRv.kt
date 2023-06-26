package com.turgunboyevjurabek.chempionat3.adabter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.turgunboyevjurabek.chempionat3.databinding.ItemRvBinding
import com.turgunboyevjurabek.chempionat3.models.Valyuta

class AdabterRv(val list: ArrayList<Valyuta>) :
    RecyclerView.Adapter<AdabterRv.Vh>() {
    inner class Vh(val itemrv: ItemRvBinding) : ViewHolder(itemrv.root) {
        fun onBind(valyuta: Valyuta) {
            itemrv.tht1.text=valyuta.title
            itemrv.tht2.text=valyuta.userId.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}