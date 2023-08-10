package com.paprolafsm.features.viewAllOrder.interf

import com.paprolafsm.app.domain.NewOrderGenderEntity
import com.paprolafsm.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}