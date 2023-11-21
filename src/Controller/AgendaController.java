/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Agenda;
import dao.AgendamentoDAO;
import dao.ClienteDAO;
import dao.Conexao;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.agendamento;

/**
 *
 * @author Administrador
 */
public class AgendaController {

    private Agenda view;
    private ClienteDAO clienteDao;

    public AgendaController(Agenda view) throws SQLException {
        this.view = view;
        this.clienteDao = new ClienteDAO(new Conexao().getConnection());
        // Adicione um método para carregar os nomes dos clientes no JComboBox
        carregarNomesClientes();
    }

    private void carregarNomesClientes() {
        try {
            ArrayList<String> nomesClientes = clienteDao.selectAllNomes();
            for (String nomeCliente : nomesClientes) {
                view.getjComboBoxClientes().addItem(nomeCliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarAgendamento() {
        String agendamentoCliente = (String) view.getjComboBoxClientes().getSelectedItem();
        String agendamentoServico = view.getjTextFieldServico().getText();
        String agendamentoValor = view.getjTextFieldValor().getText();
        String agendamentoData = view.getjTextFieldData().getText();
        String agendamentoHora = view.getjTextFieldHora().getText();
        String agendamentoObs = view.getjTextObs().getText();

        // Validação de Entradas
        if (agendamentoCliente == null || agendamentoServico.isEmpty() || agendamentoValor.isEmpty() || agendamentoData.isEmpty() || agendamentoHora.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valor = Double.parseDouble(agendamentoValor);

            // Criar objeto agendamento
            agendamento Agendamento = new agendamento(WIDTH, agendamentoCliente, agendamentoServico, valor, agendamentoHora, agendamentoData, agendamentoObs);

            // Realizar o agendamento
            Connection conexao = new Conexao().getConnection();
            AgendamentoDAO agendamentoDao = new AgendamentoDAO(conexao);
            agendamentoDao.insert(Agendamento);

            String emailCliente = clienteDao.selectEmailByNome(agendamentoCliente); // Substitua pelo método real do seu DAO
            String assunto = "Agendamento - Barbeiro";
            String corpo = "Seu agendamento foi marcado para " + Agendamento.getData()
                    + " no horário " + Agendamento.getHora()
                    + " com um valor de R$" + Agendamento.getValor()
                    + ". Favor chegar com 15 minutos de antecedência.";

            EmailSender.enviarEmail(emailCliente, assunto, corpo);

            // Limpar campos após o agendamento bem-sucedido
            limparCampos();

            JOptionPane.showMessageDialog(view, "O agendamento foi realizado com sucesso!!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um valor válido para o campo Valor!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar o agendamento:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limparCampos() {
        view.getjComboBoxClientes().setSelectedIndex(-1);
        view.getjTextFieldServico().setText("");
        view.getjTextFieldValor().setText("");
        view.getjTextFieldData().setText("");
        view.getjTextFieldHora().setText("");
        view.getjTextObs().setText("");
    }

    public void atualizarTabela() {
        DefaultTableModel tabelaAgenda = (DefaultTableModel) view.getjTableTaBela().getModel();
        tabelaAgenda.setRowCount(0); // Limpa a tabela antes de adicionar os dados atualizados

        try {
            AgendamentoDAO agendamentoDao = new AgendamentoDAO(new Conexao().getConnection());
            ArrayList<agendamento> agendamentos = agendamentoDao.selectAll(); // Supondo que você tenha um método para selecionar todos os agendamentos

            for (agendamento agendamento : agendamentos) {
                Object[] dados = {agendamento.getCliente(), agendamento.getServico(), agendamento.getValor(), agendamento.getData(), agendamento.getHora(), agendamento.getObservacao()};
                tabelaAgenda.addRow(dados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Erro ao atualizar a tabela:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
