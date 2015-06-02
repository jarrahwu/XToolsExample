package com.stkj.xoolsdebug;

import com.stkj.xtools.XTool;

/**
 * Created by jarrah on 2015/5/29.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        XTool.init(getApplicationContext());
    }
}
