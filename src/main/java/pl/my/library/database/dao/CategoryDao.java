package pl.my.library.database.dao;

import com.j256.ormlite.support.ConnectionSource;

public class CategoryDao extends CommonDao {
    public CategoryDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public CategoryDao() {
        super();
    }
}
