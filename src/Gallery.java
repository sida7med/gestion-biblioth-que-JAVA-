import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by ahmed on 22.12.17.
 */
public class Gallery extends JPanel {

    ArrayList<Book> data;
    JScrollPane scrollPane;
    JPanel container= new JPanel();
    public Gallery(JButton b) {
        super();
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createMatteBorder(10,0,10,0,new Color(56, 50, 101)));
        this.container.setLayout(new BoxLayout(this.container,BoxLayout.Y_AXIS));
        this.container.setBackground(new Color(0,0,0,1));
        data = queryData();
        for (Book book : data) {
            AdPanel adPanel = new AdPanel(book);
            adPanel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    infoTrigger(b,book);

                }

            });
            this.container.add(adPanel);
        }
        this.scrollPane = new JScrollPane(this.container);
        this.scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                repaint();
            }
        });
        this.scrollPane.setBackground(new Color(0,0,0));
        this.scrollPane.getVerticalScrollBar().setBackground(new Color(56, 50, 101));
        this.scrollPane.setPreferredSize(new Dimension(700,580));
        this.add(scrollPane);
    }

    private ArrayList<Book> queryData() {
        ResultSet resultSelect;
        ArrayList<Book> data = new ArrayList<>();
        Connexion cx = new Connexion();
        cx.con = cx.getConnexion();
        String req="SELECT * FROM Books";
        resultSelect= cx.requeteSelect(req);
        try {
            while (resultSelect.next()) {
                int id = resultSelect.getInt("book_id");
                String name = resultSelect.getString("name");
                String author = resultSelect.getString("author");
                String imageUrl = resultSelect.getString("image");
                String resume = resultSelect.getString("resume");
                String state = resultSelect.getString("state");
                String date = resultSelect.getString("date");
                data.add(new Book(id,name,author,imageUrl,resume,state,date));
            }
        }catch (SQLException e){
            System.out.println("Null pointer exception:"+e);
        }
        return data;
    }

    private void infoTrigger(JButton b,Book book) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelButtons = new JPanel(new FlowLayout());
        JPanel prec = new JPanel(new FlowLayout());
        JLabel paper = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/800x800/paper2.png"));
        this.setVisible(false);
        this.removeAll();
        BookInfo bookInfo = new BookInfo(book);
        bookInfo.setBackground(new Color(0,0,0,1));
        panel.add(bookInfo, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(40,70,0,100));
        b.setBackground(new Color(0,0,0));
        b.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton b1= new JButton("Modify");
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton b2= new JButton("Delete");
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton b3= new JButton("Lend");
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookForm(book).setVisible(true);
            }
        });
        b2.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            int a=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                a=JOptionPane.showConfirmDialog(null,"Are you sure?","Delete",JOptionPane.YES_NO_OPTION);
                if (a == 0 ) {
                    String req = "DELETE FROM Books WHERE book_id="+book.getiD()+" ";
                    System.out.println(req);
                    int a = cx.requeteUpdate(req);
                    System.out.println(a);
                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "Operation successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong !", "Information", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            @Override
            public void actionPerformed(ActionEvent e) {
                    String newDate= ecrire(book);
                    String req = "UPDATE Books SET state=\""+book.getState()+"\",date=\""+newDate+"\" WHERE book_id= "+book.getiD();
                    int a = cx.requeteUpdate(req);
                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "Operation successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong !", "Information", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
        panelButtons.add(b1);panelButtons.add(b2);panelButtons.add(b3);
        panel.setBackground(new Color(0,0,0,1));
        panelButtons.setBackground(new Color(0,0,0,1));
        prec.setBackground(new Color(0,0,0));
        prec.add(b);
        paper.setLayout(new BoxLayout(paper,BoxLayout.Y_AXIS));
        paper.add(panel);paper.add(panelButtons);
        this.setLayout(new BorderLayout());
        this.add(prec,BorderLayout.WEST);this.add(paper,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public String ecrire(Book book) {
        BufferedWriter bf;
        String rDate=" ";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        try{
            bf = new BufferedWriter(new FileWriter("/home/ahmed/IdeaProjects/Project/src/files/"+book.getName()+""+book.getiD()+".txt"));
            rDate=""+dtf.format(localDate);
            String msg="Title: "+book.getName()+"\n" +
                    "Author: "+book.getAuthor()+"\n" +
                    "Lend date: "+rDate+"\n" +
                    "Lend period: 10 days";
            bf.write(msg);
            bf.close();
        }
        catch(Exception e) { e.printStackTrace(); }
        return rDate;
    }

}
