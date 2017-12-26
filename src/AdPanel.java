import javax.swing.*;
import java.awt.*;

/**
 * Created by ahmed on 22.12.17.
 */
public class AdPanel extends JButton {

    Book book;
    JLabel icon;
    JPanel identifier;
    JLabel state;

    public AdPanel(Book book) {
        this.book = book;
        this.icon = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/128x128/"+book.getImgUrl()));
        this.identifier = new JPanel();
        this.identifier.setLayout(new BoxLayout(this.identifier, BoxLayout.Y_AXIS));
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 18);
        JLabel l1= new JLabel(this.book.getName());
        l1.setForeground(Color.WHITE);
        l1.setFont(font);
        l1.setSize(100,30);
        JLabel l2= new JLabel(this.book.getAuthor());
        l2.setFont(l2.getFont().deriveFont(16.0f));
        l2.setForeground( new Color(229, 58, 64));
        this.identifier.setBackground(new Color(0,0,0,1));
        this.identifier.add(l1);
        this.identifier.add(l2);
        this.state = new JLabel(this.book.getState());
        this.state.getFont().isBold();
        if (this.state.getText().contentEquals( "available")) {
            this.state.setForeground(Color.GREEN);
        }
        else {
            this.state.setForeground(Color.red);
        }
        this.setBackground(new Color(0,0,0,1));
        this.setSize(200,100);
        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        this.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color(56, 50, 101)));
        this.add(this.icon);this.add(this.identifier);this.add(this.state);
    }
}
