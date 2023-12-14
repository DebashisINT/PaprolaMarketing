package com.paprolafsmnew.app.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paprolafsmnew.app.AppConstant


@Dao
interface CallHisDao {

    @Insert
    fun insert(vararg obj: CallHisEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(kist: List<CallHisEntity>)

    @Query("select * from call_his")
    fun getAllData():List<CallHisEntity>

    @Query("select * from call_his where call_number=:call_number and call_date_time=:call_date_time and call_type=:call_type and call_duration_sec=:call_duration_sec")
    fun getFilterData(call_number:String,call_date_time:String,call_type:String,call_duration_sec:String):List<CallHisEntity>

    @Query("select * from call_his where shop_id=:shop_id")
    fun getCallListByID(shop_id:String):List<CallHisEntity>

}