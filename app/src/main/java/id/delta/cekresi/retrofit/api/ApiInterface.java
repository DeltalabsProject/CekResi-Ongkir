package id.delta.cekresi.retrofit.api;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import id.delta.cekresi.retrofit.models.ongkir.OngkirResponse;
import id.delta.cekresi.retrofit.models.resi.JneResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 8/19/16.
 */
public interface ApiInterface {

    String BASE_URL = "http://ibacor.com/api/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    //JNE
    @GET("cek-resi")
    Call<JneResponse> getJNEResponse(@Query("pengirim") String pengirim, @Query("resi") String resi);

    //Ongkir http://ibacor.com/api/ongkir?service=tiki&dari=bandung&ke=garut&berat=5
    @GET("ongkir")
    Call<OngkirResponse>getOngkir(@Query("service") String service, @Query("dari") String dari, @Query("ke")String ke, @Query("berat")String berat);
}
