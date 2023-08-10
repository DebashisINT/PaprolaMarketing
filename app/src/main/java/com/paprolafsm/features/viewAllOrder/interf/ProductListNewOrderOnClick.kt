package com.paprolafsm.features.viewAllOrder.interf

import com.paprolafsm.app.domain.NewOrderGenderEntity
import com.paprolafsm.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}