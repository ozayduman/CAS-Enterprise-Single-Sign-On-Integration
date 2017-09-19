package com.ozayduman.casclient.repository;

import java.util.Map;

/**
 * Created by ozayd on 19.09.2017.
 */
public interface CitizenRepository {
    void saveCitizen(Citizen citizen);

    void updateCitizen(Citizen citizen);

    Citizen findCitizen(String id);

    Map<Object, Object> findAllCitizens();

    void deleteCitizen(String id);
}
