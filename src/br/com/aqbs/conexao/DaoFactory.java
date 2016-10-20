/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.conexao;



import br.com.aqbs.dao.DaoProperties;
import br.com.aqbs.dao.NumeroDaoJDBC;
import br.com.aqbs.exception.DaoConfigurationException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author aqbs
 */
public abstract class DaoFactory {

    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    public static DaoFactory getInstance(String name) throws DaoConfigurationException {
        if (name == null) {
            throw new DaoConfigurationException("Banco de Dados inexistente.");
        }

        DaoProperties properties = new DaoProperties(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
        String password = properties.getProperty(PROPERTY_PASSWORD, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);
        
        DaoFactory instance;

        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new DaoConfigurationException(
                        "Driver'" + driverClassName + "' está faltando no classpath.", e);
            }
            instance = new DriverManagerDAOFactory(url, username, password);
        } else {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new DaoConfigurationException(
                        "DataSource '" + url + "' está faltando no JNDI.", e);
            }
            if (username != null) {
                instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
            } else {
                instance = new DataSourceDAOFactory(dataSource);
            }
        }

        return instance;
    }

    public abstract Connection getConnection() throws SQLException;

    
 
    
    public NumeroDaoJDBC getNumeroDAO() {
        return new NumeroDaoJDBC(this);
    }


}
