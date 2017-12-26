import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ahmed on 21.12.17.
 */
public class List extends JPanel {

    String[] columns= {"ID","Title","Author"};
    ArrayList<Book> books;
    JTable table= new JTable() {
        private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    DefaultTableModel aModel = (DefaultTableModel) table.getModel();
    JScrollPane scrollPane;

    public List() {
        super();
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createMatteBorder(10,0,10,0,new Color(56, 50, 101)));
        aModel.setColumnIdentifiers(columns);
        books = queryData(aModel);
        table.setModel(aModel);
        /*table = new JTable(data,columns) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
                jc.setBorder(BorderFactory.createMatteBorder(2,0,2,0,new Color(56, 50, 101)));
                return jc;
            }
        };*/
        table.setPreferredScrollableViewportSize(new Dimension(300,500));
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(0,0,0,1));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.setRowHeight(50);
        table.setFont(table.getFont().deriveFont(13.0f));
        table.setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(56, 50, 101));
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(16.0f));
        table.getTableHeader().setForeground(Color.WHITE);
        scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                repaint();
            }
        });
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        scrollPane.setBackground(new Color(0,0,0));
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private ArrayList<Book> queryData(DefaultTableModel aModel) {
        ResultSet resultSelect;
        ArrayList<Book> data = new ArrayList<Book>();
        Connexion cx = new Connexion();
        cx.con = cx.getConnexion();
        String req="SELECT * FROM Books";
        resultSelect= cx.requeteSelect(req);
        try {
            java.sql.ResultSetMetaData rsmd = resultSelect.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (resultSelect.next()) {
                int id = resultSelect.getInt("book_id");
                String name = resultSelect.getString("name");
                String author = resultSelect.getString("author");
                String imageUrl = resultSelect.getString("image");
                String resume = resultSelect.getString("resume");
                String state = resultSelect.getString("state");
                String date = resultSelect.getString("date");
                data.add(new Book(id,name,author,imageUrl,resume,state,date));
                Object[] objects = new Object[colNo];
                for(int i=0;i<colNo;i++){
                    objects[i]=resultSelect.getObject(i+1);
                }
                aModel.addRow(objects);
            }
        }catch (SQLException e){
            System.out.println("Null pointer exception:"+e);
        }
        return data;
    }

}
