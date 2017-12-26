import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by ahmed on 25.12.17.
 */
public class ConfigurationForm extends JFrame {

    JLabel title= new JLabel("Configuration");
    JLabel nameLabel = new JLabel("Identifier: ");
    JTextField name = new JTextField(12);
    JLabel pswLabel = new JLabel("Old password: ");
    JPasswordField psw = new JPasswordField(12);
    JLabel pswLabel2 = new JLabel("New password: ");
    JPasswordField psw2 = new JPasswordField(12);
    JButton edit = new JButton("Edit");
    //Layout Panels
    JPanel line1 = new JPanel(new GridLayout(2,1));
    JPanel line2 = new JPanel(new GridLayout(2,1));
    JPanel line22 = new JPanel(new GridLayout(2,1));
    JPanel line3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel details = new JPanel();
    Color c1 = new Color(108, 73, 176);

    public ConfigurationForm() {

        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        isAlwaysOnTop();
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.GRAY);
        title.setFont(title.getFont().deriveFont(18.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(400,100));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16.0f));
        pswLabel.setFont(pswLabel.getFont().deriveFont(16.0f));
        pswLabel2.setFont(pswLabel2.getFont().deriveFont(16.0f));
        styleInput(name);styleInput(psw);
        styleInput(psw2);styleButton(edit);
        title.setBorder(BorderFactory.createMatteBorder(6,0,6,0,c1));
        line1.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line2.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line22.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line1.add(nameLabel);line1.add(name);
        line2.add(pswLabel);line2.add(psw);
        line22.add(pswLabel2);line22.add(psw2);
        line3.add(edit);
        details.setLayout(new GridLayout(5,1));
        details.add(title);details.add(line1);details.add(line2);details.add(line22);details.add(line3);
        Container container = this.getContentPane();
        container.add(details);
        // Actions
       edit.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] input = psw.getPassword();
                String password2 = new String(psw2.getPassword());
                if (isPasswordCorrect(input)) {
                    String req="UPDATE Epi SET Nom=\""+name.getText()+"\",Mdp=\""+password2+"\" WHERE Nom= \""+name.getText()+"\"";
                    int a= cx.requeteUpdate(req);
                    System.out.println();
                    if (a>0) {
                        JOptionPane.showMessageDialog(null, "Operation successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Something went wrong !","Information",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid password. Try again.",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    public void styleInput(JTextField text) {
        text.setBackground(new Color(122, 122, 122, 250));
        text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        text.setForeground(Color.WHITE);
        text.setSize(new Dimension(150, 40));
    }

    public void styleButton(JButton button) {
        button.setBackground(c1);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    private boolean isPasswordCorrect(char[] input) {
        Connexion cx = new Connexion();
        cx.con = cx.getConnexion();
        String password = new String(psw.getPassword());
        System.out.println(password);
        String req="SELECT Nom, Mdp FROM Epi WHERE ( Nom=\""+name.getText()+"\" AND Mdp=\""+password+"\" )";
        System.out.println(req);
        ResultSet a= cx.requeteSelect(req);
        System.out.println(a);
        if (a == null) {
            return false;
        }
        else {
            return true;
        }

    }

}
