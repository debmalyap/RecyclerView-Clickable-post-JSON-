package in.qbent.com.qbentrecclick;

import java.net.CookieHandler;
import java.net.CookieManager;

import okhttp3.CookieJar.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class RetrofitClient
{
    public static final String my_url = "https://api.myjson.com/bins/1bah94";

    private static Retrofit retrofit = null;

    private static boolean checkClient()
    {
        if(retrofit!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Retrofit getRetrofit()
    {
        return retrofit;
    }

    public static Retrofit getClient()
    {
        if(checkClient())
        {
            return getRetrofit();
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager();

    }
}
