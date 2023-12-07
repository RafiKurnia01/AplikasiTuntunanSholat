package com.azhar.bacaansholat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.bacaansholat.R
import com.azhar.bacaansholat.model.ModelBacaan
import kotlinx.android.synthetic.main.list_niat_shalat.view.txtArabic
import kotlinx.android.synthetic.main.list_niat_shalat.view.txtId
import kotlinx.android.synthetic.main.list_niat_shalat.view.txtLatin
import kotlinx.android.synthetic.main.list_niat_shalat.view.txtName
import kotlinx.android.synthetic.main.list_niat_shalat.view.txtTerjemahan

class DoaAdapter(private val modelBacaan: List<ModelBacaan>) : RecyclerView.Adapter<DoaAdapter.ListViewHolder>() {

    public class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtId: TextView
        var txtName: TextView
        var txtArabic: TextView
        var txtLatin: TextView
        var txtTerjemahan: TextView

        init{
            txtId = itemView.txtId
            txtName = itemView.txtName
            txtArabic = itemView.txtArabic
            txtLatin = itemView.txtLatin
            txtTerjemahan = itemView.txtTerjemahan
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_doa, viewGroup, false)
        return DoaAdapter.ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.txtId.text = dataModel.id
        listViewHolder.txtName.text = dataModel.name
        listViewHolder.txtArabic.text = dataModel.arabic
        listViewHolder.txtLatin.text = dataModel.latin
        listViewHolder.txtTerjemahan.text = dataModel.terjemahan
    }
}