

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.table.DefaultTableModel;

public class citatranscurso extends JPanel {
	private JTable table;
	String[] valor=new String[6];
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	JTextArea ob;
	private JComboBox<String> comboBox;
	private JComboBox<String> en;
	private JTextField medi;
	private JTextField cant;
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
	private JTextField textmedicinas;
	private JTextField NombrePaciente;
	private JTextField idpaciente;
	JTextArea textArea_1;
	private JTable tableMedicines;
	private DefaultTableModel modelMedicines;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public citatranscurso() throws SQLException {
		this.setBounds(0,0,785,780);
		setLayout(null);
		
		
		modelMedicines = new DefaultTableModel(new Object[][] {}, new String[] { "Medicina", "Cantidad","Observaciones" });
		tableMedicines = new JTable(modelMedicines);
		JScrollPane scrollPaneMedicines = new JScrollPane(tableMedicines);
		scrollPaneMedicines.setBounds(558, 567, 208, 120);
		add(scrollPaneMedicines);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(558, 322, 208, 200);
		add(scrollPane_1);
		
		table_1 = new JTable();
		
		modelo= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicina", "Cantidad"
			}
		);
		scrollPane_1.setViewportView(table_1);
		
		llenar_medicina();
		table_1.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedRow = table_1.getSelectedRow();
		        if (selectedRow != -1) {
		            String medicina = table_1.getValueAt(selectedRow, 0).toString();
		            String cantidad = table_1.getValueAt(selectedRow, 1).toString();

		            medi.setText(medicina);
		            cant.setText(cantidad);
		        }
		    }
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 70, 740, 120);
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
		
		table.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            String idCita = table.getValueAt(selectedRow, 0).toString();
		            String idPaciente = table.getValueAt(selectedRow, 1).toString();
		            String nombrePaciente = table.getValueAt(selectedRow, 2).toString();

		            cita.setText(idCita);
		            idpaciente.setText(idPaciente);
		            NombrePaciente.setText(nombrePaciente);
		        }
		    }
		});
		
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setBounds(26, 31, 56, 16);
		add(lblCitas);
		
		JLabel lblMedicina = new JLabel("Medicina");
		lblMedicina.setBounds(26, 241, 56, 16);
		add(lblMedicina);
		
		medi = new JTextField();
		medi.setBounds(26, 283, 116, 22);
		add(medi);
		medi.setColumns(10);
		
		cant = new JTextField();
		cant.setBounds(168, 283, 116, 22);
		add(cant);
		cant.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(168, 241, 56, 16);
		add(lblCantidad);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(340, 241, 116, 16);
		add(lblObservaciones);
		
		JLabel lblEnfermero = new JLabel("Enfermero");
		lblEnfermero.setBounds(164, 322, 102, 16);
		add(lblEnfermero);
		
		en = new JComboBox();
		en.setBounds(174, 351, 102, 22);
		add(en);
		enfermer();
		
		JLabel lblIdcita = new JLabel("IDCita");
		lblIdcita.setBounds(26, 206, 56, 16);
		add(lblIdcita);
		
		cita = new JTextField();
		cita.setBounds(88, 203, 116, 22);
		add(cita);
		cita.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String med = medi.getText();
                String ca = cant.getText();
                String obs = textArea_1.getText(); // Obtener observaciones del JTextArea
                modelMedicines.addRow(new Object[]{med, ca, obs});
                medi.setText(""); // Limpiar el campo de medicamento
                cant.setText(""); // Limpiar el campo de cantidad
                textArea_1.setText(""); // Limpiar el JTextArea de observaciones
			}
		});
		btnAgregar.setBounds(26, 318, 97, 25);
		add(btnAgregar);
		
		
		
		
		JButton btnImpirmir = new JButton("Impirmir");
		btnImpirmir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hacer();
			}
		});
		btnImpirmir.setBounds(26, 606, 97, 25);
		add(btnImpirmir);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(26, 411, 459, 171);
		add(scrollPane_2);
		
		ob = new JTextArea();
		scrollPane_2.setViewportView(ob);
		
		textmedicinas = new JTextField();
		 textmedicinas.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyReleased(KeyEvent e) {
		            String searchQuery = textmedicinas.getText();
		            buscarMedicinas(searchQuery);
		        }
		    });
		textmedicinas.setBounds(558, 287, 116, 22);
		add(textmedicinas);
		textmedicinas.setColumns(10);
		
		JLabel lblMedicinas = new JLabel("Buscar medicinas");
		lblMedicinas.setBounds(558, 263, 170, 16);
		add(lblMedicinas);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(296, 282, 237, 103);
		add(scrollPane_3);
		
		textArea_1 = new JTextArea();
		scrollPane_3.setViewportView(textArea_1);
		
		JButton btnVerCitasCerradas = new JButton("Ver Citas cerradas");
		btnVerCitasCerradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("Citas Cerradas");
		        frame.setSize(800, 600);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		        // Crear el panel y agregarlo a la ventana
		        CitasCerradasPanel panel = new CitasCerradasPanel(cn);
		        frame.add(panel);
		        frame.setVisible(true);
			}
		});
		btnVerCitasCerradas.setBounds(153, 606, 164, 25);
		add(btnVerCitasCerradas);
		
		NombrePaciente = new JTextField();
		NombrePaciente.setColumns(10);
		NombrePaciente.setBounds(270, 203, 116, 22);
		add(NombrePaciente);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(216, 203, 56, 16);
		add(lblNombre);
		
		JLabel lblIdPaciente = new JLabel("id Paciente");
		lblIdPaciente.setBounds(398, 203, 83, 16);
		add(lblIdPaciente);
		
		idpaciente = new JTextField();
		idpaciente.setColumns(10);
		idpaciente.setBounds(476, 203, 116, 22);
		add(idpaciente);
		
		JLabel lblMedicinasAgregadas = new JLabel("medicinas Agregadas");
		lblMedicinasAgregadas.setBounds(558, 535, 170, 16);
		add(lblMedicinasAgregadas);
		 enferme();
	}
	

	public void llenar() {
	    try {
	        // Consulta para obtener solo las citas abiertas
	        pst = cn.prepareStatement("SELECT IDCita, IDPaciente, Nombre, Genero, Edad, Consultorio, Doctor, Cita, Dia, Mes FROM cita WHERE Cita = 'abierto'");
	        rs = pst.executeQuery();

	        // Crea un modelo de tabla con las columnas requeridas
	        DefaultTableModel model = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "IDCita", "IDPaciente", "Nombre", "Genero", "Edad", "Consultorio", "Doctor", "Cita", "Dia", "Mes"
	            }
	        );

	        // Formato de fecha esperado: dd/MM/yyyy
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	        // Llena la tabla con los datos y calcula la edad
	        while (rs.next()) {
	            String idCita = rs.getString("IDCita");
	            String idPaciente = rs.getString("IDPaciente");
	            String nombre = rs.getString("Nombre");
	            String genero = rs.getString("Genero");
	            String fechaNacimiento = rs.getString("Edad"); // Asume que esta es la fecha de nacimiento
	            String consultorio = rs.getString("Consultorio");
	            String doctor = rs.getString("Doctor");
	            String cita = rs.getString("Cita");
	            String dia = rs.getString("Dia");
	            String mes = rs.getString("Mes");

	            // Calcula la edad
	            int edad = calcularEdad(fechaNacimiento, formatter);

	            // Agrega una fila con los datos
	            model.addRow(new Object[]{idCita, idPaciente, nombre, genero, edad, consultorio, doctor, cita, dia, mes});
	        }

	        // Asigna el modelo a la tabla
	        table.setModel(model);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Método para calcular la edad
	public int calcularEdad(String fechaNacimiento, DateTimeFormatter formatter) {
	    try {
	        LocalDate birthDate = LocalDate.parse(fechaNacimiento, formatter);
	        LocalDate currentDate = LocalDate.now();
	        return Period.between(birthDate, currentDate).getYears();
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return -1; // Valor de error o manejo alternativo
	    }
	}

		public void llenar_medicina()
		{
			try
		     {
		    pst = cn.prepareStatement("SELECT * FROM medicina");
		    rs = pst.executeQuery();
		    table_1.setModel(DbUtils.resultSetToTableModel(rs));
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
	public void hacer() {
	    obtener();
	    modificar();

	    String enf = (String) en.getSelectedItem();
	    ob.setText(""); // Limpiar el contenido antes de agregar el nuevo
	    ob.append("Datos del Paciente:\n");
	    ob.append("Nombre: " + name + "\n");
	    ob.append("Género: " + genero + "\n");
	    ob.append("Edad: " + edad + "\n\n");

	    ob.append("Información de la Cita:\n");
	    ob.append("Consultorio: " + consu + "\n");
	    ob.append("Doctor: " + doctor + "\n");
	    ob.append("Fecha de la Cita: " + dia + " " + mes + "\n\n");

	    ob.append("Enfermero(a): " + enf + "\n\n");

	    ob.append("Medicinas Recetadas:\n");
	    ob.append(String.format("%-20s %-10s %-20s\n", "Medicina", "Cantidad", "Observaciones"));
	    for (int i = 0; i < modelMedicines.getRowCount(); i++) {
	        String medicina = modelMedicines.getValueAt(i, 0).toString();
	        String cantidad = modelMedicines.getValueAt(i, 1).toString();
	        String observaciones = modelMedicines.getValueAt(i, 2).toString();
	        ob.append(String.format("%-20s %-10s %-20s\n", medicina, cantidad, observaciones));

	        // Actualizar la base de datos para restar la cantidad
	        restarCantidad(medicina, Integer.parseInt(cantidad));
	    }

	    modelMedicines.setRowCount(0); // Limpiar el modelo de medicinas
	    llenar_medicina(); // Recargar las medicinas en la tabla
	}

	public void restarCantidad(String medicina, int cantidadRestar) {
	    try {
	        String sql = "UPDATE medicina SET Cantidad = Cantidad - ? WHERE Nombre = ?";
	        pst = cn.prepareStatement(sql);
	        pst.setInt(1, cantidadRestar);
	        pst.setString(2, medicina);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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


	public void buscarMedicinas(String searchQuery) {
	    try {
	        // Prepara la consulta SQL que busca por ID o Nombre
	        String sql = "SELECT IDMedicina, Nombre, Cantidad FROM medicina WHERE IDMedicina LIKE ? OR Nombre LIKE ?";
	        pst = cn.prepareStatement(sql);
	        pst.setString(1, "%" + searchQuery + "%");
	        pst.setString(2, "%" + searchQuery + "%");
	        
	        // Ejecuta la consulta y actualiza la tabla
	        rs = pst.executeQuery();
	        table_1.setModel(DbUtils.resultSetToTableModel(rs));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
