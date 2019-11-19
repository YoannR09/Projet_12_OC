package fr.yr.site.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commande {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private Integer statutId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getStatutId() {
        return statutId;
    }

    public void setStatutId(Integer statutId) {
        this.statutId = statutId;
    }
}
