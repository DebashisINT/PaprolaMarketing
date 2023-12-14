package com.paprolafsmnew.features.contacts

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import com.paprolafsmnew.app.AppDatabase
import com.paprolafsmnew.app.Pref
import com.paprolafsmnew.app.domain.AddShopDBModelEntity
import com.paprolafsmnew.app.domain.QuestionEntity
import com.paprolafsmnew.app.domain.QuestionSubmitEntity
import com.paprolafsmnew.app.types.FragType
import com.paprolafsmnew.app.uiaction.IntentActionable
import com.paprolafsmnew.app.utils.AppUtils
import com.paprolafsmnew.app.utils.PermissionUtils
import com.paprolafsmnew.app.utils.Toaster
import com.paprolafsmnew.app.widgets.MovableFloatingActionButton
import com.paprolafsmnew.base.presentation.BaseFragment
import com.paprolafsmnew.features.addshop.presentation.AdapterQuestionList
import com.paprolafsmnew.features.addshop.presentation.AddShopFragment
import com.paprolafsmnew.features.dashboard.presentation.DashboardActivity
import com.paprolafsmnew.features.location.LocationWizard
import com.paprolafsmnew.features.location.SingleShotLocationProvider
import com.paprolafsmnew.features.viewAllOrder.interf.QaOnCLick
import com.paprolafsmnew.widgets.AppCustomTextView
import com.pnikosis.materialishprogress.ProgressWheel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.Random


