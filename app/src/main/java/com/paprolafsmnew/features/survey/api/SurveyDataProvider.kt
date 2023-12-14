package com.paprolafsmnew.features.survey.api

import com.paprolafsmnew.features.photoReg.api.GetUserListPhotoRegApi
import com.paprolafsmnew.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}