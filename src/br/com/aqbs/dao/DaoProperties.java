/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.dao;

import br.com.aqbs.exception.DaoConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author aqbs
 */
public class DaoProperties {

    private static final String PROPERTIES_FILE = "br/com/aqbs/resources/dao.properties";
    private static final Properties PROPERTIES = new Properties();
    private String specificKey;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DaoConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' não encontrado.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DaoConfigurationException(
                    "Não foi possivel carregar o properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public DaoProperties(String specificKey) throws DaoConfigurationException {
        this.specificKey = specificKey;
    }

    public String getProperty(String key, boolean mandatory) throws DaoConfigurationException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DaoConfigurationException("Propriedade '" + fullKey + "'"
                        + " está faltando no properties file '" + PROPERTIES_FILE + "'.");
            } else {
                property = null;
            }
        }

        return property;
    }
}
