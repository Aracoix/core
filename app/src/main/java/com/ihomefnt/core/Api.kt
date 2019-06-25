package com.ihomefnt.core

import com.ihomefnt.commonlib.CommonLibInit
import com.ihomefnt.commonlib.api.BaseRetrofitClient

/**
 *@Descripion 获取service
 *@Auther aracoix
 *@Date 2019-06-1811:42
 **/
object Api {
    val service by lazy { BaseRetrofitClient.getService(CoreService::class.java, CommonLibInit.baseUrl) }
}