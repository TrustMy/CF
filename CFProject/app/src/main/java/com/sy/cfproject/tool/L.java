package com.sy.cfproject.tool;

import android.util.Log;



/**
 * Created by Trust on 2017/3/2.
 */
public class L {
    private static boolean isShow = true;
    private static String TAG = "lhh_Debug";
    public static void  i (String msg)
    {
        if(isShow)
        {
            Log.i(TAG, msg);
        }
    }

    public static  void e(String msg)
    {
        if(isShow)
        {
            Log.e(TAG, msg );
        }
    }
}
