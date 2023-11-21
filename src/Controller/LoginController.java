/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Login;
import View.MenuPrincipal;
import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;

public class LoginController {
    private Login view;

    public LoginController(Login view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        
        // Buscar usuário na view
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        // Criar objeto Usuario com parâmetros adequados
        Usuario usuarioAutenticar = new Usuario(1, "", "", "", usuario, senha);
        
        // Verificar se existe no banco de dados
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        boolean existe = usuarioDao.existePorNomeESenha(usuarioAutenticar);
        
        // Se existir, direciona para a tela de menu
        if(existe){
            MenuPrincipal telaDeMenu = new MenuPrincipal();
            telaDeMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Nome ou Senha inválidos");
        }
    }
}
