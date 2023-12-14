package com.paprolafsmnew.features.weather.api

import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.task.api.TaskApi
import com.paprolafsmnew.features.task.model.AddTaskInputModel
import com.paprolafsmnew.features.weather.model.ForeCastAPIResponse
import com.paprolafsmnew.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}