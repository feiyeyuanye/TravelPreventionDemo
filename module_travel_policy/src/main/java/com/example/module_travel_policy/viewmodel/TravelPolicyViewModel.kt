package com.example.module_travel_policy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.appbase.network.HttpErrorDeal
import com.example.module_travel_policy.respository.TravelPolicyRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * FileName: TravelPolicyViewModel
 * Founder: Jiang Houren
 * Create Date: 2022/8/25 11:54
 * Profile: CityDataViewModel
 */
class TravelPolicyViewModel : ViewModel() {
    /**
     * 查询检测机构信息
     * @param fromCityId 出发地城市
     * @param toCityId 目的地城市
     */
    fun queryTravelPolicy(
        fromCityId: String,
        toCityId: String
    ) = flow {
        val data = TravelPolicyRespository().queryTravelPolicy(
            fromCityId, toCityId
        )
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}
