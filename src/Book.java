/**
 * Created by ahmed on 21.12.17.
 */
public class Book {
    private int iD;
    private String name;
    private String author;
    private String imgUrl;
    private String resume;
    private String state;
    private String date;

    public Book() {

    }

    public Book(int iD,String name, String author, String imgUrl, String resume, String state, String date) {

        this.iD = iD;
        this.name = name;
        this.author = author;
        this.imgUrl = imgUrl;
        this.resume = resume;
        this.state = state;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public String getResume() {
        return resume;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", resume='" + resume + '\'' +
                ", state='" + state + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
