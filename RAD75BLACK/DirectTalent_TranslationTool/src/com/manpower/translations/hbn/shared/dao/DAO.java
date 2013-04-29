package com.manpower.translations.hbn.shared.dao;

import java.util.List;
import java.util.Properties;

public interface DAO {

    Object findById(Number id);

    List findAll();

    Object makePersistent(Object obj);

    List findByCustomQuery(String query, Properties params);

    Object runSQLQuery(String query, Properties params, String type);

    Object delete(Object obj);

    Object makeUpdate(Object obj);

    void updateAll(List records);

}
