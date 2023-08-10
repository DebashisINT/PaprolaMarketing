package com.paprolafsm.features.orderList.model

import com.paprolafsm.base.BaseResponse


class ReturnListResponseModel: BaseResponse() {
    var return_list: ArrayList<ReturnDataModel>? = null
}