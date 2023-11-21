/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.RelatorioDAO;
import java.util.List;
import model.modelRelatorio;

public class RelatorioController {

    private final RelatorioDAO relatorioDAO;

    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO();
    }

    public List<modelRelatorio> getRelatoriosByData(String data) {
        return relatorioDAO.getRelatoriosByData(data);
    }

    public void deletarRelatorioPorId(int id) {
        relatorioDAO.deletarRelatorioPorId(id);
    }
}
