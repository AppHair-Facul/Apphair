/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class agendamento {
    private int id;
    private String cliente;
    private String servico;
    private double valor;
    private String hora;
    private String data;
    private String observacao;

    public agendamento(int id, String cliente, String servico, double valor, String hora, String data, String observacao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        this.hora = hora;
        this.data = data;
        this.observacao = observacao;
    }

    public agendamento(String cliente, String servico, double valor, String hora, String data, String observacao) {
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        this.hora = hora;
        this.data = data;
        this.observacao = observacao;
    }

    public agendamento(String cliente, String servico, double valor, String hora, String data) {
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        this.hora = hora;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    
    
    

   
      
}
