/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Victor
 */


public class Usuario {
    protected int id;
    protected String telefone;
    protected String email;
    protected String rg;
    protected String usuario;
    protected String senha;

    public Usuario(int id, String telefone, String email, String rg, String usuario, String senha) {
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String telefone, String email, String rg, String usuario, String senha) {
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

}
