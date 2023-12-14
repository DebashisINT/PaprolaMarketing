package com.paprolafsmnew.features.viewAllOrder.orderOptimized

import com.paprolafsmnew.app.domain.ProductOnlineRateTempEntity
import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}