

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class citatranscurso extends JPanel {
	private JTable table;
	String[] valor=new String[6];
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	JTextArea textArea;
	private JComboBox<String> comboBox;
	private JComboBox<String> en;
	private JTextField medi;
	private JTextField cant;
	private JTextField ob;
	private JTextField cita;
	private JTable table_1;
	 public String name;
	 public String genero;
	 public String edad;
	 public String consu;
	 public String doctor;
	 public String dia;
	 public String mes;
	DefaultTableModel modelo;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public citatranscurso() throws SQLException {
		this.setBounds(0,0,785,780);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 70, 740, 250);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IDcita", "IDPaciente", "Nombre", "Genero", "Edad", "Consultorio", "Doctor", "Cita", "Dia", "Mes"
			}
		));
		scrollPane.setViewportView(table);
		llenar();
		
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setBounds(26, 31, 56, 16);
		add(lblCitas);
		
		JLabel lblMedicina = new JLabel("Medicina");
		lblMedicina.setBounds(26, 363, 56, 16);
		add(lblMedicina);
		
		medi = new JTextField();
		medi.setBounds(26, 398, 116, 22);
		add(medi);
		medi.setColumns(10);
		
		cant = new JTextField();
		cant.setBounds(191, 398, 116, 22);
		add(cant);
		cant.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(190, 363, 56, 16);
		add(lblCantidad);
		
		ob = new JTextField();
		ob.setBounds(369, 398, 116, 22);
		add(ob);
		ob.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(369, 363, 116, 16);
		add(lblObservaciones);
		
		JLabel lblEnfermero = new JLabel("Enfermero");
		lblEnfermero.setBounds(551, 363, 102, 16);
		add(lblEnfermero);
		
		en = new JComboBox();
		en.setBounds(551, 398, 102, 22);
		add(en);
		enfermer();
		
		JLabel lblIdcita = new JLabel("IDCita");
		lblIdcita.setBounds(26, 334, 56, 16);
		add(lblIdcita);
		
		cita = new JTextField();
		cita.setBounds(94, 333, 116, 22);
		add(cita);
		cita.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(516, 451, 250, 200);
		add(scrollPane_1);
		
		table_1 = new JTable();
		
		modelo= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicina", "Cantidad"
			}
		);
		table_1.setModel(modelo);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String med=medi.getText();
				String ca=cant.getText();
				table_1.getModel();
				Object[] inv={med,ca};
				modelo.addRow(inv);
			}
		});
		btnAgregar.setBounds(26, 428, 97, 25);
		add(btnAgregar);
		
		JButton btnImpirmir = new JButton("Impirmir");
		btnImpirmir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hacer();
			}
		});
		btnImpirmir.setBounds(185, 433, 97, 25);
		add(btnImpirmir);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(26, 478, 459, 171);
		add(scrollPane_2);
		
		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		 enferme();
	}
	public void llenar()
	{
		try
	     {
	    pst = cn.prepareStatement("select * from cita");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	     
	  
	}
	public void enferme() throws SQLException
	{
		String id="0";
	
		pst = cn.prepareStatement("SELECT Nombre FROM enfer");
        
        
        rs = pst.executeQuery();
        ArrayList<String> valoresColumna = new ArrayList<>();

        try{
        
        while (rs.next()) {
        	String valor = rs.getString(1);
            valoresColumna.add(valor);
        }

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        System. out. println(valor[0]);

     for (String v : valoresColumna) {
         modelo.addElement(v);
     }

     // Crear el JComboBox y establecer el modelo
     comboBox = new JComboBox<>(modelo);
        
    } catch (SQLException e) {
        System.err.println("Error al obtener los valores de la columna: " + e.getMessage());
    }
	}
	public void enfermer()
	{
		enfermodelo obj=new enfermodelo();
		obj.start();
		ArrayList<enfer> llave1=obj.geten();
		en.removeAllItems();
		for(int i=0;i<llave1.size();i++)
		{
			en.addItem(llave1.get(i).getNombre());
		}
		//info[5]=(String)doc.getSelectedItem();
	}
	public void hacer()
	{
obtener();
//restar();
		modificar();
		String enf=(String)en.getSelectedItem();
		String obse=ob.getText();
		textArea.append("nombre  genero  Fecha de nacimiento" + "\n");
		textArea.append(name +"  "+genero+"  "+edad+"\n");
		textArea.append("consultorio  Doctor" + "\n");
		textArea.append(consu +"  "+doctor+"\n");
		textArea.append("FECHA" + "\n");
		textArea.append(dia +" "+mes+"\n");
		textArea.append("enfermero" + "\n");
		textArea.append(enf + "\n");
		textArea.append("medicinas" +"  cantidad"+ "\n");
		for (int i = 0; i < table_1.getRowCount(); i++){
            String valor = (String) table_1.getValueAt(i, 0);
            String valor1 = (String) table_1.getValueAt(i, 1);
            textArea.append(valor +"   "+ valor1 + "\n");
        }
		textArea.append("Observaciones" + "\n");
		textArea.append(obse + "\n");
	}
	private void restar() {
		
		String bid1;
		bid1  = cita.getText();
		String ce="cerrada";
		try {
		pst = cn.prepareStatement("update medicina set cantidad=? where IDmedicina =?");
		pst.setString(1, ce);
		          
		            pst.executeUpdate();
		           
		          
		            cita.setText("");
		           
		            cita.requestFocus();
		}
		 
		            catch (SQLException e1) {
		e1.printStackTrace();
		}
	}
	public void modificar()
	{
		String bid1;
		bid1  = cita.getText();
		String ce="cerrada";
		try {
			//"update pacientes set Nombre=?,Genero=?,Edad=?,Consultorio=?,Doctor=?,Dia=?,Mes=? where IDpaciente =?"
		pst = cn.prepareStatement("update cita set Nombre=?,Genero=?,Edad=?,Consultorio=?,Doctor=?,Cita=?,Dia=?,Mes=? where IDCita =?");
		pst.setString(1, name);
		pst.setString(2, genero);
		pst.setString(3, edad);
		pst.setString(4, consu);
		pst.setString(5, doctor);
		pst.setString(6, ce);
		pst.setString(7, dia);
		pst.setString(8, mes);
		pst.setString(9, bid1);
		            pst.executeUpdate();
		           
		            llenar();
		          
		            cita.setText("");
		           
		            cita.requestFocus();
		}
		 
		            catch (SQLException e1) {
		e1.printStackTrace();
		}
	}
	public void obtener()
	{
		int index = -1;
		try {
	          
            String id1 = cita.getText();
 
                pst = cn.prepareStatement("select Nombre,Genero,Edad,Consultorio,Doctor,Dia,Mes from cita where IDCita = ?");
                pst.setString(1, id1);
                
                rs = pst.executeQuery();
 
            if(rs.next()==true)
            {	
                name = rs.getString(1);
                genero = rs.getString(2);
                edad= rs.getString(3);
               consu= rs.getString(4);
                doctor= rs.getString(5);
                dia= rs.getString(6);
                mes= rs.getString(7);
                
       
            }  
            else
            {
            cita.setText("");
            	ob.setText("");
            	
            }
            
 
 
        }
catch (SQLException ex) {
          
        }
	}

}
