package net.infobosccoma.pois.models.persistence.daos.interfaces;

import java.util.List;

import net.infobosccoma.pois.models.business.entities.Pois;

/**
 * Created by profe on 23/2/2016.
 */
public interface PoisDAO {

    Pois getById(long id);
    List<Pois> getAll();
    boolean save(Pois pois);
    boolean update(Pois pois);
    boolean delete(Pois pois);

    List<Pois> getByStringReference(String text);
}
