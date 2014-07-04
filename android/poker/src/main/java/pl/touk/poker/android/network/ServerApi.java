package pl.touk.poker.android.network;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ServerApi {

    @POST("/grooming/{id}/join")
//    void joinSession(@Body Request request, @Path("id") String id, Callback<Response> callback);
    void joinSession(@Query("name") String name, @Path("id") String id, Callback<Response> callback);

    @POST("/grooming/{id}/estimate")
//    void estimate(@Body EstimateRequest request, @Path("id") String id, Callback<EmptyResponse> callback);
    void estimate(@Query("userId") String userId,@Query("estimate") String estimate, @Path("id") String id, Callback<EmptyResponse> callback);

}
