package lt.pst.uzd.models;

import javax.persistence.*;

@Entity
public class Veiksmai {

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
}
