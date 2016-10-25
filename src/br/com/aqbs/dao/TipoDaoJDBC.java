/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.dao;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static br.com.aqbs.dao.DaoUtil.prepareStatement;
import br.com.aqbs.model.Tipo;

/**
 *
 * @author aqbs
 */
public class TipoDaoJDBC implements IDaoTipo {

    private static final String SQL_ALL
            = "SELECT * FROM testeCerto.Tipo";
     private static final String SQL_INSERT
            = "INSERT INTO `Tipo`(`nome`, `pagamento`) VALUES (?, ?)";
     private static final String SQL_SP_LIST
            = "call {sp_lista(?)";
   
     private DaoFactory daoFactory;

    public TipoDaoJDBC(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Tipo find(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Tipo l) throws IllegalArgumentException, DaoException {
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
    public void update(Tipo l) throws IllegalArgumentException, DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipo> list() throws DaoException {
        List<Tipo> Numero = new ArrayList<>();

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

    private static Tipo map(ResultSet resultSet) throws SQLException {
        Tipo Numero = new Tipo();
        Numero.setId(resultSet.getInt("idTipo"));
        Numero.setNome(resultSet.getString("nome"));
        Numero.setPagamento(resultSet.getInt("pagamento"));
       

        return Numero;
    }


}
