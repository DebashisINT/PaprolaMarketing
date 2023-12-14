package com.paprolafsmnew.features.photoReg.present

import com.paprolafsmnew.app.domain.ProspectEntity
import com.paprolafsmnew.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}