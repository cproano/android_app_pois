package net.infobosccoma.pois.network;

import net.infobosccoma.pois.models.business.entities.Pois;

import retrofit.http.GET;
import retrofit.http.Path;

public interface PoisAPI {

    @GET("/pois")
    Pois.Llista getAll();

    @GET("/pois/{cityName}")
    Pois.Llista getByString(@Path("cityName") String cityName);

}
