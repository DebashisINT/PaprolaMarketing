package com.paprolafsm.features.stock.api

import com.paprolafsm.app.Pref
import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.stock.model.AddStockInputParamsModel
import com.paprolafsm.features.stock.model.NewStockListResponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 17-09-2019.
 */
class StockRepository(val apiService: StockApi) {

    fun addStock(addStock: AddStockInputParamsModel): Observable<BaseResponse> {
        return apiService.addStock(addStock)
    }

    fun getStockList(): Observable<NewStockListResponseModel> {
        return apiService.getStockList(Pref.session_token!!, Pref.user_id!!)
    }
}