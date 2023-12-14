package com.paprolafsmnew.features.nearbyshops.presentation

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import com.paprolafsmnew.app.AppDatabase
import com.paprolafsmnew.app.domain.AddShopDBModelEntity
import com.paprolafsmnew.app.domain.CallHisEntity
import com.paprolafsmnew.app.types.FragType
import com.paprolafsmnew.app.utils.AppUtils
import com.paprolafsmnew.app.utils.PermissionUtils
import com.paprolafsmnew.app.utils.Toaster
import com.paprolafsmnew.base.presentation.BaseFragment
import com.paprolafsmnew.features.dashboard.presentation.DashboardActivity
import com.paprolafsmnew.features.leaveapplynew.LeaveHome
import lecho.lib.hellocharts.model.Line
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ShopCallHisFrag : BaseFragment(), View.OnClickListener {

    private lateinit var mContext: Context
    private var permissionUtils: PermissionUtils? = null
    private lateinit var rvCallLogL : RecyclerView
    private lateinit var adapterCallLogL : AdapterCallLogL
    private lateinit var tvShopInitial : TextView
    private lateinit var tvShopName : TextView
    private lateinit var tvShopAddr : TextView
    private lateinit var llShopAddr : LinearLayout

    private var shopDtls = AddShopDBModelEntity()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        var shopID: String = ""
        fun getInstance(objects: Any): ShopCallHisFrag {
            val shopCallHisFrag = ShopCallHisFrag()
            if (!TextUtils.isEmpty(objects.toString())) {
                shopID = objects.toString()
            }
            return shopCallHisFrag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frag_shop_call_log_his, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        rvCallLogL = view.findViewById(R.id.rv_frag_shop_call_log_list)
        tvShopInitial = view.findViewById(R.id.tv_frag_shop_call_his_shop_initial)
        tvShopName = view.findViewById(R.id.tv_frag_shop_call_his_shop_name)
        tvShopAddr = view.findViewById(R.id.tv_frag_shop_call_his_shop_addr)
        llShopAddr = view.findViewById(R.id.ll_frag_shop_call_log_his_addr)

        shopDtls = AppDatabase.getDBInstance()!!.addShopEntryDao().getShopByIdN(shopID)
        tvShopInitial.text = shopDtls.shopName!!.get(0).toString()
        tvShopName.text = shopDtls.shopName!!
        tvShopAddr.text = shopDtls.address!!

        llShopAddr.setOnClickListener(this)

        saveCallHisToDB()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ll_frag_shop_call_log_his_addr -> {
                var intentGmap: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${shopDtls.shopLat},${shopDtls.shopLong}&mode=1"))
                intentGmap.setPackage("com.google.android.apps.maps")
                if(intentGmap.resolveActivity(mContext.packageManager) !=null){
                    mContext.startActivity(intentGmap)
                }
            }
        }
    }

    fun saveCallHisToDB(){
        var permissionList = arrayOf<String>(Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_CONTACTS)
        permissionUtils = PermissionUtils(mContext as Activity, object : PermissionUtils.OnPermissionListener {
            @TargetApi(Build.VERSION_CODES.M)
            override fun onPermissionGranted() {
                doAsync {
                    var callHisL = AppUtils.obtenerDetallesLlamadas(mContext) as ArrayList<AppUtils.Companion.PhoneCallDtls>
                    if(callHisL.size>0){
                        for(i in 0..callHisL.size-1){
                            try{
                                var obj:CallHisEntity = CallHisEntity()
                                var callNo = if(callHisL.get(i).number!!.length>10) callHisL.get(i).number!!.replace("+","").removeRange(0,2) else callHisL.get(i).number!!
                                var isMyShop = AppDatabase.getDBInstance()!!.addShopEntryDao().getShopByIdPhone(shopID!!,callNo) as ArrayList<AddShopDBModelEntity>
                                if(isMyShop.size>0){
                                    obj.apply {
                                        shop_id = shopID
                                        call_number = callNo
                                        call_date = callHisL.get(i).callDateTime!!.split(" ").get(0)
                                        call_time = callHisL.get(i).callDateTime!!.split(" ").get(1)
                                        call_date_time = callHisL.get(i).callDateTime!!
                                        call_type = callHisL.get(i).type!!
                                        call_duration_sec = callHisL.get(i).callDuration!!
                                    }
                                    var isPresent = (AppDatabase.getDBInstance()!!.callhisDao().getFilterData(obj.call_number,obj.call_date_time,obj.call_type,obj.call_duration_sec) as ArrayList<CallHisEntity>).size
                                    if(isPresent==0){
                                        AppDatabase.getDBInstance()!!.callhisDao().insert(obj)
                                    }
                                }
                            }catch (ex:Exception){
                                ex.printStackTrace()
                            }
                        }
                    }
                    uiThread {
                        showCallHisData()
                    }
                }
            }
            override fun onPermissionNotGranted() {

            }
        },permissionList)
    }

    fun showCallHisData(){
        var callL = AppDatabase.getDBInstance()!!.callhisDao().getCallListByID(shopID) as ArrayList<CallHisEntity>
        if(callL.size>0){
            adapterCallLogL = AdapterCallLogL(mContext,callL)
            rvCallLogL.adapter=adapterCallLogL
        }else{
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_available))
        }
    }

}