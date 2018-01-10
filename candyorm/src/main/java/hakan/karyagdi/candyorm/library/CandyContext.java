package hakan.karyagdi.candyorm.library;

import android.content.Context;

/**
 * Created by hakan on 1/10/18.
 */

public class CandyContext  {
    private static Context context;


    public static Context getContext()
    {
        if(context==null)
        {
            throw new NullPointerException("Appcontext is null.");
        }
        else
        {
            return context;
        }
    }

    public static void init(Context ctx)
    {
        context=ctx;
    }
    public  static void terminate()
    {
        context = null;
    }

}
