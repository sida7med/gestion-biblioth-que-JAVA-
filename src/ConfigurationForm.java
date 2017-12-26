import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ahmed on 25.12.17.
 */
public class ConfigurationForm extends JFrame {

    JLabel nameLabel = new JLabel("Name: ");
    JTextField name = new JTextField(12);
    JLabel authorLabel = new JLabel("Author: ");
    JTextField author = new JTextField(12);
    JButton edit = new JButton("Edit");
    //Layout Panels
    JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel details = new JPanel();
    Color c1 = new Color(56, 50, 101);
    JLabel home = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/bar6.jpg"));

    public ConfigurationForm() {

        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        isAlwaysOnTop();
        home.setBackground(Color.BLACK);
        home.setBorder(BorderFactory.createEmptyBorder(50,10,50,10));
        line1.setBackground(new Color(0,0,0,1));
        line2.setBackground(new Color(0,0,0,1));
        line3.setBackground(new Color(0,0,0,1));
        nameLabel.setForeground(Color.white);nameLabel.setFont(nameLabel.getFont().deriveFont(16.0f));
        authorLabel.setForeground(Color.white);authorLabel.setFont(authorLabel.getFont().deriveFont(16.0f));
        styleInput(name);styleInput(author);
        styleButton(edit);
        line1.add(nameLabel);line1.add(name);
        line2.add(authorLabel);line2.add(author);
        line3.add(edit);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(line1);details.add(line2);details.add(line3);
        details.setBackground(new Color(0,0,0,1));
        home.setLayout(new BorderLayout());
        home.add(details,BorderLayout.CENTER);
        Container container = this.getContentPane();
        container.add(home);
        // Actions
       /* edit.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            @Override
            public void actionPerformed(ActionEvent e) {
                String req="UPDATE Books SET book_id="+book.getiD()+",name=\""+name.getText()+"\",author=\""+author.getText()+"\",image=\""+image.getText()+"\",resume=\""+resume.getText()+"\",state=\""+stateValue+"\",date=\""+dateValue+"\" WHERE book_id= "+book.getiD()+"";
                int a= cx.requeteUpdate(req);
                System.out.println(a);
                if (a>0) {
                    JOptionPane.showMessageDialog(null, "Operation successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Something went wrong !","Information",JOptionPane.ERROR_MESSAGE);
                }
            }
        });*/

    }

    public void styleInput(JTextField text) {
        text.setBackground(new Color(65, 65, 65, 250));
        text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        text.setForeground(Color.WHITE);
        text.setSize(new Dimension(150, 40));
    }

    public void styleButton(JButton button) {
        button.setBackground(c1);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

    }

}
