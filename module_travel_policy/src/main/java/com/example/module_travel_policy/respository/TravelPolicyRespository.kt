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

package com.example.module_travel_policy.respository

import com.example.appbase.network.BaseReqData
import com.example.appbase.network.RetrofitServiceBuilder
import com.example.module_travel_policy.api.TravelPolicyApi
import com.example.module_travel_policy.bean.TravelPolicyDataBean

/**
 * 数据仓库层
 */
//@AndroidEntryPoint
class TravelPolicyRespository {
    //创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        TravelPolicyApi::class.java
    )
//    @Inject
//    lateinit var netWork:TravelPolicyApi
    /**
     * 查询检测机构信息
     * @param fromCityId 出发城市id
     * @param toCityId 目的地城市id
     */
    suspend fun queryTravelPolicy(
        fromCityId: String,
        toCityId: String
    ): BaseReqData<TravelPolicyDataBean>? {
        netWork?.let {
            return it.queryTravelPolicy(fromCityId, toCityId)
        }
        return null
    }
}