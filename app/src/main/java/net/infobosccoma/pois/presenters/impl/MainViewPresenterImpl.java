package net.infobosccoma.pois.presenters.impl;


import android.view.View;
import android.widget.Toast;

import java.util.List;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.models.persistence.daos.impl.PoisRESTDAO;
import net.infobosccoma.pois.models.persistence.daos.interfaces.PoisDAO;
import net.infobosccoma.pois.presenters.interfaces.IMainViewPresenter;
import net.infobosccoma.pois.views.impl.activities.BaseActivity;
import net.infobosccoma.pois.views.interfaces.IMainView;
import net.infobosccoma.poiscloud.R;

public class MainViewPresenterImpl implements IMainViewPresenter {


    private IMainView view;
    private List<Pois> pois;
    private PoisDAO poisDAO;

    @Override
    public void onCreate(IMainView view) {
        this.view = view;
        poisDAO = new PoisRESTDAO((BaseActivity)view);
    }

    @Override
    public void setPois(Pois.Llista list) {

        this.pois = list;

    }

    @Override
    public void getAllPoisFromService() {
    /*    view.showProgressBar();
        view.hideList();*/
        // Aquí, és on demanem les dades
        // Obetnim dades del cloud
        pois = poisDAO.getAll();
        //showList();
    }

    @Override
    public void getPoisFromServiceByText(String text) {
/*
        view.showProgressBar();
        view.hideList();*/
        pois = poisDAO.getByStringReference(text);
    }


    @Override
    public void showList() {
        //view.hideProgressBar();
        view.createList(view.createTitularsAdapter(pois));
        view.showList();
    }

    @Override
    public void onItemClicked(int position) {
       // view.goToDetailActivity(persons.get(position));
        view.moveCamera(pois.get(position));
    }

    @Override
    public void delete(Pois pois) {

    }

    @Override
    public List<Pois> getList() {
        return pois;
    }

    @Override
    public void updatePoisOfMain(Pois.Llista result) {
        this.pois = result;
        view.updateListOfPois((Pois.Llista) pois);
    }


}

