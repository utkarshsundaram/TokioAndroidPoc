package tokiopoc.tokio.tokioandroidpoc.application;

import android.support.multidex.MultiDexApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import maxlife.maxlifepoc.network.ApiClientInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maxlife.maxlifepoc.network.NetworkConstant.NETWORK_TIMEOUT;

/**
 * BaseApplication class of the app for firebase storage and initialising the
 * retrofit
 *
 * @author Utkarsh Sundaram
 */
public class TokioApplication extends MultiDexApplication {
    private final String TAG = TokioApplication.class.getSimpleName();

    public static String BASE_URL = "http://ec2-52-66-198-58.ap-south-1.compute.amazonaws.com";


    private static Retrofit mRetrofitClient;

    public static ApiClientInterface getClient() {
        return mRetrofitClient.create(ApiClientInterface.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setRetrofitApiClient();
       /* registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() {
            @Override
            public void onActivityCreated(Activity a, Bundle savedInstanceState) {
                if (!AppUtils.isTablet(a)) {
                    a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });*/
    }

    private void setRetrofitApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
