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
    JPanel line = new JPanel(new GridLayout(1,2));
    JPanel line1 = new JPanel(new GridLayout(1,2));
    JPanel line2 = new JPanel(new GridLayout(2,1));
    JPanel line3 = new JPanel(new GridLayout(2,1));
    JPanel line4 = new JPanel(new GridLayout(1,2));
    JPanel line5 = new JPanel(new GridLayout(2,1));
    JPanel line6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel details = new JPanel();
    Color c1 = new Color(113, 72, 180);
    JLabel title = new JLabel();

    public BookForm(Book book) {

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
        nameLabel.setFont(nameLabel.getFont().deriveFont(16.0f));
        authorLabel.setFont(authorLabel.getFont().deriveFont(16.0f));
        imageLabel.setFont(imageLabel.getFont().deriveFont(16.0f));
        resumeLabel.setFont(resumeLabel.getFont().deriveFont(16.0f));
        stateLabel.setFont(stateLabel.getFont().deriveFont(16.0f));
        dateLabel.setFont(dateLabel.getFont().deriveFont(16.0f));
        styleInput(name);styleInput(author);styleInput(image);styleInput(date);
        resume.setBackground(new Color(122, 122, 122, 250));
        resume.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        resume.setForeground(Color.WHITE);
        resume.setSize(new Dimension(150, 40));
        state.setBackground(new Color(122, 122, 122, 250));
        state.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, c1));
        state.setForeground(Color.WHITE);
        state.setSize(new Dimension(150, 40));
        styleButton(edit);styleButton(add);
        title.setBorder(BorderFactory.createMatteBorder(6,0,6,0,c1));
        line.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line1.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line2.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line3.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line4.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line5.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        line1.add(nameLabel);line1.add(name);
        line2.add(authorLabel);line2.add(author);
        line.add(imageLabel);line.add(image);
        line3.add(resumeLabel);line3.add(resume);
        line4.add(stateLabel);line4.add(state);
        line5.add(dateLabel);line5.add(date);
        line6.add(add);line6.add(edit);
        details.setLayout(new GridLayout(8,1));
        details.add(title);details.add(line1);details.add(line2);details.add(line);details.add(line3);details.add(line4);details.add(line5);details.add(line6);
        Container container = this.getContentPane();
        container.add(details);
        //
        if (book == null) {
            setTitle("Add");
            edit.setVisible(false);
            line4.setVisible(false);
            line5.setVisible(false);
            title.setText("ADD BOOK");
        }
        else {
            setTitle("Modify");
            title.setText("MODIFY BOOK");
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
}
