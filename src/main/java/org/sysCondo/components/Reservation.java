package org.sysCondo.components;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private int id;
    private String solicitante;
    //private String endereco;
    //private String contato;
    private String area;
    //private String codigoArea;
    private Date data;
    private String status;

    public Reservation(int id, String solicitante, String area, Date data, String status) {
        this.id = id;
        this.solicitante = solicitante;
        //this.endereco = endereco;
        //this.contato = contato;
        this.area = area;
        //this.codigoArea = codigoArea;
        this.data = data;
        this.status = status;
    }

    // Getters para acessar os dados de reserva
    public int getId() {
        return id;
    }
    public String getSolicitante() { return solicitante; }
    //public String getEndereco() { return endereco; }
    //public String getContato() { return contato; }
    public String getArea() { return area; }
    //public String getCodigoArea() { return codigoArea; }
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:00");
        return formatter.format(data);
    }
    public String getStatus() {
        return status;
    }
}
