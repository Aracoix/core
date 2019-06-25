package com.ihomefnt.core

/**
 * Created by Aracoix
 * on 2018/6/18
 */
data class BaseResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)