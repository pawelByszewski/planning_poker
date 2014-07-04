package pl.touk.poker.android.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ServerApi {

    @POST("/grooming/{id}/join")
    void agentActivate(@Body Request request, @Path("id") String lastNotificationDate, Callback<Response> callback);

    @DELETE("/grooming/{id}/join")
    void agentActivate(@Body Request request, @Path("id") String lastNotificationDate, Callback<Response> callback);
    /grooming/{id}/join?name={name}
}
