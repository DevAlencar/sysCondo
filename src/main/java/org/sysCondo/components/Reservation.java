package org.sysCondo.components;

import java.util.Date;

public class Reservation {
    private String solicitante;
    private String endereco;
    private String contato;
    private String area;
    private String codigoArea;
    private Date data;
<<<<<<< HEAD
    private String status;
=======
    private String timeSlot;
>>>>>>> 3ba456da79e3386e9ce7357b364856eb47f481b5

    public Reservation(String solicitante, String endereco, String contato, String area, String codigoArea, Date data, String timeSlot) {
        this.solicitante = solicitante;
        this.endereco = endereco;
        this.contato = contato;
        this.area = area;
        this.codigoArea = codigoArea;
        this.data = data;
<<<<<<< HEAD
        this.status = status;
=======
        this.timeSlot = timeSlot;
>>>>>>> 3ba456da79e3386e9ce7357b364856eb47f481b5
    }

    // Getters para acessar os dados de reserva
    public String getSolicitante() { return solicitante; }
    public String getEndereco() { return endereco; }
    public String getContato() { return contato; }
    public String getArea() { return area; }
    public String getCodigoArea() { return codigoArea; }
    public Date getData() { return data; }
<<<<<<< HEAD
    public void setStatus(String status) { this.status = status; }
=======
    public String getTimeSlot() {return timeSlot; }
>>>>>>> 3ba456da79e3386e9ce7357b364856eb47f481b5
}
