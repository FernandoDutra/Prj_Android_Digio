package br.com.fernandodutra.prj_android_digio.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/05/2019
 * Time: 00:03
 * Prj_Android_Digio
 */
public class Permissions {

    public static boolean validate(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            if (!checkPermission(activity, permission)) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            return true;
        }

        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        ActivityCompat.requestPermissions(activity, newPermissions, 1);

        return false;
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean ok = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        return ok;
    }

    public static boolean isGpsPermissionOk(Context context) {
        boolean ok1 = checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        boolean ok2 = checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        return ok1 && ok2;
    }
}
