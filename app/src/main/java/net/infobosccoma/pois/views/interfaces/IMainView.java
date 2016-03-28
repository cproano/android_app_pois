package net.infobosccoma.pois.views.interfaces;

import java.util.List;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.models.business.entities.Titular;
import net.infobosccoma.pois.views.impl.adapters.PoisAdapter;

public interface IMainView {

    public void createList(PoisAdapter adapter);

    public PoisAdapter createTitularsAdapter(List<Pois> persons);

    //public void showProgressBar();

    //public void hideProgressBar();

    public void showList();

    //public void hideList();

    //public void updateListOfPois(Pois.Llista pois);

    void goToDetailActivity(Pois pois);

    void moveCamera(Pois poi);
    void updateListOfPois(Pois.Llista result);
}
