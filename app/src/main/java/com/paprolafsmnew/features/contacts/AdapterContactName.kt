package com.paprolafsmnew.features.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import kotlinx.android.synthetic.main.row_cont_select.view.cb_row_cont_sel_name
import kotlinx.android.synthetic.main.row_cont_select.view.tv_row_cont_sel_name

class AdapterContactName(var mContext:Context,var mList:ArrayList<ContactDtls>,var listner:onClick):
    RecyclerView.Adapter<AdapterContactName.ContactNameViewHolder>(){

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactNameViewHolder {
        val v = layoutInflater.inflate(R.layout.row_cont_select, parent, false)
        return ContactNameViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactNameViewHolder, position: Int) {
        holder.bindItems()
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    inner class ContactNameViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(){
            itemView.apply {
                tv_row_cont_sel_name.text = mList.get(adapterPosition).name
                if(mList.get(adapterPosition).isTick){
                    cb_row_cont_sel_name.isChecked = true
                }else{
                    cb_row_cont_sel_name.isChecked = false
                }
                cb_row_cont_sel_name.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked){
                        listner.onTick(mList!!.get(adapterPosition))
                    }else{
                        listner.onUnTick(mList!!.get(adapterPosition))
                    }
                }
            }
        }
    }

    interface onClick{
        fun onTick(obj:ContactDtls)
        fun onUnTick(obj:ContactDtls)
    }

}