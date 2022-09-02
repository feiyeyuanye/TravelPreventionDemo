package com.example.appbase.network

/**
 * FileName: BaseReqData
 * Founder: Jiang Houren
 * Create Date: 2022/8/23 12:08
 * Profile: 基础数据返回格式
 * 每个接口返回的数据结构相同的地方，
 * 仅有 result 字段返回的数据结构不同，
 * BaseReqData 泛型类
 */
class BaseReqData<T> {
    val result: T? = null
    val reason: String = ""
    val error_code: Int = 0
}
