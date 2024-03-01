package de.home_skrobanek.manhunt.backend.manager.http;


import de.home_skrobanek.manhunt.backend.manager.http.dao.Message;
import de.home_skrobanek.manhunt.backend.manager.http.dao.Player;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("player/authentication")
    Call<Message> loginUser(@Body Player player);
}
