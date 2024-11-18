package org.sysCondo.components;

import java.util.ArrayList;
import java.util.List;

public class Maintenence {
    private String area;
    private String tipo;
    private String solicitante;
    private String status;
    private String motivoRecusa;
    private List<Cost> custos;

    public Maintenence(String area, String tipo, String solicitante, String status, List<Cost> custos, String motivoRecusa) {
        this.area = area;
        this.tipo = tipo;
        this.solicitante = solicitante;
        this.status = status;
        this.custos = custos != null ? custos : new ArrayList<>();
        this.motivoRecusa = motivoRecusa;
    }


    public String getArea() {
        return area;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivoRecusa() {
        return motivoRecusa;
    }

    public void setMotivoRecusa(String motivoRecusa) {
        this.motivoRecusa = motivoRecusa;
    }

    public List<Cost> getCustos() {
        return custos;
    }

    public void addCusto(Cost custo) {
        this.custos.add(custo);
    }

    public double getTotalCustos() {
        return custos.stream().mapToDouble(Cost::getValor).sum();
    }

    @Override
    public String toString() {
        return "Área: " + area +
                " | Tipo: " + tipo +
                " | Solicitante: " + solicitante +
                " | Status: " + status +
                " | Custos: " + custos +
                " | Motivo da Recusa: " + motivoRecusa;
    }
}
