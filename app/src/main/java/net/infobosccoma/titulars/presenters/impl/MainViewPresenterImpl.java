package net.infobosccoma.titulars.presenters.impl;


import android.content.Context;

import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;
import net.infobosccoma.titulars.models.persistence.daos.impl.TitularsRESTDAO;
import net.infobosccoma.titulars.models.persistence.daos.interfaces.TitularsDAO;
import net.infobosccoma.titulars.presenters.interfaces.IMainViewPresenter;
import net.infobosccoma.titulars.views.impl.activities.BaseActivity;
import net.infobosccoma.titulars.views.interfaces.IMainView;

public class MainViewPresenterImpl implements IMainViewPresenter {


    private IMainView view;
    private List<Titular> titulars;
    private TitularsDAO titularsDAO;

    @Override
    public void onCreate(IMainView view) {
        this.view = view;
        titularsDAO = new TitularsRESTDAO((BaseActivity)view);
    }

    @Override
    public void setTitulars(Titular.Llista list) {
        this.titulars = list;
    }


    @Override
    public void getTitularsFromService() {
        view.showProgressBar();
        view.hideList();

        // Aquí, és on demanem les dades
        // Obetnim dades del cloud
        titulars = titularsDAO.getAll();
        //showList();
    }



    @Override
    public void showList() {
        view.hideProgressBar();
        view.createList(view.createTitularsAdapter(titulars));
        view.showList();
    }

    @Override
    public void onItemClicked(int position) {
       // view.goToDetailActivity(persons.get(position));
    }

    @Override
    public void delete(Titular titular) {
        titularsDAO.delete(titular);
    }


}

