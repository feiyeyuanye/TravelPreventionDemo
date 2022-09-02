package com.example.module_risk_level.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.appbase.network.HttpErrorDeal
import com.example.module_risk_level.respository.RiskLevelRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * FileName: RiskLevelViewModel
 * Founder: Jiang Houren
 * Create Date: 2022/8/25 08:46
 * Profile:
 */
class RiskLevelViewModel: ViewModel()  {

    fun loadRiskLevelMessage() = flow {
        val data = RiskLevelRespository().loadRiskLeverMessage()
        emit(data)
    }.catch {
        if (it is Exception){
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}
