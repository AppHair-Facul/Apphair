/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ClienteDAO;
import dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import view.CadastroCliente;

/**
 *
 * @author Victor
 */
public class FormCadastroCliente {
    
    private CadastroCliente view;

    public FormCadastroCliente(CadastroCliente view) {
        this.view = view;
    }
    
    public void salvaCliente(){
                
        String nomeCliente = view.getjTextFieldNome().getText();
        String emailCliente = view.getjTextFieldEmail().getText();
        String telefoneCliente = view.getjTextFieldTelefone().getText();
        String rgCliente = view.getjTextFieldRG().getText();
        
        Cliente clienteSara = new Cliente(telefoneCliente, emailCliente, rgCliente, nomeCliente);
        
        try {
            Connection conexao = new Conexao().getConnection();
            ClienteDAO clienteDao = new ClienteDAO(conexao);
            clienteDao.insert(clienteSara);
            
            JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
