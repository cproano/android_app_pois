package net.infobosccoma.titulars.network;


import net.infobosccoma.titulars.models.business.entities.Titular;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;


public class TitularsRetrofitSpiceRequest extends RetrofitSpiceRequest<Titular.Llista, TitularsAPI> {

    public TitularsRetrofitSpiceRequest() {
        super(Titular.Llista.class, TitularsAPI.class);
    }

    @Override
    public Titular.Llista loadDataFromNetwork() {
        return getService().getAll();
    }

}


