package com.paprolafsm.features.login.model.productlistmodel

import com.paprolafsm.app.domain.ModelEntity
import com.paprolafsm.app.domain.ProductListEntity
import com.paprolafsm.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}