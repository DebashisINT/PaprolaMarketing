package com.paprolafsmnew.features.stockAddCurrentStock.api

import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.location.model.ShopRevisitStatusRequest
import com.paprolafsmnew.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.paprolafsmnew.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.paprolafsmnew.features.stockAddCurrentStock.model.CurrentStockGetData
import com.paprolafsmnew.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}