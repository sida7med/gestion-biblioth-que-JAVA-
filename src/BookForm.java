import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by ahmed on 24.12.17.
 */
public class BookForm extends JFrame {
    JLabel nameLabel = new JLabel("Name: ");
    JTextField name = new JTextField(12);
    JLabel authorLabel = new JLabel("Author: ");
    JTextField author = new JTextField(12);
    JLabel imageLabel = new JLabel("Image: ");
    JTextField image = new JTextField(12);
    JLabel stateLabel = new JLabel("state: ");
    String[] list = { "available","not available" };
    JComboBox state = new JComboBox(list);
    JLabel dateLabel = new JLabel("Date: ");
    JTextField date = new JTextField(12);
    JLabel resumeLabel = new JLabel("Resume: ");
    JTextArea resume = new JTextArea(5,12);
    JButton edit = new JButton("Modify");
    JButton add = new JButton("Add");
    //Layout Panels
    JPanel line = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel line6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel details = new JPanel();
    Color c1 = new Color(56, 50, 101);
    JLabel home = new JLabel(new ImageIcon("/home/ahmed/IdeaProjects/Project/src/images/bar6.jpg"));

    public BookForm(Book book) {

        setSize(400, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        isAlwaysOnTop();
        home.setBackground(Color.BLACK);
        home.setBorder(BorderFactory.createEmptyBorder(50,10,50,10));
        line.setBackground(new Color(0,0,0,1));
        line1.setBackground(new Color(0,0,0,1));
        line2.setBackground(new Color(0,0,0,1));
        line3.setBackground(new Color(0,0,0,1));
        line4.setBackground(new Color(0,0,0,1));
        line5.setBackground(new Color(0,0,0,1));
        line6.setBackground(new Color(0,0,0,1));
        nameLabel.setForeground(Color.white);nameLabel.setFont(nameLabel.getFont().deriveFont(16.0f));
        authorLabel.setForeground(Color.white);authorLabel.setFont(authorLabel.getFont().deriveFont(16.0f));
        imageLabel.setForeground(Color.white);imageLabel.setFont(imageLabel.getFont().deriveFont(16.0f));
        resumeLabel.setForeground(Color.white);resumeLabel.setFont(resumeLabel.getFont().deriveFont(16.0f));
        stateLabel.setForeground(Color.white);stateLabel.setFont(stateLabel.getFont().deriveFont(16.0f));
        dateLabel.setForeground(Color.white);dateLabel.setFont(dateLabel.getFont().deriveFont(16.0f));
        styleInput(name);styleInput(author);styleInput(image);styleInput(date);
        resume.setBackground(new Color(65, 65, 65, 250));
        resume.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        resume.setForeground(Color.WHITE);
        resume.setSize(new Dimension(150, 40));
        state.setBackground(new Color(65, 65, 65, 250));
        state.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        state.setForeground(Color.WHITE);
        state.setSize(new Dimension(150, 40));
        styleButton(edit);styleButton(add);
        line1.add(nameLabel);line1.add(name);
        line2.add(authorLabel);line2.add(author);
        line.add(imageLabel);line.add(image);
        line3.add(resumeLabel);line3.add(resume);
        line4.add(stateLabel);line4.add(state);
        line5.add(dateLabel);line5.add(date);
        line6.add(add);line6.add(edit);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(line1);details.add(line2);details.add(line);details.add(line3);details.add(line4);details.add(line5);details.add(line6);
        details.setBackground(new Color(0,0,0,1));
        home.setLayout(new BorderLayout());
        home.add(details,BorderLayout.CENTER);
        Container container = this.getContentPane();
        container.add(home);
        //
        if (book == null) {
            setTitle("Add");
            edit.setVisible(false);
            line4.setVisible(false);
            line5.setVisible(false);
        }
        else {
            setTitle("Modify");
            name.setText(book.getName());
            author.setText(book.getAuthor());
            image.setText(book.getImgUrl());
            resume.setText(book.getResume());
            if (book.getState().equals("available")) {
                line4.setVisible(false);
                line5.setVisible(false);
            }
            else {
                state.setSelectedIndex(1);
                date.setText(book.getDate());
            }
            add.setVisible(false);
        }

        // Actions
        add.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            String dateValue ="null";
            String stateValue = "available";
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (line5.isVisible()) {
                    dateValue = date.getText();
                }
                System.out.println(state.getSelectedItem().toString());
                if (state.getSelectedIndex() == 1) {
                    stateValue = "not available";
                }*/
                String req="INSERT INTO Books(name,author,image,resume,state,date) VALUES(\""+name.getText()+"\",\""+author.getText()+"\",\""+image.getText()+"\",\""+resume.getText()+"\",\"available\",\"NULL\")";
                int a= cx.requeteUpdate(req);
                if (a>0){
                    JOptionPane.showMessageDialog(null,"Operation successful!","Information",JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Something went wrong !","Information",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            Connexion cx=new Connexion();
            String dateValue ="null";
            String stateValue = "available";
            @Override
            public void actionPerformed(ActionEvent e) {
                if (line5.isVisible()) {
                    dateValue = date.getText();
                }
                System.out.println(state.getSelectedItem().toString());
                if (state.getSelectedIndex() == 1) {
                    stateValue = "not available";
                }
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
        });

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
