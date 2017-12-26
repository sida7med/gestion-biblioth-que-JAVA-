import javax.swing.*;
import javax.swing.text.ParagraphView;
import java.awt.*;

/**
 * Created by ahmed on 23.12.17.
 */
public class BookInfo extends JPanel {

    private Book book;
    JLabel titleLabel = new JLabel("Title:");
    JLabel title;
    JLabel authorLabel = new JLabel("Author Name: ");
    JLabel author;
    JLabel resumeLabel = new JLabel("Resume:");
    JTextArea resume;
    JLabel stateLabel = new JLabel("State : ");
    JLabel state;
    JLabel dateLabel = new JLabel("Since: ");
    JLabel date;
    JLabel img;
    JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel details = new JPanel();

    public BookInfo(Book book) {
        super();
        this.book = book;
        img =new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/400x400/"+book.getImgUrl()));
        this.setBorder(BorderFactory.createEmptyBorder(50,10,50,10));
        line1.setBackground(new Color(238, 231, 158));
        line2.setBackground(new Color(238, 231, 158));
        line3.setBackground(new Color(238, 231, 158));
        line4.setBackground(new Color(238, 231, 158));
        line5.setBackground(new Color(238, 231, 158));
        title = new JLabel(""+this.book.getName());
        line1.add(titleLabel);line1.add(title);
        author = new JLabel(" "+book.getAuthor());
        line2.add(authorLabel);line2.add(author);
        resume = new JTextArea(" "+book.getResume(),5,22);
        resume.setEditable(false);
        resume.setLineWrap(true);
        resume.setWrapStyleWord(true);
        resume.setOpaque(false);
        line3.add(resumeLabel);line3.add(resume);
        state = new JLabel(" "+book.getState());
        line4.add(stateLabel);line4.add(state);
        date = new JLabel(" "+book.getDate());
        line5.add(dateLabel);line5.add(date);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(line1);details.add(line2);details.add(line3);details.add(line4);details.add(line5);
        details.setBackground(new Color(0,0,0,1));
        details.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(details);this.add(img);
        if (book.getState().equals("available")) {
            line5.setVisible(false);
        }
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
