package net.infobosccoma.pois.network;


import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class PoisRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://infobosccoma.net/pmdm/pois/v1/"; //http://gitlab.infobosccoma.net";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(PoisAPI.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}