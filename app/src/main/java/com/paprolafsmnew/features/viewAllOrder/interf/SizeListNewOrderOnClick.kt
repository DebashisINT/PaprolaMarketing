package com.paprolafsmnew.features.viewAllOrder.interf

import com.paprolafsmnew.app.domain.NewOrderProductEntity
import com.paprolafsmnew.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}