package net.infobosccoma.pois.presenters.interfaces;


import android.view.View;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.models.business.entities.Titular;
import net.infobosccoma.pois.views.interfaces.IMainView;

import java.util.List;

public interface IMainViewPresenter {

    public void onCreate(IMainView view);

    public void setPois(Pois.Llista list);

    public void getAllPoisFromService();

    public void getPoisFromServiceByText(String text);

    public void showList();

    public void onItemClicked(int position);

    void delete(Pois titular);


    List<Pois> getList();

    void updatePoisOfMain(Pois.Llista result);
}
