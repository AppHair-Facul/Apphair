/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class UsuarioDAO {
    
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    

    public boolean existePorNomeESenha(Usuario usuarioAutenticar) throws SQLException {
        String sql = "select * from usuario where nome = ? and senha = ?";
        
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, usuarioAutenticar.getUsuario());
        statement.setString(2, usuarioAutenticar.getSenha());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
        
    }
    
    public void insert(Usuario usuario) throws SQLException{
        String sql = "insert into usuario (nome, telefone, email, rg, senha) values (?, ?, ?, ?, ?)";        
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getTelefone());
        statement.setString(3, usuario.getEmail());
        statement.setString(4, usuario.getRg());
        statement.setString(5, usuario.getSenha());
        statement.execute();
    }
    
    public void update(Usuario usuario) throws SQLException{
        String sql = "update usuario set nome = ?, telefone = ?, email = ?, rg = ?, senha = ?";        
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getTelefone());
        statement.setString(3, usuario.getEmail());
        statement.setString(4, usuario.getRg());
        statement.setString(5, usuario.getSenha());
        statement.execute();
    }
    
    public void insertOrUpdate(Usuario usuario) throws SQLException{
        if (usuario.getId() > 0){
            update(usuario);
        }else{
            insert(usuario);
        }
    }
    
    public void delete(Usuario usuario) throws SQLException{
        String sql = "delete from cliente where id = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
    }
    
    public ArrayList<Usuario> selectAll() throws SQLException{
        String sql = "select * from usuario";        
        PreparedStatement statement =  connection.prepareStatement(sql);
        
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String usuario = resultSet.getString("nome");
            String telefone = resultSet.getString("telefone");
            String email = resultSet.getString("email");
            String rg = resultSet.getString("rg");
            String senha = resultSet.getString("senha");
            
            Usuario usuarioComDadosDoBanco = new Usuario(id, telefone, email, rg, usuario, senha);
            usuarios.add(usuarioComDadosDoBanco);
        }
        
        return usuarios;
    }
    
    public Usuario selectPorId(Usuario usuario) throws SQLException{
        String sql = "select * from usuario where id = ?";        
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        ArrayList<Usuario> usuarios = pesquisa(statement);
        return usuarios.get(0);
    }
  
}
 

