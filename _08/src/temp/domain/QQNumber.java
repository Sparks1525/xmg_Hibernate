package temp.domain;

public class QQNumber {
    private Long id;
    private Integer number;
    private QQZone zone;

    public QQZone getZone() {
        return zone;
    }

    public void setZone(QQZone zone) {
        this.zone = zone;
    }

    public QQNumber() {
    }

    public QQNumber(Long id) {
        this.id = id;
    }

    public QQNumber(Integer number) {
        this.number = number;
    }

    public QQNumber(Long id, Integer number, QQZone zone) {
        this.id = id;
        this.number = number;
        this.zone = zone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "QQNumber{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
