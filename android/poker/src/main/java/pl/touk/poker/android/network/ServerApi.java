package pl.touk.poker.android.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ServerApi {

    @POST("/grooming/{id}/join")
    void joinSession(@Body Request request, @Path("id") String id, Callback<Response> callback);

}
