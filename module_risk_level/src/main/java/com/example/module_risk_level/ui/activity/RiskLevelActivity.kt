package com.example.module_risk_level.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.appbase.config.ARouteConfig.RISK_LEVEL
import com.example.appbase.config.BaseApplication
import com.example.appbase.ui.BaseActivity
import com.example.appbase.utils.DialogLoadingUtils
import com.example.module_risk_level.bean.RiskLevelDetailBean
import com.example.module_risk_level.bean.enum.DataTypeEnum
import com.example.module_risk_level.databinding.ActivityRiskLevelBinding
import com.example.module_risk_level.ui.adapter.RiskLevelMessageAdapter
import com.example.module_risk_level.viewmodel.RiskLevelViewModel

/**
 * 风险等级查询Activity
 */
@Route(path = RISK_LEVEL)
class RiskLevelActivity : BaseActivity<ActivityRiskLevelBinding>() {
    /**
     * viewModel
     */
    private val riskLevelViewModel by viewModels<RiskLevelViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    /**
     * 初始化
     */
    private fun init() {
        mViewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
        loadData()
    }
    /**
     * 查询数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, "请稍后")
        riskLevelViewModel.loadRiskLevelMessage().observe(this, Observer {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    // 手动创建并添加中、高风险地区标题信息，并将中、高风险地区信息放置在一个集合中。
                    mViewBinding.bean = it.result
                    val list = mutableListOf<RiskLevelDetailBean>()
                    val highTitle = RiskLevelDetailBean()
                    highTitle.dataType = DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal
                    RiskLevelDetailBean()
                    list.add(highTitle)
                    it.result?.high_list?.let {
                        list.addAll(it)
                    }
                    val middleTitle = RiskLevelDetailBean()
                    middleTitle.dataType = DataTypeEnum.DATA_IS_MIDDLE_TITLE.ordinal
                    list.add(middleTitle)
                    it.result?.middle_list?.let { middle ->
                        list.addAll(middle)
                    }
                    val riskLevelAdapter = RiskLevelMessageAdapter(list)
                    mViewBinding.rvData.adapter = riskLevelAdapter
                }else if (it.error_code == 10012){
                    // {"resultcode":"112","reason":"超过每日可允许请求次数","result":null,"error_code":10012}
                    Toast.makeText(
                        BaseApplication.mContext,
                        BaseApplication.mContext.getString(com.example.appbase.R.string.exceed_allow_number), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    override fun getViewBinding(): ActivityRiskLevelBinding {
        return ActivityRiskLevelBinding.inflate(layoutInflater)
    }
}
