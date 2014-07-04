package pl.touk.poker.android.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ServerApi {

    @POST("/grooming/{id}/join?name={name}")
//    void joinSession(@Body Request request, @Path("id") String id, Callback<Response> callback);
    void joinSession(@Path("name") String name, @Path("id") String id, Callback<Response> callback);

    @POST("/grooming/{id}/estimate?userId={userId}&estimate={estimate}")
//    void estimate(@Body EstimateRequest request, @Path("id") String id, Callback<EmptyResponse> callback);
    void estimate(@Path("userId") String userId,@Path("estimate") String estimate, @Path("id") String id, Callback<EmptyResponse> callback);

}
