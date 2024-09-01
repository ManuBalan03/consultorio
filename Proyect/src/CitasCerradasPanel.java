import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;

public class CitasCerradasPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private Connection cn;
    private DefaultTableModel model;

    public CitasCerradasPanel(Connection connection) {
        this.cn = connection;
        setLayout(null);
        setBounds(0, 0, 800, 600);

        // Crear el modelo y la tabla
        model = new DefaultTableModel(new Object[][] {}, new String[] {
            "IDCita", "IDPaciente", "Nombre", "Genero", "Edad", "Consultorio", "Doctor", "Cita", "Dia", "Mes"
        });
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 760, 400);
        add(scrollPane);

        // Campo de búsqueda
        searchField = new JTextField();
        searchField.setBounds(20, 20, 200, 30);
        add(searchField);

        // Agregar KeyListener al campo de búsqueda
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarCitas(searchField.getText());
            }
        });

        // Inicializar la tabla con citas cerradas
        cargarCitasCerradas();
    }

    private void cargarCitasCerradas() {
        try {
            String sql = "SELECT IDCita, IDPaciente, Nombre, Genero, Edad, Consultorio, Doctor, Cita, Dia, Mes FROM cita WHERE Cita = 'cerrada'";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void buscarCitas(String searchQuery) {
        try {
            String sql = "SELECT IDCita, IDPaciente, Nombre, Genero, Edad, Consultorio, Doctor, Cita, Dia, Mes FROM cita WHERE (IDCita LIKE ? OR Nombre LIKE ?) AND Cita = 'cerrada'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + searchQuery + "%");
            pst.setString(2, "%" + searchQuery + "%");
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
