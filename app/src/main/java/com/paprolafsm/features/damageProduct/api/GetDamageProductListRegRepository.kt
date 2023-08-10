package com.paprolafsm.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.paprolafsm.app.FileUtils
import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.NewQuotation.model.*
import com.paprolafsm.features.addshop.model.AddShopRequestData
import com.paprolafsm.features.addshop.model.AddShopResponse
import com.paprolafsm.features.damageProduct.model.DamageProductResponseModel
import com.paprolafsm.features.damageProduct.model.delBreakageReq
import com.paprolafsm.features.damageProduct.model.viewAllBreakageReq
import com.paprolafsm.features.login.model.userconfig.UserConfigResponseModel
import com.paprolafsm.features.myjobs.model.WIPImageSubmit
import com.paprolafsm.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}