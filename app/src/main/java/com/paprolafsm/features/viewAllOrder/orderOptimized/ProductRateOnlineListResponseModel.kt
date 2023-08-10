package com.paprolafsm.features.viewAllOrder.orderOptimized

import com.paprolafsm.app.domain.ProductOnlineRateTempEntity
import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}