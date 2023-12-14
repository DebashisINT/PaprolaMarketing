package com.paprolafsmnew.features.viewAllOrder.interf

import com.paprolafsmnew.app.domain.NewOrderGenderEntity
import com.paprolafsmnew.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}