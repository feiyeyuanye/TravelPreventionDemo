package com.example.module_city.respository

import android.net.Network
import com.example.appbase.network.BaseReqData
import com.example.appbase.network.RetrofitServiceBuilder
import com.example.module_city.api.CityApi
import com.example.module_city.bean.CityDataBean


/**
 * FileName: CityRespository
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 08:15
 * Profile: 数据仓库层
 */
class CityRespository {

    //创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        CityApi::class.java
    )

    /**
     * 加载城市清单
     */
    suspend fun loadCityData(): BaseReqData<List<CityDataBean>>? {
        netWork?.let {
            return it.loadCityData()
        }
        return null
    }
}