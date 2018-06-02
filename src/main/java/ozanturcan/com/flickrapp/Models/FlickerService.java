package ozanturcan.com.flickrapp.Models;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import okhttp3.OkHttpClient;
import ozanturcan.com.flickrapp.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Legend on 26.05.2018.
 */

public class FlickerService {
    public static PojoFlicker pjFlickerList;
    static RestRETROFIT restRETROFIT;
    public static Photos photos;
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

    public static void getFlickerServices(Context context) {
        ////RetroFit Log Screen Star
        restRETROFIT = FlickerService.getRetrofitBase().create(RestRETROFIT.class);
        Call<PojoFlicker> call = restRETROFIT.callFlickerURL();
        call.enqueue(new Callback<PojoFlicker>() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PojoFlicker> call, Response<PojoFlicker> response) {
                if(response.body().stat != "fail"){
                    pjFlickerList = new PojoFlicker();
                    pjFlickerList = response.body();
                    photos = pjFlickerList.photos;
                    ((MainActivity)context).fillPhotos(photos);
                }

            }

            @Override
            public void onFailure(Call<PojoFlicker> call, Throwable t) {

            }
        });
    }
}
