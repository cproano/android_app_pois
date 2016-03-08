package net.infobosccoma.titulars.presenters.interfaces;


import android.content.Context;

import net.infobosccoma.titulars.models.business.entities.Titular;
import net.infobosccoma.titulars.views.impl.activities.BaseActivity;
import net.infobosccoma.titulars.views.interfaces.IMainView;

public interface IMainViewPresenter {

    public void onCreate(IMainView view);

    public void setTitulars(Titular.Llista list);

    public void getTitularsFromService();

    public void showList();

    public void onItemClicked(int position);

    void delete(Titular titular);
}
