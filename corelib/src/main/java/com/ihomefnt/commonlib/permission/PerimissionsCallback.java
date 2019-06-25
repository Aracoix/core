package com.ihomefnt.commonlib.permission;

import java.util.ArrayList;

/**
 * 当前类注释：权限请求回调
 * <p>
 * Author :Aracoix
 * <p>
 * Created  2017/3/9.16:24
 * <p>
 */

public interface PerimissionsCallback {

    void onGranted(ArrayList<PermissionEnum> grantedList);

    void onDenied(ArrayList<PermissionEnum> deniedList);
}
