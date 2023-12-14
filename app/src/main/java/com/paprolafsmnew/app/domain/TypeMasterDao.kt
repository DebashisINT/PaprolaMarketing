package com.paprolafsmnew.app.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TypeMasterDao {

    @Insert
    fun insert(vararg obj: TypeMasterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(kist: List<TypeMasterEntity>)

    @Query("select * from crm_type_master")
    fun getAll(): List<TypeMasterEntity>
}