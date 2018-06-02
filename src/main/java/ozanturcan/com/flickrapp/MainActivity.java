package ozanturcan.com.flickrapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ozanturcan.com.flickrapp.Models.FlickerService;
import ozanturcan.com.flickrapp.Models.GetFlickerServices;
import ozanturcan.com.flickrapp.Models.Photo;
import ozanturcan.com.flickrapp.Models.Photos;
import ozanturcan.com.flickrapp.Models.PojoFlicker;
import ozanturcan.com.flickrapp.Models.RestRETROFIT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    Photos lstPhoto;
    public  PojoFlicker pjFlickerList;
    static RestRETROFIT restRETROFIT;
    public  Photos photos;
    final String TAG = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        //region swipeContainer
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        fetchTimelineAsync();

                        swipeContainer.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //endregion
        //region EndlessRecyclerOnScrollListener
        EndlessRecyclerOnScrollListener myListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                 recyclerViewAdapter.notifyDataSetChanged();
            }
        };
        recyclerView.addOnScrollListener(myListener);
        //endregion
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        GetFlickerServices.getFlickerServices(this);
        lstPhoto = new Photos();

    }
    public void fetchTimelineAsync() {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        GetFlickerServices.getFlickerServices(this);

        recyclerViewAdapter.notifyDataSetChanged();
    }
    public void fillPhotos(Photos lstPhoto){

        recyclerViewAdapter = new RecyclerViewAdapter(this,lstPhoto.getPhoto());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