class ContactsFrag : BaseFragment(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var mFab: MovableFloatingActionButton
    private lateinit var ivContactSync: ImageView
    private lateinit var ivContactSyncDel: ImageView
    private lateinit var adapterContGr:AdapterContactGr
    private lateinit var adapterContName:AdapterContactName
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var rvContactL: RecyclerView

    private var locationStr:String = ""
    private var location_pinStr:String = ""

    private lateinit var adapterContactList: AdapterContactList

    private var permissionUtils: PermissionUtils? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frag_contacts, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        progress_wheel = view.findViewById(R.id.progress_wheel_frag_add_cont)
        rvContactL = view.findViewById(R.id.rv_frag_contacts_list)
        mFab = view.findViewById(R.id.fab_frag_contacts_add_contacts)
        ivContactSync = view.findViewById(R.id.iv_frag_contacts_dialog)
        ivContactSyncDel = view.findViewById(R.id.iv_frag_contacts_dialog_del)
        mFab.setOnClickListener(this)
        ivContactSync.setOnClickListener(this)
        ivContactSyncDel.setOnClickListener(this)
        mFab.setCustomClickListener {
            (mContext as DashboardActivity).loadFragment(FragType.ContactsAddFrag, true, "")
        }
        initPermissionCheckOne()
        singleLocation()
        shopContactList()
    }

    fun initPermissionCheckOne() {
        var permissionList = arrayOf<String>( Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_CONTACTS)
        permissionUtils = PermissionUtils(mContext as Activity, object : PermissionUtils.OnPermissionListener {
            @TargetApi(Build.VERSION_CODES.M)
            override fun onPermissionGranted() {

            }
            override fun onPermissionNotGranted() {

            }
        },permissionList)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iv_frag_contacts_dialog_del->{
                AppDatabase.getDBInstance()!!.addShopEntryDao().del99()
                shopContactList()
            }
            R.id.iv_frag_contacts_dialog -> {
                val contGrDialog = Dialog(mContext)
                contGrDialog.setCancelable(true)
                contGrDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                contGrDialog.setContentView(R.layout.dialog_contact_gr)
                val tvHeader = contGrDialog.findViewById(R.id.dialog_contact_gr_header) as TextView
                val rvContactGrName = contGrDialog.findViewById(R.id.rv_dialog_cont_gr) as RecyclerView
                tvHeader.text = "Please select Contact Group"
                contGrDialog.show()
                var grNameL = AppUtils.getPhoneBookGroups(mContext) as ArrayList<ContactGr>
                if(grNameL.size>0){
                    adapterContGr = AdapterContactGr(mContext,grNameL,object :AdapterContactGr.onClick
                    {
                        override fun onGrClick(obj: ContactGr) {
                            contGrDialog.dismiss()
                            showContactNameL(obj)
                        }
                    })
                    rvContactGrName.adapter = adapterContGr
                }
            }
        }
    }

    private fun singleLocation() {
        try{
            progress_wheel.spin()
            SingleShotLocationProvider.requestSingleUpdate(mContext,
                object : SingleShotLocationProvider.LocationCallback {
                    override fun onStatusChanged(status: String) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onProviderEnabled(status: String) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onProviderDisabled(status: String) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNewLocationAvailable(location: Location) {
                        if(location!=null){
                            locationStr = LocationWizard.getNewLocationName(mContext, location.latitude, location.longitude)
                            location_pinStr = LocationWizard.getPostalCode(mContext, location.latitude, location.longitude)
                        } else{
                            var lloc: Location = Location("")
                            lloc.latitude=Pref.current_latitude.toDouble()
                            lloc.longitude=Pref.current_longitude.toDouble()
                            locationStr = LocationWizard.getNewLocationName(mContext, lloc.latitude, lloc.longitude)
                            location_pinStr = LocationWizard.getPostalCode(mContext, lloc.latitude, lloc.longitude)
                        }
                        progress_wheel.stopSpinning()
                    }

                })
        }catch (ex:Exception){
            ex.printStackTrace()
            progress_wheel.stopSpinning()
        }
    }

    fun showContactNameL(obj:ContactGr){
        var contactL : ArrayList<ContactDtls> = AppUtils.getContactsFormGroup(obj.gr_id,obj.gr_name,mContext)
        var contactLTemp : ArrayList<ContactDtls> = ArrayList()
        contactLTemp.addAll(contactL)
        if(contactL.size>0){
            val contactDialog = Dialog(mContext)
            contactDialog.setCancelable(true)
            contactDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            contactDialog.setContentView(R.layout.dialog_cont_select)
            val rvContactL = contactDialog.findViewById(R.id.rv_dialog_cont_list) as RecyclerView
            val tvHeader = contactDialog.findViewById(R.id.tv_dialog_cont_sel_header) as TextView
            val submit = contactDialog.findViewById(R.id.tv_dialog_cont_list_submit) as TextView
            tvHeader.text = "Please select Contact(s)"

            for(i in 0..contactL.size-1){
                var ob = contactL.get(i)
                var  isPresentInDb= (AppDatabase.getDBInstance()!!.addShopEntryDao().getCustomData(ob.name,ob.number) as ArrayList<AddShopDBModelEntity>).size
                if(isPresentInDb!=0){
                    contactLTemp.remove(ob)
                }
            }
            if(contactLTemp.size>0){
                contactL=contactLTemp
                var contactTickL : ArrayList<ContactDtls> = ArrayList()
                rvContactL.layoutManager = LinearLayoutManager(mContext)
                adapterContName = AdapterContactName(mContext,contactL,object :AdapterContactName.onClick{
                    override fun onTick(obj: ContactDtls) {
                        contactTickL.add(obj)
                    }
                    override fun onUnTick(obj: ContactDtls) {
                        contactTickL.remove(obj)
                    }
                })
                submit.setOnClickListener {
                    if(contactTickL.size>0){
                        contactDialog.dismiss()
                        submitContact(contactTickL)
                    }else{
                        contactDialog.dismiss()
                        Toaster.msgShort(mContext,"Please select Contact(s)")
                    }
                }
                rvContactL.adapter = adapterContName
                contactDialog.show()
            }else{
                Toaster.msgShort(mContext,"No Contact avaliable")
            }
        }
    }

    fun submitContact(contactTickL : ArrayList<ContactDtls>){
        doAsync {
            progress_wheel.spin()
            for(i in 0..contactTickL.size-1){
                var shopObj:AddShopDBModelEntity = AddShopDBModelEntity()
                val random = Random()
                shopObj.shop_id = Pref.user_id + "_" + System.currentTimeMillis().toString() +  (random.nextInt(999 - 100) + 100).toString()
                shopObj.shopName = contactTickL.get(i).name
                shopObj.companyName_id = ""
                shopObj.companyName = ""
                shopObj.jobTitle = contactTickL.get(i).gr_name
                shopObj.ownerEmailId = ""
                shopObj.ownerName = contactTickL.get(i).name
                shopObj.ownerContactNumber = contactTickL.get(i).number
                shopObj.address = locationStr
                shopObj.pinCode = location_pinStr
                shopObj.crm_assignTo=Pref.user_id
                shopObj.type_id = ""
                shopObj.crm_status = ""
                shopObj.crm_source = ""
                shopObj.crm_reference = ""
                shopObj.remarks = "sync from phone contact"
                shopObj.amount = ""
                shopObj.type="99"
                shopObj.added_date = AppUtils.getCurrentDateTime()
                shopObj.isUploaded = true
                AppDatabase.getDBInstance()!!.addShopEntryDao().insert(shopObj)
            }
            uiThread {
                Handler().postDelayed(Runnable {
                    progress_wheel.stopSpinning()
                    shopContactList()
                }, 1500)
            }
        }
    }

    fun shopContactList(){
        var contL = AppDatabase.getDBInstance()!!.addShopEntryDao().getContatcShops() as ArrayList<AddShopDBModelEntity>
        if(contL.size>0){
            (mContext as DashboardActivity).setTopBarTitle("Contact(s) : ${contL.size}")
            adapterContactList = AdapterContactList(mContext,contL,object :AdapterContactList.onClick{
                override fun onCallClick(obj: AddShopDBModelEntity) {
                    IntentActionable.initiatePhoneCall(mContext, obj.ownerContactNumber)
                }
            })
            rvContactL.adapter = adapterContactList
            rvContactL.visibility = View.VISIBLE
        }else{
            (mContext as DashboardActivity).setTopBarTitle("Contact(s)")
            rvContactL.visibility = View.GONE
        }
    }


}