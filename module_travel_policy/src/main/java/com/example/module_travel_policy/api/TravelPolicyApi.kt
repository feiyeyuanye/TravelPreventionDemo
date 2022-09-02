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

package com.example.module_travel_policy.api

import com.example.appbase.network.BaseApi
import com.example.appbase.network.BaseReqData
import com.example.module_travel_policy.bean.TravelPolicyDataBean
import retrofit2.http.GET
import retrofit2.http.Query

interface TravelPolicyApi {
    /**
     * 查询出行政策
     * @param key 接口key值 无须传
     * @param fromCityId 出发地城市id
     * @param toCityId 目的地城市id
     */
    @GET("query")
    suspend fun queryTravelPolicy(
        @Query("from") fromCityId: String,
        @Query("to") toCityId: String,
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<TravelPolicyDataBean>
}