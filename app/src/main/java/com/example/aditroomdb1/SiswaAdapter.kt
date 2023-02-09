package com.example.aditroomdb1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aditroomdb1.Room.TbSiswa
import kotlinx.android.synthetic.main.adapter_siswa.view.*

class SiswaAdapter (private  val siswas: ArrayList<TbSiswa>, private val listener: onAdapterListener) :
    RecyclerView.Adapter<SiswaAdapter.ViewHolder>() {

    class ViewHolder (val view: View): RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_siswa, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val siswa = siswas[position]
        holder.view.text_title.text = siswa.nama
        holder.view.text_title.setOnClickListener {
            listener.onClick(siswa)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(siswa)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(siswa)
        }
    }

    override fun getItemCount()=siswas.size


    fun setData(list: List<TbSiswa>) {
        siswas.clear()
        siswas.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClick(siswa: TbSiswa)
        fun onUpdate(siswa: TbSiswa)
        fun onDelete(siswa: TbSiswa)
    }

}