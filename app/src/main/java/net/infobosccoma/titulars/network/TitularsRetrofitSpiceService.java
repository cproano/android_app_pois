package net.infobosccoma.titulars.network;


import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class TitularsRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://infobosccoma.net/pmdm/titularscloud/v1/"; //http://gitlab.infobosccoma.net";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(TitularsAPI.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}