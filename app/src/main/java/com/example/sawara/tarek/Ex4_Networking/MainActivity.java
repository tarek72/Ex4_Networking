package com.example.sawara.tarek.Ex4_Networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;



public class MainActivity extends AppCompatActivity {

    public interface ImgurService {

        @Headers("Authorization: Client-ID " + CLIENT_ID)
        @GET("/3/album/{albumHash}")

        Call<ImagesResponse> getAlbumImages(@Path("albumHash") String albumHash);
    }


    public static final String BASE_URL = "https://api.imgur.com";
    public static final String CLIENT_ID = "31a7768bec71c9d";
    public static final String MY_ALBUM_ID = "eCZeqD8";




    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;

    private Button mShowButton;

    private ArrayList<ImageViewModel>  imageList ;//= {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};


    static Retrofit.Builder retrofitBuilder;
    static OkHttpClient.Builder httpClient;
    static Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = new ArrayList<>();
        initComponents();
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitBuilder =
                        new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create());
                httpClient =
                        new OkHttpClient.Builder();
                retrofit =
                        retrofitBuilder
                                .client(httpClient.build())
                                .build();
                ImgurService imgurService = retrofit.create(ImgurService.class);

                Call<ImagesResponse> call = imgurService.getAlbumImages(MY_ALBUM_ID);
                call.enqueue(new Callback<ImagesResponse>() {


                    @Override
                    public void onResponse(Call<ImagesResponse> call,
                                           Response<ImagesResponse> response) {


                        ImagesResponse data = response.body();
                        if (data != null) {
                            for (ImagesResponse.Image image: data.data.images) {
                                imageList.add(new ImageViewModel(image.id,image.link));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }



                    @Override
                    public void onFailure(Call<ImagesResponse> call, Throwable t) {

                    }
                });
            }
        });


    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(imageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initComponents() {
        mShowButton = findViewById(R.id.ButtonShowAlbum);
        initRecyclerView();
    }
}