package net.infobosccoma.titulars.models.persistence.daos.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;
import net.infobosccoma.titulars.models.persistence.daos.interfaces.TitularsDAO;
import net.infobosccoma.titulars.network.Response;
import net.infobosccoma.titulars.network.TitularsAPI;
import net.infobosccoma.titulars.network.TitularsRetrofitSpiceRequest;
import net.infobosccoma.titulars.views.impl.activities.BaseActivity;
import net.infobosccoma.titulars.views.impl.activities.MainActivity;
import net.infobosccoma.titularscloud.R;


/**
 * Created by marc on 23/2/2016.
 */
public class TitularsRESTDAO implements TitularsDAO {

    private Context context;

    public TitularsRESTDAO(Context context) {
        this.context = context;
    }


    @Override
    public Titular getById(long id) {
        Titular person = null;


        return person;
    }

    /**
     * Obtenir tots els titulars.
     * Es fa una petició GET al servei REST
     * @return
     */
    @Override
    public List<Titular> getAll() {
        List<Titular> titulars = new ArrayList();

        // create request object
        RetrofitSpiceRequest<Titular.Llista, TitularsAPI> request = new RetrofitSpiceRequest<Titular.Llista, TitularsAPI>(Titular.Llista.class, TitularsAPI.class) {
            @Override
            public Titular.Llista loadDataFromNetwork() throws Exception {
                return getService().getAll();
            }
        };
        ((BaseActivity) context).getSpiceManager().execute(request, context.getString(R.string.cache_titulars), DurationInMillis.ONE_MINUTE, (RequestListener<Titular.Llista>) ((BaseActivity) context).getListListener());

        return titulars;
    }

    @Override
    public boolean save(final Titular titular) {
        // create request object
        RetrofitSpiceRequest<Response, TitularsAPI> request = new RetrofitSpiceRequest<Response, TitularsAPI>(Response.class, TitularsAPI.class) {
            @Override
            public Response loadDataFromNetwork() throws Exception {
                return  getService().insert(titular.getTitol(), titular.getSubtitol());
            }
        };

        ((BaseActivity) context).getSpiceManager().execute(request, (RequestListener<Response>) ((BaseActivity) context).getUpdateListener());
        return true;

    }

    @Override
    public boolean update(final Titular titular) {
        // create request object
        RetrofitSpiceRequest<Response, TitularsAPI> request = new RetrofitSpiceRequest<Response, TitularsAPI>(Response.class, TitularsAPI.class) {
            @Override
            public Response loadDataFromNetwork() throws Exception {
                return  getService().update(titular.get_id(), titular.getTitol(), titular.getSubtitol());
            }
        };

        ((BaseActivity) context).getSpiceManager().execute(request, (RequestListener<Response>) ((BaseActivity) context).getUpdateListener());
        return true;
    }

    @Override
    public boolean delete(final Titular titular) {
        // create request object
        RetrofitSpiceRequest<Response, TitularsAPI> request = new RetrofitSpiceRequest<Response, TitularsAPI>(Response.class, TitularsAPI.class) {
            @Override
            public Response loadDataFromNetwork() throws Exception {
                return  getService().delete(titular.get_id());
            }
        };

        ((BaseActivity) context).getSpiceManager().execute(request, (RequestListener<Response>) ((BaseActivity) context).getUpdateListener());
        return true;
    }
}
