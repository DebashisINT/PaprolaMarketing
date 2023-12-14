package com.paprolafsmnew.features.viewAllOrder.model

import com.paprolafsmnew.app.domain.NewOrderColorEntity
import com.paprolafsmnew.app.domain.NewOrderGenderEntity
import com.paprolafsmnew.app.domain.NewOrderProductEntity
import com.paprolafsmnew.app.domain.NewOrderSizeEntity
import com.paprolafsmnew.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

