package net.infobosccoma.pois.models.persistence.daos.impl;

import android.content.Context;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.models.persistence.daos.interfaces.PoisDAO;
import net.infobosccoma.pois.network.PoisAPI;
import net.infobosccoma.pois.views.impl.activities.BaseActivity;
import net.infobosccoma.poiscloud.R;


/**
 * Created by marc on 23/2/2016.
 */
public class PoisRESTDAO implements PoisDAO {

    private Context context;

    public PoisRESTDAO(Context context) {
        this.context = context;
    }


    @Override
    public Pois getById(long id) {
        Pois pois = null;


        return pois;
    }

    /**
     * Obtenir tots els titulars.
     * Es fa una petició GET al servei REST
     * @return
     */
    @Override
    public List<Pois> getAll() {
        List<Pois> pois = new ArrayList();

        // create request object
        RetrofitSpiceRequest<Pois.Llista, PoisAPI> request = new RetrofitSpiceRequest<Pois.Llista, PoisAPI>(Pois.Llista.class, PoisAPI.class) {
            @Override
            public Pois.Llista loadDataFromNetwork() throws Exception {
                return getService().getAll();
            }
        };
        ((BaseActivity) context).getSpiceManager().execute(request, context.getString(R.string.cache_titulars), 100, (RequestListener<Pois.Llista>) ((BaseActivity) context).getListListener());

        return pois;
    }

    @Override
    public boolean save(final Pois titular) {
        // create request object
        //No inserim farem cap inserció a la BBDD del WebService per tant, no ens cal implementar el mètode

        return false;

    }

    @Override
    public boolean update(final Pois pois) {
        // create request object

        //No utilitzarem aquest mètode per tant no cal implementar-lo
        return false;
    }

    @Override
    public boolean delete(final Pois pois) {
        // create request object

        //No utilitzarem aquest mètode per tant no cal implementar-lo
        return  false;
    }

    @Override
    public List<Pois> getByStringReference(final String text) {

        List<Pois> pois = new ArrayList();
        // create request object
        RetrofitSpiceRequest<Pois.Llista, PoisAPI> request = new RetrofitSpiceRequest<Pois.Llista, PoisAPI>(Pois.Llista.class, PoisAPI.class) {
            @Override
            public Pois.Llista loadDataFromNetwork() throws Exception {
                return getService().getByString(text);
            }
        };
        ((BaseActivity) context).getSpiceManager().execute(request, context.getString(R.string.cache_titulars), 100, (RequestListener<Pois.Llista>) ((BaseActivity) context).getListListener());

        return pois;

    }
}
