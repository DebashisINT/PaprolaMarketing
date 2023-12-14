package com.paprolafsmnew.features.nearbyshops.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import com.paprolafsmnew.app.domain.CallHisEntity
import com.paprolafsmnew.app.utils.AppUtils
import kotlinx.android.synthetic.main.row_call_log_list.view.iv_row_call_log_his_type
import kotlinx.android.synthetic.main.row_call_log_list.view.tv_row_call_log_his_date
import kotlinx.android.synthetic.main.row_call_log_list.view.tv_row_call_log_his_duration
import kotlinx.android.synthetic.main.row_call_log_list.view.tv_row_call_log_his_sync_status
import kotlinx.android.synthetic.main.row_call_log_list.view.tv_row_call_log_his_time

class AdapterCallLogL(var mContext: Context,var callL:ArrayList<CallHisEntity>):RecyclerView.Adapter<AdapterCallLogL.CallLogLViewHolder>() {

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogLViewHolder {
        val v = layoutInflater.inflate(R.layout.row_call_log_list, parent, false)
        return CallLogLViewHolder(v)
    }

    override fun onBindViewHolder(holder: CallLogLViewHolder, position: Int) {
        holder.bindItems()
    }

    override fun getItemCount(): Int {
        return callL.size
    }

    inner class CallLogLViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(){
            itemView.apply {
                tv_row_call_log_his_date.text = AppUtils.getFormatedDateNew(callL.get(adapterPosition).call_date,"yy-mm-dd","dd-mm-yy")
                tv_row_call_log_his_time.text = callL.get(adapterPosition).call_time
                if(callL.get(adapterPosition).call_type.equals("OUTGOING",ignoreCase = true)){
                    iv_row_call_log_his_type.setImageResource(R.drawable.ic_outgoing_call)
                }else if(callL.get(adapterPosition).call_type.equals("INCOMING",ignoreCase = true)){
                    iv_row_call_log_his_type.setImageResource(R.drawable.ic_incomming_call)
                }
                if(callL.get(adapterPosition).isUploaded){
                    tv_row_call_log_his_sync_status.setImageResource(R.drawable.ic_registered_shop_sync)
                }else{
                    tv_row_call_log_his_sync_status.setImageResource(R.drawable.ic_registered_shop_not_sync)
                }
                tv_row_call_log_his_duration.text = AppUtils.getMMSSfromSeconds(callL.get(adapterPosition).call_duration_sec.toInt())
            }
        }
    }

}