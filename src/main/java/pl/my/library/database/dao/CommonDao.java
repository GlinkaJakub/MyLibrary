package pl.my.library.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.my.library.database.models.BaseModel;
import pl.my.library.utils.FxmlUtils;
import pl.my.library.utils.exceptions.ApplicationExceptios;

import java.sql.SQLException;
import java.util.List;

public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected ConnectionSource connectionSource;

    public CommonDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public CommonDao() {
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws ApplicationExceptios {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.create.update"));
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationExceptios {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.refresh"));
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationExceptios {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.delete"));

        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationExceptios {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.not.found"));
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationExceptios {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.delete"));
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationExceptios {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        }
    }

    public <T extends BaseModel, I> Dao getDao(Class<T> cls) throws ApplicationExceptios {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExceptios(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }
}

