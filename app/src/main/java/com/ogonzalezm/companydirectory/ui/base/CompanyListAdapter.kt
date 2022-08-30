package com.ogonzalezm.companydirectory.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ogonzalezm.companydirectory.R
import com.ogonzalezm.companydirectory.data.Company

class CompanyListAdapter(
    val onItemSelected: (company: Company) -> (Unit)
): ListAdapter<Company, CompanyListAdapter.ViewHolder>(object: DiffUtil.ItemCallback<Company>(){
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyName: TextView = view.findViewById(R.id.name)
        val companyNit: TextView = view.findViewById(R.id.nit)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = getItem(position)
        holder.companyName.text = company.name
        holder.companyNit.text = "NIT: ${company.nit}"

        holder.itemView.setOnClickListener {
            onItemSelected(company)
        }
    }

}