/*
 *
 *  * Copyright (C)  HuangLinqing, TravelPrevention Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.module_city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.module_city.respository.CityRespository
import com.example.appbase.network.HttpErrorDeal
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：CityDataViewModel
 */
class CityDataViewModel : ViewModel() {

    /**
     * 加载城市清单
     * 通过 Flow 的 .asLiveData 方法将结果暴露给 UI 层。
     */
    fun loadCityData() = flow {
        val data = CityRespository().loadCityData()
        // 通过 emit 函数发送结果
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
//        如果遇到了异常则发送一个 null 值，并通过 HttpErrorDeal 类统一处理异常信息
        emit(null)
    }.asLiveData()
}