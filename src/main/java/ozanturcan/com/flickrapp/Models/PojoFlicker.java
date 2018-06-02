package ozanturcan.com.flickrapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Legend on 26.05.2018.
 */

public class PojoFlicker {
        @SerializedName("photos")
        @Expose
        public Photos photos;
        @SerializedName("stat")
        @Expose
        public String stat;
}
