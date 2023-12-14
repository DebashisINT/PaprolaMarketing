package com.paprolafsmnew.features.activities.api

import com.paprolafsmnew.features.member.api.TeamApi
import com.paprolafsmnew.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}