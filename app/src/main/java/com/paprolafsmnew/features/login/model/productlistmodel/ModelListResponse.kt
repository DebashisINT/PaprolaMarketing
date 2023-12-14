package com.paprolafsmnew.features.login.model.productlistmodel

import com.paprolafsmnew.app.domain.ModelEntity
import com.paprolafsmnew.app.domain.ProductListEntity
import com.paprolafsmnew.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}