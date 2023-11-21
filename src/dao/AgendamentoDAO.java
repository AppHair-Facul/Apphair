/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao; 

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.agendamento;
import view.CadastroCliente;


/**
 *
 * @author Administrador
 */
public class AgendamentoDAO {

    private final Connection connection;

    public AgendamentoDAO(Connection connection) {
        this.connection = connection;
    }
   public void insert(agendamento agendamento) throws SQLException {
    String sql = "INSERT INTO agendamento(cliente, servico, valor, data, hora, observacao) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, agendamento.getCliente());
        statement.setString(2, agendamento.getServico());
        statement.setDouble(3, agendamento.getValor());
        statement.setString(4, agendamento.getData());
        statement.setString(5, agendamento.getHora());
        statement.setString(6, agendamento.getObservacao());

        statement.execute();
    }
}
   
    public ArrayList<agendamento> selectAll() throws SQLException {
        // Método para selecionar todos os agendamentos no banco de dados
        String sql = "SELECT * FROM agendamento";
        ArrayList<agendamento> agendamentos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                agendamento agendamento = new agendamento(
                        resultSet.getString("cliente"),
                        resultSet.getString("servico"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("data"),
                        resultSet.getString("hora"),
                        resultSet.getString("observacao")
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Re-lança a exceção para que o controle de erros seja tratado na camada superior
        }

        return agendamentos;
    }
     

 }
    

