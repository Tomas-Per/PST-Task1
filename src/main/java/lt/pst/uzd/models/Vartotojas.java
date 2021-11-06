package lt.pst.uzd.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vartotojas {

    public Vartotojas() {

    }
    public Vartotojas(String vardas, String telNr) {
        super();
        this.vardas = vardas;
        this.telNr = telNr;
    }
    public Vartotojas(int id, String vardas, String telNr) {
        this.id = id;
        this.vardas = vardas;
        this.telNr = telNr;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String vardas;

    @Column
    private String telNr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vartotojas created = (Vartotojas) o;
        return this.id == created.getId() && Objects.equals(this.vardas, created.getVardas());
    }
}
