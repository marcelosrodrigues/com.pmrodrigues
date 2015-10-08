package test.com.pmrodrigues.sisgns.acceptables;

import com.pmodrigues.pageobjects.factory.WebDriverFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Marceloo on 07/10/2015.
 */
public abstract class AbstractAcceptableTests {

    private static WebDriver webdriver = WebDriverFactory.createWebDriver();

    private final ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

    private Connection connection = null;

    @AfterClass
    public static void finaliza() {
        webdriver.close();
        webdriver.quit();
    }

    protected static WebDriver getDriver() {
        return webdriver;
    }

    private Connection getConnection() {

        try {
            if (this.connection == null || this.connection.isClosed()) {
                Class.forName(bundle.getString("jdbc.driverClassName"));
                this.connection = DriverManager.getConnection(bundle.getString("jdbc.url"),
                        bundle.getString("jdbc.username"),
                        bundle.getString("jdbc.password"));

                connection.setAutoCommit(false);
            }

            return this.connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    protected void update(final String sql, Object... params) {

        try {
            new QueryRunner().update(this.getConnection(), sql, params);
        } catch (SQLException e) {
            DbUtils.rollbackAndCloseQuietly(this.getConnection());
            throw new RuntimeException(e);
        }

    }

    protected <T> T queryForObject(final String sql, Class<T> t, Object... params) {
        try {
            return new QueryRunner().query(this.getConnection(), sql, new BeanHandler<>(t), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(this.getConnection());
        }
    }

    protected <T> List<T> query(final String sql, Class<T> t, Object... params) {
        try {
            return new QueryRunner().query(this.getConnection(), sql, new BeanListHandler<>(t), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(this.getConnection());
        }
    }

    protected void commit() {
        DbUtils.commitAndCloseQuietly(this.getConnection());
    }

    public <T> T queryForScalar(final String sql, Object... params) {
        try {
            return new QueryRunner().query(this.getConnection(), sql, new ScalarHandler<T>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(this.getConnection());
        }
    }
}
