package com.paprolafsmnew.features.newcollectionreport

import com.paprolafsmnew.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}