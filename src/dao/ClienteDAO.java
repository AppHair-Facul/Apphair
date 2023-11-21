/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import view.CadastroCliente;
import Controller.AgendaController;


/**
 *
 * @author Victor
 */
public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Cliente cliente) throws SQLException {
        String sql = "insert into cliente(nome, telefone, email, rg) values(?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getTelefone());
        statement.setString(3, cliente.getEmail());
        statement.setString(4, cliente.getRg());
        statement.execute();

    }

    public void update(Cliente cliente) throws SQLException {
        String sql = "update cliente set nome = ?, telefone = ?, email = ?, rg = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getTelefone());
        statement.setString(3, cliente.getEmail());
        statement.setString(4, cliente.getRg());
        statement.setInt(5, cliente.getId());
        statement.execute();
    }

    public void insertOrUpdate(Cliente cliente) throws SQLException {
        if (cliente.getId() > 0) {
            update(cliente);
        } else {
            insert(cliente);
        }
    }

    public void delete(Cliente cliente) throws SQLException {
        String sql = "delete from cliente where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cliente.getId());
        statement.execute();
    }

    public ArrayList<Cliente> selectAll() throws SQLException {
        String sql = "select * from cliente";
        PreparedStatement statement = connection.prepareStatement(sql);

        return pesquisa(statement);

    }

    private ArrayList<Cliente> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String telefone = resultSet.getString("telefone");
            String email = resultSet.getString("email");
            String rg = resultSet.getString("rg");

            Cliente clienteComDadosDoBanco = new Cliente(id, telefone, email, rg, nome);
            clientes.add(clienteComDadosDoBanco);
        }

        return clientes;
    }

    public Cliente selectPorId(Cliente cliente) throws SQLException {
        String sql = "select * from cliente where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cliente.getId());
        return pesquisa(statement).get(0);

    }

    public ArrayList<String> selectAllNomes() throws SQLException {
        String sql = "SELECT nome FROM cliente";
        PreparedStatement statement = connection.prepareStatement(sql);
        return pesquisaNomes(statement);
    }

    private ArrayList<String> pesquisaNomes(PreparedStatement statement) throws SQLException {
        ArrayList<String> nomesClientes = new ArrayList<String>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            nomesClientes.add(nome);
        }

        return nomesClientes;
    }

    public String selectEmailByNome(String nome) throws SQLException {
        String sql = "SELECT email FROM cliente WHERE id > 0 and nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                } else {
                    // Trate o caso em que o cliente não foi encontrado
                    return null;
                }
            }
        }
    }
}
