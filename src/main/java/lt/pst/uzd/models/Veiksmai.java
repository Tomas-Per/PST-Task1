package lt.pst.uzd.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Veiksmai {

    public Veiksmai() {

    }
    public Veiksmai(int id, String veiksmas, int vartotojoId, String data) {
        this.id = id;
        this.veiksmas = veiksmas;
        this.vartotojoId = vartotojoId;
        this.data = data;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String veiksmas;

    @Column
    private int vartotojoId;

    @Column
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVeiksmas() {
        return veiksmas;
    }

    public void setVeiksmas(String veiksmas) {
        this.veiksmas = veiksmas;
    }

    public int getVartotojoId() {
        return vartotojoId;
    }

    public void setVartotojoId(int vartotojoId) {
        this.vartotojoId = vartotojoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        Veiksmai created = (Veiksmai) o;
        return id == created.id && Objects.equals(veiksmas, created.veiksmas);
    }
}
