package com.paprolafsmnew.features.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import com.paprolafsmnew.app.domain.AddShopDBModelEntity
import kotlinx.android.synthetic.main.row_contact_list.view.iv_row_cont_list_cont_number
import kotlinx.android.synthetic.main.row_contact_list.view.tv_row_cont_list_added_dt_ti
import kotlinx.android.synthetic.main.row_contact_list.view.tv_row_cont_list_addr
import kotlinx.android.synthetic.main.row_contact_list.view.tv_row_cont_list_cont_number
import kotlinx.android.synthetic.main.row_contact_list.view.tv_row_cont_list_name
import kotlinx.android.synthetic.main.row_contact_list.view.tv_row_cont_list_name_initial

class AdapterContactList(var mContext:Context,var shopL:ArrayList<AddShopDBModelEntity>,var listner:onClick):
    RecyclerView.Adapter<AdapterContactList.ContactListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        var v = LayoutInflater.from(mContext).inflate(R.layout.row_contact_list,parent,false)
        return ContactListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.bindItems()
    }

    override fun getItemCount(): Int {
        return shopL.size
    }

    inner class ContactListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(){
            itemView.apply {
                tv_row_cont_list_name_initial.text= shopL.get(adapterPosition).shopName!!.get(0).toString()
                tv_row_cont_list_name.text = shopL.get(adapterPosition).shopName
                tv_row_cont_list_addr.text = shopL.get(adapterPosition).address
                tv_row_cont_list_cont_number.text = shopL.get(adapterPosition).ownerContactNumber
                tv_row_cont_list_added_dt_ti.text = shopL.get(adapterPosition).added_date.replace("T"," ")
                iv_row_cont_list_cont_number.setOnClickListener {
                    listner.onCallClick(shopL.get(adapterPosition))
                }
            }
        }
    }

    interface onClick{
        fun onCallClick(obj:AddShopDBModelEntity)
    }

}