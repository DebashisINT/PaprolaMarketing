package com.paprolafsmnew.features.login.model.alarmconfigmodel

import com.paprolafsmnew.base.BaseResponse

/**
 * Created by Saikat on 19-02-2019.
 */
class AlarmConfigResponseModel : BaseResponse() {
    var alarm_settings_list: ArrayList<AlarmConfigDataModel>? = null
}