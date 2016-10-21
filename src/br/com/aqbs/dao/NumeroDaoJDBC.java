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

/**
 *
 * @author aqbs
 */
public class NumeroDaoJDBC implements IDaoGeneric {

    private static final String SQL_ALL
            = "SELECT * FROM testeCerto.Numero";
     private static final String SQL_INSERT
            = "INSERT INTO `Numero`(`valor`, `cor`, `data`,`turno`, `dealer`) VALUES (?, ?, ?, ?, ?)";
     private static final String SQL_SP_LIST
            = "call {sp_lista(?)";
   
     private DaoFactory daoFactory;

    public NumeroDaoJDBC(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Numero find(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Numero l) throws IllegalArgumentException, DaoException {
        Object[] values = {
            l.getValor(),
            l.getCor(),
            l.getData(),
            l.getTurno(),
            l.getDealer()

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
    public void update(Numero l) throws IllegalArgumentException, DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Numero> list() throws DaoException {
        List<Numero> Numero = new ArrayList<>();

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

    private static Numero map(ResultSet resultSet) throws SQLException {
        Numero Numero = new Numero();
        Numero.setId(resultSet.getInt("idNumero"));
        Numero.setValor(resultSet.getString("valor"));
        Numero.setCor(resultSet.getString("cor"));
        Numero.setData(resultSet.getString("data"));
        Numero.setTurno(resultSet.getString("turno"));
        Numero.setTurno(resultSet.getString("dealer"));

        return Numero;
    }


}
