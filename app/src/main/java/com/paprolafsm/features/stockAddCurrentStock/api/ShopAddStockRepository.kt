package com.paprolafsm.features.stockAddCurrentStock.api

import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.location.model.ShopRevisitStatusRequest
import com.paprolafsm.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.paprolafsm.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.paprolafsm.features.stockAddCurrentStock.model.CurrentStockGetData
import com.paprolafsm.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}