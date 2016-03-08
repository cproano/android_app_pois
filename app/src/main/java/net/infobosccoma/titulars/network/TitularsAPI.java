package net.infobosccoma.titulars.network;

import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface TitularsAPI {

    @GET("/titulars")
    Titular.Llista getAll();

    @GET("/titulars/{id}")
    Titular getById(@Path("id") int id);

    @POST("/titulars")
    Response insert(@Path("titol") String titol, @Path("subtitol") String subtitol);

    @PUT("/titulars/{id}")
    Response update(@Path("id") int id, @Path("titol") String titol, @Path("subtitol") String subtitol);

    @DELETE("/titulars/{id}")
    Response delete(@Path("id") int id);


}
