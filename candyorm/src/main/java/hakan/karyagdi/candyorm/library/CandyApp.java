package hakan.karyagdi.candyorm.library;

import android.app.Application;
import android.content.Context;

/**
 * Created by hakan on 12/4/17.
 */

public class CandyApp extends Application {


    public void onCreate() {
        super.onCreate();
        CandyContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        CandyContext.terminate();
    }
}
