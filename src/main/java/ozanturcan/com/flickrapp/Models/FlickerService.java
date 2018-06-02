package ozanturcan.com.flickrapp.Models;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Legend on 26.05.2018.
 */

public class FlickerService {

    private static Retrofit retrofit = null;
    private static String base_URL = "https://api.flickr.com/services/rest/";
    public static Retrofit getRetrofitBase(){
        if (retrofit == null)
        {
             retrofit = new Retrofit.Builder()
                     .baseUrl(base_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(new OkHttpClient())
                     .build();
            return retrofit;

        }
        return retrofit;
    }
}
