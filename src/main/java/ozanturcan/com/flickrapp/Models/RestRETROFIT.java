package ozanturcan.com.flickrapp.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Legend on 26.05.2018.
 */

public interface RestRETROFIT {

    @GET("?method=flickr.photos.getRecent&api_key=004ac4e3901e843d6ba9b61edb62312d&extras=date_upload%2Cowner_name%2Clast_update%2Curl_m%2Cdescription&format=json&nojsoncallback=1")
    Call<PojoFlicker> callFlickerURL();
}
