package ozanturcan.com.flickrapp.Models;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ozanturcan.com.flickrapp.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Legend on 27.05.2018.
 */

public class GetFlickerServices {
    public static PojoFlicker pjFlickerList;
    static RestRETROFIT restRETROFIT;
    public static Photos photos;


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
