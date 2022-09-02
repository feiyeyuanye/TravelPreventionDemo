package com.example.module_test_agency.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.appbase.network.HttpErrorDeal
import com.example.module_test_agency.respository.AgencyRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * FileName: AgencyViewModel
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 10:30
 * Profile: CityDataViewModel
 */
class AgencyViewModel : ViewModel() {
    /**
     * 查询检测机构信息
     * @param cityId 城市id
     */
    fun loadTestAgencyMessage(cityId: String) = flow {
        val data = AgencyRespository().loadTestAgencyMessage(cityId)
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}