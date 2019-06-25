package com.ihomefnt.commonlib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ihomefnt.commonlib.ex.log


abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(initLayoutId(), null)
    }


    /**
     * @return 返回当前页面布局id
     */
    abstract fun initLayoutId(): Int

    /**
     * 初始化ui控件监听事件
     */
    abstract fun initListener()

    /**
     * 初始化页面数据
     */
    abstract fun initData()


}
