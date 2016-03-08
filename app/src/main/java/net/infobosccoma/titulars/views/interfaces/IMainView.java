package net.infobosccoma.titulars.views.interfaces;

import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;
import net.infobosccoma.titulars.views.impl.adapters.TitularsAdapter;

public interface IMainView {

    public void createList(TitularsAdapter adapter);

    public TitularsAdapter createTitularsAdapter(List<Titular> persons);

    public void showProgressBar();

    public void hideProgressBar();

    public void showList();

    public void hideList();


}
