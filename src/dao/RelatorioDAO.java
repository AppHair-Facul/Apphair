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
import java.util.List;
import model.modelRelatorio;

public class RelatorioDAO {
    
    public List<modelRelatorio> getRelatoriosByData(String data) {
        List<modelRelatorio> relatorios = new ArrayList<>();

        try (Connection conexao = new Conexao().getConnection()) {
            String sql = "SELECT * FROM agendamento WHERE id > 0 and data = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, data);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String cliente = rs.getString("cliente");
                        String servico = rs.getString("servico");
                        int valor = rs.getInt("valor");
                        String dataDb = rs.getString("data");
                        String hora = rs.getString("hora");
                        String observacao = rs.getString("observacao");
                        
                        modelRelatorio relatorio = new modelRelatorio(id, cliente, servico, valor, dataDb, hora, observacao);
                        relatorios.add(relatorio);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção de maneira apropriada para sua aplicação
        }

        return relatorios;
    }

    public void deletarRelatorioPorId(int id) {
        try (Connection conexao = new Conexao().getConnection()) {
            String sql = "DELETE FROM agendamento WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção de maneira apropriada para sua aplicação
        }
    }
}
