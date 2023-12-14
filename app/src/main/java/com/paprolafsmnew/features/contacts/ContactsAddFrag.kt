package com.paprolafsmnew.features.contacts

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.paprolafsmnew.R
import com.paprolafsmnew.app.AppDatabase
import com.paprolafsmnew.app.NetworkConstant
import com.paprolafsmnew.app.Pref
import com.paprolafsmnew.app.domain.AddShopDBModelEntity
import com.paprolafsmnew.app.domain.CompanyMasterEntity
import com.paprolafsmnew.app.domain.NewOrderColorEntity
import com.paprolafsmnew.app.domain.NewOrderProductEntity
import com.paprolafsmnew.app.utils.AppUtils
import com.paprolafsmnew.app.utils.Toaster
import com.paprolafsmnew.base.presentation.BaseActivity
import com.paprolafsmnew.base.presentation.BaseFragment
import com.paprolafsmnew.features.dashboard.presentation.DashboardActivity
import com.paprolafsmnew.features.location.LocationWizard
import com.paprolafsmnew.features.location.SingleShotLocationProvider
import com.paprolafsmnew.features.member.api.TeamRepoProvider
import com.paprolafsmnew.features.member.model.TeamListDataModel
import com.paprolafsmnew.features.member.model.TeamListResponseModel
import com.paprolafsmnew.features.viewAllOrder.presentation.ProductListNewOrderDialog
import com.google.android.material.textfield.TextInputEditText
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.Random

