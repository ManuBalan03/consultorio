import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.ResultSetMetaData;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cit extends JPanel {
	private JTextField did;
	private JTextField dia;
	
	DefaultTableModel modelo;
	private JTable table;
	private JTable table1;
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	TableRowSorter<DefaultTableModel> sorter;
	String []info=new String[10];
	private JTable table_1;
	private JTextField idpaci;
	private JComboBox<String> comboBox;
	/**
	 * Create the panel.
	 */
	public cit() {
		setBackground(new Color(192, 192, 192));
		modelo = new DefaultTableModel();
		
		
		this.setBounds(0,0,798,705);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cita");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 35));
		lblNewLabel.setBounds(303, 29, 202, 50);
		add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Busqueda");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNombre.setBounds(42, 101, 96, 16);
		add(lblNombre);
		
		did = new JTextField();
		did.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				buscar();
			}
		});
		did.setBounds(150, 99, 116, 22);
		add(did);
		did.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Verdana", Font.BOLD, 14));
		lblFecha.setBounds(42, 139, 56, 16);
		add(lblFecha);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDia.setBounds(42, 174, 56, 16);
		add(lblDia);
		
		dia = new JTextField();
		dia.setBounds(42, 219, 116, 22);
		add(dia);
		dia.setColumns(10);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMes.setBounds(189, 174, 56, 16);
		add(lblMes);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
		comboBox.setForeground(new Color(64, 128, 128));
		comboBox.setBackground(new Color(51, 51, 51));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril ", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboBox.setBounds(189, 218, 124, 22);
		add(comboBox);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(64, 128, 128));
		btnAgregar.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnAgregar.setBackground(new Color(51, 51, 51));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					hacer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(42, 423, 97, 25);
		add(btnAgregar);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(399, 82, 358, 222);
		add(scrollPane_1);
		
		table1 = new JTable();
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Genero", "fecha de nacimiento", "consultorio", "Doctor"
			}
		));
		scrollPane_1.setViewportView(table1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(175, 313, 592, 200);
		add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IDCita", "IDPaciente", "Nombre", "Genero", "Edad", "Consultorio", "Doctor", "Cita", "Dia", "Mes"
			}
		));
		scrollPane_2.setViewportView(table_1);
		table_load2();
		
		JLabel lblId_1 = new JLabel("Id del paciente");
		lblId_1.setBounds(42, 331, 116, 16);
		add(lblId_1);
		
		idpaci = new JTextField();
		idpaci.setBounds(42, 374, 116, 22);
		add(idpaci);
		idpaci.setColumns(10);
		table_load();
	
		
	}
	public void table_load()
    {
     try
     {
    pst = cn.prepareStatement("select * from pacientes");
    rs = pst.executeQuery();
    table1.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
}
	public void buscar()
	{
		
		String dide=did.getText();
		try{
		
        
		 pst = cn.prepareStatement("select Nombre,Genero,Fecha,Consultorio,Doctor from pacientes where IDpaciente = ?");
         pst.setString(1, dide);
         
         rs = pst.executeQuery();
      
        
        // Mostrar los resultados en la consola (puedes adaptar esto a tu interfaz de usuario)
             
             

             // Crear objeto DefaultTableModel para almacenar los datos de la fila
             DefaultTableModel model = new DefaultTableModel();

             // Recuperar los datos del ResultSet y agregarlos a la tabla
             ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
             int columnCount = metaData.getColumnCount();
        

             
             model.addColumn("ID"); // Añadir la columna de posición
             for (int i = 1; i <= columnCount; i++) {
                 model.addColumn(metaData.getColumnName(i));
             }
             String pos =did.getText();// Contador para la posición de la fila
             while (rs.next()) {
                 Object[] row = new Object[columnCount + 1];
                 row[0] = pos; // Añadir la posición al primer elemento de la fila
                 for (int i = 1; i <= columnCount; i++) {
                     row[i] = rs.getObject(i);
                 }
                 model.addRow(row);
                 
                 table1.setModel(model);
             }
             if(did.getText().equals(""))
             {
            	 table_load();
             }
         

             // Cerrar la conexión
             
//cn.close();
        }
        
     catch (SQLException e) {
        System.err.println("Error al obtener la fila: " + e.getMessage());
    }
    
}
	public void hacer() throws SQLException
	{
		String id1 = idpaci.getText();
		pst = cn.prepareStatement("select Nombre,Genero,Fecha,Consultorio,Doctor from pacientes where IDpaciente = ?");
        pst.setString(1, id1);
        
        rs = pst.executeQuery();

    if(rs.next()==true)
    {	
        String name = rs.getString(1);
        String genero = rs.getString(2);
        String  fecha = rs.getString(3);
        String  consu= rs.getString(4);
        String  doctor= rs.getString(5);
        
        
        info[1]=idpaci.getText();
        info[2]=name;
        info[3]=genero;
        info[4]=fecha;
        info[5]=consu;
        info[6]=doctor;
		info[7]="Abierto";
		info[8]=dia.getText();
		info[9]=(String)comboBox.getSelectedItem();
		
	
		 try {
				pst = cn.prepareStatement("insert into cita(IDCita,IDpaciente,Nombre,Genero,Edad,Consultorio,Doctor,Cita,Dia,Mes)values(?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, info[0]);
				pst.setString(2, info[1]);
				pst.setString(3, info[2]);
				pst.setString(4, info[3]);
				pst.setString(5, info[4]);
				pst.setString(6, info[5]);
				pst.setString(7, info[6]);
				pst.setString(8, info[7]);
				pst.setString(9, info[8]);
				pst.setString(10, info[9]);
				pst.executeUpdate();
			
				
			 
				
				
				idpaci.setText("");
				dia.setText("");
				table_load2();
			   }

			catch (SQLException e1) 
		        {
								
			e1.printStackTrace();
			}
		 
	}
	}
	public void table_load2()
    {
     try
     {
    pst = cn.prepareStatement("select * from cita");
    rs = pst.executeQuery();
    table_1.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
}
}

	
