/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Victor
 */
public class Cliente {
    
    protected int id;
    protected String telefone;
    protected String email;
    protected String rg;
    protected String nome;

    public Cliente(int id, String telefone, String email, String rg, String nome) {
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.nome = nome;
    }

    public Cliente(String telefone, String email, String rg, String nome) {
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.nome = nome;
    }

    public Cliente(int id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
}
