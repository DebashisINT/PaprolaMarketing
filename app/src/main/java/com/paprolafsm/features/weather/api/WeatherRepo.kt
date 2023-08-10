package com.paprolafsm.features.weather.api

import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.task.api.TaskApi
import com.paprolafsm.features.task.model.AddTaskInputModel
import com.paprolafsm.features.weather.model.ForeCastAPIResponse
import com.paprolafsm.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}