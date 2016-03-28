package net.infobosccoma.pois.network;


import net.infobosccoma.pois.models.business.entities.Pois;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;


public class PoisRetrofitSpiceRequest extends RetrofitSpiceRequest<Pois.Llista, PoisAPI> {

    public PoisRetrofitSpiceRequest() {
        super(Pois.Llista.class, PoisAPI.class);
    }

    @Override
    public Pois.Llista loadDataFromNetwork() {
        return getService().getAll();
    }

}


