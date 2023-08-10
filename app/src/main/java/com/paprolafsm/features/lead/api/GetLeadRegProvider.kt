package com.paprolafsm.features.lead.api

import com.paprolafsm.features.NewQuotation.api.GetQuotListRegRepository
import com.paprolafsm.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}