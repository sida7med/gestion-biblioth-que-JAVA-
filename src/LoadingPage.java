import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoadingPage extends JFrame{

    // Preparing components
    JLabel background;
    JPanel panel;
    JPanel panelForm= new JPanel();
    JLabel label1= new JLabel("WELCOME TO");
    JLabel label2= new JLabel("I-LIBRARY");
    JLabel label3= new JLabel("Archive and file manager");
    JLabel load=new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/813.gif"));
    JLabel login= new JLabel("Identifier");
    JLabel psw= new JLabel("Password");
    JTextField email= new JTextField(12);
    JPasswordField pass= new JPasswordField(12);
    JButton connexion= new JButton("Connexion");

    public LoadingPage() {
        setTitle("Loading page");
        setUndecorated(true);
        setSize(639, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        // Styling header elements
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 35);
        label1.setForeground(Color.WHITE);
        label1.setFont(font);
        label1.setHorizontalAlignment(SwingConstants.LEFT);

        Color c1 = new Color(46, 41, 78);
        label2.setForeground(new Color(94, 103, 179));
        label2.setFont(label2.getFont().deriveFont(24.0f));
        label2.setHorizontalAlignment(SwingConstants.LEFT);

        label3.setForeground(Color.LIGHT_GRAY);
        label3.setFont(label3.getFont().deriveFont(12.0f));
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        // background image and elements addition in a bordered layout label
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/page.jpg"));
        add(background);
        background.setLayout(null);
        background.add(label1);
        background.add(label2);
        background.add(label3);
        background.add(load);
        label1.setSize(400, 60);
        label1.setLocation(25, 50);
        label2.setSize(200, 40);
        label2.setLocation(25, 100);
        label3.setSize(500, 25);
        label3.setLocation(25, 140);
        load.setSize(300, 200);
        load.setLocation(180, 200);
        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 170));
        panel.setBounds(-270, 0, 270, 480);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 20, 0, c1));
        panelForm.setLayout(new GridLayout(5, 0, 0, 20));
        panelForm.setBackground(new Color(0, 0, 0, 1));
        panelForm.setBorder(BorderFactory.createEmptyBorder(150, 20, 100, 20));
        // Styling form elements
        login.setForeground(Color.WHITE);
        psw.setForeground(Color.WHITE);
        email.setBackground(new Color(10, 10, 10, 250));
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        email.setPreferredSize(new Dimension(150, 30));
        email.setForeground(Color.WHITE);
        pass.setBackground(new Color(10, 10, 10, 250));
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        pass.setForeground(Color.WHITE);
        connexion.setBackground(c1);
        connexion.setForeground(Color.WHITE);
        connexion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        connexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Action saveAction = new AbstractAction("Enter") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Entering...");
            }
        };

        saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_ENTER);
        connexion.setAction(saveAction);
        panelForm.add(login);
        panelForm.add(email);
        panelForm.add(psw);
        panelForm.add(pass);
        panelForm.add(connexion);
        panel.add(panelForm);

        // Connexion action

        connexion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    char[] input = pass.getPassword();
                    if (isPasswordCorrect(input)) {
                        new MainFrame().setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Invalid password. Try again.",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                    }

            }

        });
    }

    private boolean isPasswordCorrect(char[] input) {
        Connexion cx = new Connexion();
        cx.con = cx.getConnexion();
        String password = new String(pass.getPassword());
        System.out.println(password);
        String req="SELECT Nom, Mdp FROM Epi WHERE ( Nom=\""+email.getText()+"\" AND Mdp=\""+password+"\" )";
        System.out.println(req);
        ResultSet a= cx.requeteSelect(req);
        try {
            while (a.next()) {
            System.out.println("1"+a.getString("Nom"));
                System.out.println("2"+a.getString("Mdp"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (a == null) {
            return false;
        }
        else {
            return true;
        }

    }
    // Animation function
    public void makeUI() {
        try {
            Thread.sleep(3500L);
        }
	    catch (Exception e) {System.out.println("Sleep error");}
        background.remove(load);
        new Timer(2, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setLocation(panel.getX() + 1, 0);
                label1.setLocation(label1.getX() + 1, 50);
                label2.setLocation(label2.getX() + 1, 100);
                label3.setLocation(label3.getX() + 1, 140);
                if (panel.getX() + panel.getWidth() == 270) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Timer stopped");
                }
            }
        }).start();
        background.add(panel);
        dispose();
        setUndecorated(false);
        setVisible(true);
    }

    public static void main(String[] args){
        LoadingPage f = new LoadingPage();
        f.setVisible(true);
        // Sliding form
        f.makeUI();
    }

}