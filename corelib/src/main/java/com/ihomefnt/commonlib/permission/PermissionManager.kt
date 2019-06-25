package com.ihomefnt.commonlib.permission

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ihomefnt.commonlib.utils.LogUtils
import java.util.*

/**
 * 权限管理
 */
class PermissionManager private constructor() {

    private var mPerimissionsCallback: PerimissionsCallback? = null
    private val mPermissions: ArrayList<PermissionEnum> = arrayListOf()
    private val mPermissionsGranted: ArrayList<PermissionEnum> = arrayListOf()
    private val mPermissionsDenied: ArrayList<PermissionEnum> = arrayListOf()
    private var mTag = 100

    private fun permissionDenied(context: Context,permissionsDenied: ArrayList<PermissionEnum>) {
        val msgCN = StringBuilder()
        for (i in permissionsDenied.indices) {
            if (i == permissionsDenied.size - 1) {
                msgCN.append(permissionsDenied[i].name_cn)
            } else {
                msgCN.append(permissionsDenied[i].name_cn).append(",")
            }
        }


       AlertDialog.Builder(context)
                .setMessage(String.format("你已拒绝访问%s权限,请前往设置中心打开权限", msgCN.toString()))
                .setCancelable(false)
                .setPositiveButton("设置") { _, _ -> PerUtils().openApplicationSettings(context) }
                .create().show()
    }

    /**
     * 权限请求的返回状态区分

     * @param tag
     * *
     * @return
     */
   private fun tag(tag: Int): PermissionManager {
        this.mTag = tag
        return this
    }

    private fun checkAsk(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionToAsk = permissionToAsk(context)
            if (permissionToAsk.isEmpty()) {
                showResult(context)
            } else {
                ActivityCompat.requestPermissions((context as Activity), permissionToAsk, mTag)
            }
        } else {
            mPermissionsGranted?.addAll(mPermissions!!)
            showResult(context)
        }
    }


    /**
     * @param permissions an array of permission that you need to ask
     * *
     * @return current instance
     */
   private fun permissions(permissions: ArrayList<PermissionEnum>): PermissionManager {
        this.mPermissions.addAll(permissions)
        return this
    }




    /**
     * @param permissions permission you need to ask
     * *
     * @return current instance
     */
   private fun permission(vararg permissions: PermissionEnum): PermissionManager {
        Collections.addAll(this.mPermissions, *permissions)
        return this
    }

    private fun callback(callback: PerimissionsCallback): PermissionManager {
        this.mPerimissionsCallback = callback
        return this
    }

    /**
     * 检查是否拥有权限
     * @return permission that you realy need to ask
     */
    private fun permissionToAsk(context: Context): Array<String> {
        val permissionToAsk = ArrayList<String>()
        for (permission in mPermissions!!) {
            if (!isGranted(permission,context)) {
                permissionToAsk.add(permission.permisson)
            } else {
                mPermissionsGranted?.add(permission)
            }
        }
        return permissionToAsk.toTypedArray()
    }

    private fun isGranted(permission: PermissionEnum,context: Context): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(context, permission.permisson) == PackageManager.PERMISSION_GRANTED


    private fun showResult(context: Context) {
        if (mPerimissionsCallback != null) {
            if (mPermissionsDenied.size > 0) {
                permissionDenied( context,mPermissionsDenied)
                mPerimissionsCallback?.onDenied(mPermissionsDenied)
                mPermissionsDenied.clear()
            } else {
                mPerimissionsCallback?.onGranted(mPermissionsGranted)
                mPermissionsDenied.clear()
            }
        }
    }


    /**
     * 对请求权限的响应遍历
     *
     * @param value
     * @return
     */
   private fun onResultPermissions(value: String): PermissionEnum {
        for (permissionEnum in PermissionEnum.values()) {
            if (value.equals(permissionEnum.permisson, ignoreCase = true)) {
                return permissionEnum
            }
        }
        return PermissionEnum.READ_EXTERNAL_STORAGE
    }
    fun checkPerMission(activity: Context?, success: ()->Unit,  vararg permissions: PermissionEnum) {
        checkPerMission(activity=activity,success = success,fail = {},permissions = *permissions)
    }
    fun checkPerMission(activity: Context?, success: ()->Unit, fail:()->Unit={}, vararg permissions: PermissionEnum) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity != null) {
                manager
                    .tag(100)
                    .permission(*permissions)
                    .callback(object : PerimissionsCallback {
                        override fun onGranted(grantedList: ArrayList<PermissionEnum>) {
                            success()
                        }
                        override fun onDenied(deniedList: ArrayList<PermissionEnum>) {
                            LogUtils.e(deniedList.toString())
                            fail.invoke()
                        }
                    }).checkAsk(context = activity)
            }


        }else{
            success()
        }
    }
    fun handleResult(context: Context,requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == manager.mTag) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    manager.mPermissionsGranted.add(onResultPermissions(permissions[i]))
                } else {
                    manager.mPermissionsDenied.add(onResultPermissions(permissions[i]))
                }
            }
            manager.showResult(context)
        }

    }
    companion object {
         val manager: PermissionManager by lazy {  PermissionManager() }
    }


}
