import javax.security.auth.login.Configuration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ahmed on 19.12.17.
 */
public class MainFrame extends JFrame implements ActionListener{
    //menu
    JMenuBar menu =new JMenuBar();
    //navBar
    JLabel fond= new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/bar6.jpg"));
    JPanel nav= new JPanel();
    JButton hide= new JButton("hide");
    JPanel bar= new JPanel();
    JPanel mainBar= new JPanel(new BorderLayout());
    //JLabel bg = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/640x640/bg.png"));
    JButton mod= new JButton("Add book",new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/icon8.png"));
    JButton conf= new JButton("Configuration",new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/icon2.png"));
    JButton list= new JButton("Consult dashboard",new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/icon1.png"));
    JButton aj= new JButton("Home",new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/home.png"));
    //CRUD Panels
    JButton b= new JButton(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/return.png"));
    Gallery gallery= new Gallery(b);

    public MainFrame() {
        setTitle("Main frame");
        setSize(1400,800);
        setLocationRelativeTo(null);
        Color cx = new Color(0, 0, 0);
        Color c = new Color(56, 50, 101);
        menu.setBackground(Color.BLACK);
        menu.setBorder(BorderFactory.createLineBorder(c));
        setJMenuBar(menu);
        mainBar.setBackground(new Color(10,10,10,1));
        mainBar.setLayout(new BorderLayout());
        mainBar.setBorder(BorderFactory.createEmptyBorder(50,50,651,50));
        mod.setBorder(BorderFactory.createMatteBorder(4,0,4,0,c));
        mod.setVerticalTextPosition(AbstractButton.CENTER);
        mod.setHorizontalTextPosition(AbstractButton.TRAILING);
        mod.setBackground(Color.BLACK);
        mod.setForeground(Color.LIGHT_GRAY);
        mod.setFont(mod.getFont().deriveFont(14.0f));
        mod.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mod.setBackground(UIManager.getColor("control"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mod.setBackground(Color.BLACK);
            }
        });
        conf.setBorder(BorderFactory.createMatteBorder(4,0,4,0,c));
        conf.setVerticalTextPosition(AbstractButton.CENTER);
        conf.setHorizontalTextPosition(AbstractButton.TRAILING);
        conf.setBackground(Color.BLACK);
        conf.setForeground(Color.LIGHT_GRAY);
        conf.setFont(mod.getFont().deriveFont(14.0f));
        conf.setCursor(new Cursor(Cursor.HAND_CURSOR));
        conf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                conf.setBackground(UIManager.getColor("control"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                conf.setBackground(Color.BLACK);
            }
        });
        list.setBorder(BorderFactory.createMatteBorder(4,0,4,0,c));
        list.setVerticalTextPosition(AbstractButton.CENTER);
        list.setHorizontalTextPosition(AbstractButton.TRAILING);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.LIGHT_GRAY);
        list.setFont(list.getFont().deriveFont(14.0f));
        list.setCursor(new Cursor(Cursor.HAND_CURSOR));
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                list.setBackground(UIManager.getColor("control"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                list.setBackground(Color.BLACK);
            }
        });
        list.addActionListener(this);
        aj.setBorder(BorderFactory.createMatteBorder(4,0,4,0,c));
        aj.setVerticalTextPosition(AbstractButton.CENTER);
        aj.setHorizontalTextPosition(AbstractButton.TRAILING);
        aj.setBackground(Color.BLACK);
        aj.setForeground(Color.LIGHT_GRAY);
        aj.setFont(aj.getFont().deriveFont(14.0f));
        aj.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aj.setBackground(UIManager.getColor("control"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                aj.setBackground(Color.BLACK);
            }
        });
        aj.addActionListener(this);
        //add nav elements
        nav.setLayout(new GridLayout(4,1,10,5));
        nav.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        nav.setBackground(new Color(0,0,0,1));
        nav.setSize(250,400);
        nav.add(list);nav.add(conf);nav.add(mod);nav.add(aj);
        hide.addActionListener(this);
        bar.setBorder(BorderFactory.createMatteBorder(0, 8, 0, 8, cx));
        bar.setLayout(new BorderLayout());
        bar.setBackground(new Color(5,5,5,180));
        bar.add(nav,BorderLayout.NORTH);
        bar.add(hide,BorderLayout.SOUTH);
        fond.setLayout(new BorderLayout());
        fond.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
        fond.add(bar,BorderLayout.WEST);
        fond.add(mainBar,BorderLayout.CENTER);
        add(fond);
        animate(gallery);
        b.addActionListener(this);
        mod.addActionListener(this);
        conf.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){
        JPanel panel = gallery;
        if (e.getSource()== list)
        {
            List center= new List();
            animate(center);
            panel = center;
        }
        if (e.getSource()== hide)
        {
            animateBar(panel);
        }
        if (e.getSource()== aj ||e.getSource()== b )
        {
            animate(new Gallery(b));
            System.out.println("click");
        }
        if (e.getSource()== mod )
        {
            new BookForm(null).setVisible(true);
        }
        if (e.getSource()== conf )
        {
            new ConfigurationForm().setVisible(true);
        }
    }

    public void animateBar(JPanel panel) {

        if (bar.getX() + bar.getWidth() >= 250) {
            new Timer(3, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bar.setLocation(bar.getX() - 1, 0);
                    mainBar.setLocation(mainBar.getX() - 1, 0);
                    panel.setSize(panel.getWidth() + 1,panel.getHeight());
                    if (bar.getX() + bar.getWidth() == 20) {
                        ((Timer) e.getSource()).stop();
                        System.out.println("Timer stopped");
                    }
                }
            }).start();
        }
        else {
            new Timer(3, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bar.setLocation(bar.getX() + 1, 0);
                    mainBar.setLocation(mainBar.getX() + 1, 0);
                    panel.setSize(panel.getWidth() - 1,panel.getHeight());
                    if (bar.getX() + bar.getWidth() == 250) {
                        ((Timer) e.getSource()).stop();
                        System.out.println("Timer stopped");
                    }
                }
            }).start();
        }
    }

    public void animate(JPanel panel) {

        mainBar.setBorder(BorderFactory.createEmptyBorder(50, 50, 651, 50));
        mainBar.removeAll();
        mainBar.add(panel,BorderLayout.CENTER);
        new Timer(2, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mainBar.getInsets().bottom > 50) {
                    mainBar.setBorder(BorderFactory.createEmptyBorder(50, 50, mainBar.getInsets().bottom - 1, 50));
                    if (mainBar.getInsets().bottom == 50) {
                        ((Timer) e.getSource()).stop();
                        System.out.println("Timer stopped");
                    }
                }
                else {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }


    public static void main(String[] args) {
    }
}
