/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.dao;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.exception.DaoException;
import br.com.aqbs.model.Numero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static br.com.aqbs.dao.DaoUtil.prepareStatement;
import br.com.aqbs.model.Dealer;

/**
 *
 * @author aqbs
 */
public class DealerDaoJDBC implements IDaoDealer {

    private static final String SQL_ALL
            = "SELECT * FROM estartho_test.Dealer";
     private static final String SQL_INSERT
            = "INSERT INTO `Dealer`(`nome`) VALUES (?)";
     private static final String SQL_SP_LIST
            = "call {sp_lista(?)";
   
     private DaoFactory daoFactory;

    public DealerDaoJDBC(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Numero find(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Dealer l) throws IllegalArgumentException, DaoException {
        Object[] values = {
            l.getNome()

        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating user failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Dealer l) throws IllegalArgumentException, DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dealer> list() throws DaoException {
        List<Dealer> Numero = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_ALL);
                ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Numero.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Numero;
    }

    private static Dealer map(ResultSet resultSet) throws SQLException {
        Dealer Numero = new Dealer();
        Numero.setId(resultSet.getInt("idDealer"));
        Numero.setNome(resultSet.getString("nome"));
       

        return Numero;
    }


}
