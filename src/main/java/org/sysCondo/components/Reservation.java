package org.sysCondo.components;

import java.util.Date;

public class Reservation {
    private String solicitante;
    private String endereco;
    private String contato;
    private String area;
    private String codigoArea;
    private Date data;
    private String status;

    public Reservation(String solicitante, String endereco, String contato, String area, String codigoArea, Date data) {
        this.solicitante = solicitante;
        this.endereco = endereco;
        this.contato = contato;
        this.area = area;
        this.codigoArea = codigoArea;
        this.data = data;
        this.status = status;
    }

    // Getters para acessar os dados de reserva
    public String getSolicitante() { return solicitante; }
    public String getEndereco() { return endereco; }
    public String getContato() { return contato; }
    public String getArea() { return area; }
    public String getCodigoArea() { return codigoArea; }
    public Date getData() { return data; }
    public void setStatus(String status) { this.status = status; }
}
