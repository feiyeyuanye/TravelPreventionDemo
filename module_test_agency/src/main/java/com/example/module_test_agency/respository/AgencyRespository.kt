package com.example.module_test_agency.respository

import android.util.Log
import com.example.appbase.network.BaseReqData
import com.example.appbase.network.RetrofitServiceBuilder
import com.example.module_test_agency.api.AgencyApi
import com.example.module_test_agency.bean.AgencyDataBean

/**
 * FileName: AgencyRespository
 * Founder: Jiang Houren
 * Create Date: 2022/8/24 10:12
 * Profile: 数据仓库层
 */
class AgencyRespository {
    // 创建 service 实例
    // 这里所有的仓库层都是通过 RetrofitServiceBuilder 方法创建了一个 service 实体类，其实这是不符合类的单一职责原则的，
    // 可改为依赖注入方式去创建 service，这样便可以将创建 service 的工作从仓库层分离出来了。
    private val netWork = RetrofitServiceBuilder.createService(AgencyApi::class.java)

    /**
     * 查询检测机构信息
     * @param cityId 城市id
     */
    suspend fun loadTestAgencyMessage(cityId: String):BaseReqData<AgencyDataBean>?{
        netWork?.let {
            return it.loadTestAgencyMessage(cityId)
        }
        return null
    }

}