class ContactsAddFrag : BaseFragment(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var et_fName: TextInputEditText
    private lateinit var et_lName: TextInputEditText
    private lateinit var et_companyName: TextInputEditText
    private lateinit var rv_companyNameHint: RecyclerView
    private lateinit var et_jobTitle: TextInputEditText
    private lateinit var et_email: TextInputEditText
    private lateinit var et_phone: TextInputEditText
    private lateinit var et_addr: TextInputEditText
    private lateinit var et_pin: TextInputEditText
    private lateinit var et_assignTo: TextView
    private lateinit var et_typeID: TextView
    private lateinit var et_statusID: TextView
    private lateinit var et_remarks: TextInputEditText
    private lateinit var et_saleValue: TextInputEditText

    private lateinit var cvSubmit: CardView

    private lateinit var adapterCompanyNameHint: AdapterContactCompany
    private lateinit var member_list: ArrayList<TeamListDataModel>

    private var locationStr:String = ""
    private var location_pinStr:String = ""
    private var str_assignTo:String = Pref.user_id!!
    private var str_companyId:String = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frag_contacts_add, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        // set views
        progress_wheel = view.findViewById(R.id.progress_wheel_frag_add_cont)
        et_fName = view.findViewById(R.id.et_frag_cont_add_fname)
        et_lName = view.findViewById(R.id.et_frag_cont_add_lname)
        et_companyName = view.findViewById(R.id.et_frag_cont_add_conpany_name)
        rv_companyNameHint = view.findViewById(R.id.rv_frag_contact_add_company_hint)
        et_jobTitle = view.findViewById(R.id.et_frag_cont_add_job_title)
        et_email = view.findViewById(R.id.et_frag_cont_add_email)
        et_phone = view.findViewById(R.id.et_frag_cont_add_phone)
        et_addr = view.findViewById(R.id.et_frag_cont_add_addr)
        et_pin = view.findViewById(R.id.et_frag_cont_add_pin)
        et_assignTo = view.findViewById(R.id.et_frag_cont_add_assign_to)
        et_typeID = view.findViewById(R.id.et_frag_cont_add_type_id)
        et_statusID = view.findViewById(R.id.et_frag_cont_add_status_id)
        et_remarks = view.findViewById(R.id.et_frag_cont_add_remarks)
        et_saleValue = view.findViewById(R.id.et_frag_cont_add_sale_value)

        cvSubmit = view.findViewById(R.id.cv_frag_cont_add_submit)

        // set onclick listners
        et_assignTo.setOnClickListener(this)
        cvSubmit.setOnClickListener(this)
        et_typeID.setOnClickListener(this)
        et_statusID.setOnClickListener(this)

        rv_companyNameHint.visibility = View.GONE

        progress_wheel.spin()
        singleLocation()

        Handler().postDelayed(Runnable {
            setData()
        }, 1500)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.cv_frag_cont_add_submit ->{
                AppUtils.hideSoftKeyboard(mContext as DashboardActivity)
                submitValidationCheck()
            }
            R.id.et_frag_cont_add_assign_to->{
                getTeamList()
            }
            R.id.et_frag_cont_add_type_id->{
                Toaster.msgShort(mContext,"ty")
            }
            R.id.et_frag_cont_add_status_id ->{
                Toaster.msgShort(mContext,"st")
            }
        }
    }

    fun submitValidationCheck(){

        progress_wheel.spin()

        if(et_fName.text.toString().length==0){
            et_fName.requestFocus()
            et_fName.setError("Enter First Name")
            progress_wheel.stopSpinning()
            return
        }
        if(et_lName.text.toString().length==0){
            et_lName.requestFocus()
            et_lName.setError("Enter Last Name")
            progress_wheel.stopSpinning()
            return
        }
        if(et_phone.text.toString().length==0){
            et_phone.requestFocus()
            et_phone.setError("Enter Phone No.")
            progress_wheel.stopSpinning()
            return
        }
        if(AppDatabase.getDBInstance()!!.addShopEntryDao().getDuplicateShopData(et_phone.text.toString()).size > 0){
            et_phone.requestFocus()
            et_phone.setError("Duplicate Phone No.")
            progress_wheel.stopSpinning()
            return
        }

        var shopObj = AddShopDBModelEntity()
        val random = Random()
        shopObj.shop_id = Pref.user_id + "_" + System.currentTimeMillis().toString() +  (random.nextInt(999 - 100) + 100).toString()
        shopObj.shopName = et_fName.text.toString()+" "+et_lName.text.toString()
        shopObj.companyName_id = str_companyId
        shopObj.companyName = et_companyName.text.toString()
        shopObj.jobTitle = et_jobTitle.text.toString()
        shopObj.ownerEmailId = et_email.text.toString()
        shopObj.ownerName = et_fName.text.toString()+" "+et_lName.text.toString()
        shopObj.ownerContactNumber = et_phone.text.toString()
        shopObj.address = et_addr.text.toString()
        shopObj.pinCode = et_pin.text.toString()
        shopObj.crm_assignTo = str_assignTo
        shopObj.type_id = et_typeID.text.toString()
        shopObj.crm_status=""
        shopObj.crm_source=""
        shopObj.crm_reference=""
        shopObj.remarks = et_remarks.text.toString()
        shopObj.amount = et_saleValue.text.toString()
        shopObj.type = "99"
        shopObj.added_date = AppUtils.getCurrentDateTime()
        shopObj.isUploaded = true
        AppDatabase.getDBInstance()!!.addShopEntryDao().insert(shopObj)

        Handler().postDelayed(Runnable {
            progress_wheel.stopSpinning()
            Toaster.msgShort(mContext,"Contact added successfully.")
            (mContext as DashboardActivity).onBackPressed()
        }, 1500)

    }

    private fun singleLocation() {
        try{
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

                        et_addr.setText(locationStr)
                        et_pin.setText(location_pinStr)
                        progress_wheel.stopSpinning()
                    }

                })
        }catch (ex:Exception){
            ex.printStackTrace()
            progress_wheel.stopSpinning()
        }
    }

    private fun setData(){
        var compL:ArrayList<CompanyMasterEntity> = AppDatabase.getDBInstance()?.companyMasterDao()?.getAll() as ArrayList<CompanyMasterEntity>
        adapterCompanyNameHint = AdapterContactCompany(mContext,compL,object :AdapterContactCompany.onClick{
            override fun onNameClick(obj: CompanyMasterEntity) {
                et_companyName.setText(obj.company_name)
                et_companyName.setSelection(obj.company_name.length)
                str_companyId = obj.company_id.toString()
                rv_companyNameHint.visibility = View.GONE
            }

            override fun onNoData(nodata: Boolean) {
                if(nodata){
                    rv_companyNameHint.visibility = View.GONE
                }else{
                    rv_companyNameHint.visibility = View.VISIBLE
                }
            }
        })
        rv_companyNameHint.adapter = adapterCompanyNameHint

        et_companyName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var str = p0.toString()
                if(str.length>1){
                    adapterCompanyNameHint?.filter.filter(str)
                    rv_companyNameHint.visibility = View.VISIBLE
                }else{
                    rv_companyNameHint.visibility = View.GONE
                }

            }
        })
    }

    private fun getTeamList() {
        if (!AppUtils.isOnline(mContext)) {
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
            return
        }
        progress_wheel.spin()
        val repository = TeamRepoProvider.teamRepoProvider()
        BaseActivity.compositeDisposable.add(
            repository.teamList(Pref.user_id!!, false, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    val response = result as TeamListResponseModel
                    if (response.status == NetworkConstant.SUCCESS) {
                        progress_wheel.stopSpinning()
                        if (response.member_list != null && response.member_list!!.size > 0) {
                            member_list = ArrayList()
                            var member_list: ArrayList<TeamListDataModel> = response.member_list!!
                            loadTeamMember(member_list)
                        } else {
                            (mContext as DashboardActivity).showSnackMessage(response.message!!)
                        }
                    } else {
                        progress_wheel.stopSpinning()
                        (mContext as DashboardActivity).showSnackMessage(response.message!!)
                    }
                }, { error ->
                    error.printStackTrace()
                    progress_wheel.stopSpinning()
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                })
        )
    }

    private fun loadTeamMember(member_list:ArrayList<TeamListDataModel>) {
        if (member_list != null && member_list.isNotEmpty()) {
            TeamShowDialog.newInstance(member_list as ArrayList<TeamListDataModel>) {
                et_assignTo.text = it.user_name
                str_assignTo = it.user_id
            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        } else {
            Toaster.msgShort(mContext, "No Member Found")
        }
    }


}