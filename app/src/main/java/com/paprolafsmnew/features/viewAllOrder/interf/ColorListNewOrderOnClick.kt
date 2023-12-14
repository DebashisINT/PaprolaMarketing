package com.paprolafsmnew.features.viewAllOrder.interf

import com.paprolafsmnew.app.domain.NewOrderColorEntity
import com.paprolafsmnew.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}