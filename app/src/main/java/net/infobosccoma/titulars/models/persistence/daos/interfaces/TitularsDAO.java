package net.infobosccoma.titulars.models.persistence.daos.interfaces;

import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;

/**
 * Created by profe on 23/2/2016.
 */
public interface TitularsDAO {

    Titular getById(long id);
    List<Titular> getAll();
    boolean save(Titular titular);
    boolean update(Titular titular);
    boolean delete(Titular titular);
}
