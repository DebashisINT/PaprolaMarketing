package com.paprolafsm.features.viewAllOrder.model

import com.paprolafsm.app.domain.NewOrderColorEntity
import com.paprolafsm.app.domain.NewOrderGenderEntity
import com.paprolafsm.app.domain.NewOrderProductEntity
import com.paprolafsm.app.domain.NewOrderSizeEntity
import com.paprolafsm.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

