package temp.domain;

public class QQZone {
    private Long id;
    private String title;
    private QQNumber number;

    public QQNumber getNumber() {
        return number;
    }

    public void setNumber(QQNumber number) {
        this.number = number;
    }

    public QQZone() {
    }

    public QQZone(Long id) {
        this.id = id;
    }

    public QQZone(String title) {
        this.title = title;
    }

    public QQZone(String title, QQNumber number) {
        this.title = title;
        this.number = number;
    }

    public QQZone(Long id, String title, QQNumber number) {
        this.id = id;
        this.title = title;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QQZone{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
