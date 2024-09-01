import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class medicin extends JPanel {
	private JTable table;
	private JTextField nom;
	private JTextField bid;
	private JComboBox<String> espe;
	private JComboBox<String> gen;
	private JComboBox<String> turn;
	String[] Titulos = {"ID", "NOMBRE", "Turno", "Genero", "especialidad"};
	DefaultTableModel modelo;
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	String[][] M_datos;
	private JTextField textField;
	
	//tablas datos2;
	/**
	 * Create the panel.
	 */
	public medicin() {
		setBackground(new Color(95, 95, 95)); 
		//datos2=tablas.getInstance();
		setLayout(null);
		this.setBounds(0,0,1100,705);

		iniciarcomponentes();
		
		

	}
	public void iniciarcomponentes()
	{
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 13, 507, 410);
		add(scrollPane);			
		modelo = new DefaultTableModel();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Turno", "Genero", "Especialidad"
			}
			
		){Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}});
	
		//table.setModel(datos2.getModel());
		scrollPane.setViewportView(table);
		
		table_load();
		
		nom = new JTextField();
		nom.setBounds(100, 57, 116, 22);
		add(nom);
		nom.setColumns(10);
		
		bid = new JTextField();
		bid.setBounds(100, 57, 116, 22);
		add(bid);
		bid.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNombre.setBounds(12, 60, 56, 16);
		add(lblNombre);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setForeground(new Color(255, 255, 255));
		lblTurno.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTurno.setBounds(12, 152, 56, 16);
		add(lblTurno);
		
		turn = new JComboBox();
		turn.setFont(new Font("Verdana", Font.PLAIN, 13));
		turn.setModel(new DefaultComboBoxModel(new String[] {"Vespertino", "Matutino", "Nocturno"}));
		turn.setBounds(100, 149, 116, 22);
		add(turn);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setForeground(new Color(255, 255, 255));
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblGenero.setBounds(12, 207, 56, 16);
		add(lblGenero);
		
		gen = new JComboBox();
		gen.setFont(new Font("Verdana", Font.PLAIN, 13));
		gen.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		gen.setBounds(100, 204, 116, 22);
		add(gen);
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setForeground(new Color(255, 255, 255));
		lblEspecialidad.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblEspecialidad.setBounds(12, 258, 82, 16);
		add(lblEspecialidad);
		
		espe = new JComboBox();
		espe.setFont(new Font("Verdana", Font.PLAIN, 13));
		espe.setModel(new DefaultComboBoxModel(new String[] {"Consultorio"}));
		espe.setBounds(100, 255, 116, 22);
		add(espe);
		
		JLabel bid = new JLabel("Busqueda");
		bid.setForeground(Color.WHITE);
		bid.setFont(new Font("Verdana", Font.PLAIN, 14));
		bid.setBounds(27, 408, 91, 16);
		add(bid);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				busqueda ();
			}
		});
		textField.setBounds(127, 402, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		hacer();
	}
	public void hacer()
	{
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(94, 183, 174));
		btnAgregar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String espes = JOptionPane.showInputDialog(btnAgregar, "Introduzca la ocupacion", "ocupacion", JOptionPane.QUESTION_MESSAGE );

				if( espes != null )
				{
					espe.addItem(espes);
				}
			}
		});
		btnAgregar.setBounds(100, 298, 97, 25);
		add(btnAgregar);
		
		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnAgregar_1.setForeground(new Color(255, 255, 255));
		btnAgregar_1.setBackground(new Color(94, 183, 174));
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String []info=new String[5];
				info[0]=nom.getText();
				info[1]=(String)gen.getSelectedItem();
				info[2]=(String)espe.getSelectedItem();	
				info[4]=(String)turn.getSelectedItem();	
				 try {
						pst = cn.prepareStatement("insert into doctores(Nombre,Turno,Genero,Especialidad)values(?,?,?,?)");
						pst.setString(1, info[0]);
						pst.setString(2, info[4]);
						pst.setString(3, info[1]);
						pst.setString(4, info[2]);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "agrgado");
						
					 
						
						nom.setText("");
						nom.requestFocus();
						table_load();
					   }

					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			
			}
		});
		btnAgregar_1.setBounds(100, 445, 149, 25);
		add(btnAgregar_1);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(94, 183, 174));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String bid;
				bid  = textField.getText();
                try {
                	pst = cn.prepareStatement("delete from doctores where IDDoctor =?");
                	pst.setString(1, bid);
                	pst.executeUpdate();
                	JOptionPane.showMessageDialog(null, "eliminado");
                	table_load();
  
                	nom.setText("");
                	nom.requestFocus();
}

    catch (SQLException e1) {
e1.printStackTrace();
}

			}
		});
		btnEliminar.setBounds(348, 445, 149, 25);
		add(btnEliminar);
		
		JButton btnEliminarTodo = new JButton("Modificar");
		btnEliminarTodo.setForeground(new Color(255, 255, 255));
		btnEliminarTodo.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminarTodo.setBackground(new Color(94, 183, 174));
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String bid;
				bid  = textField.getText();
				String []info=new String[5];
				info[0]=nom.getText();
				info[1]=(String)gen.getSelectedItem();
				info[2]=(String)espe.getSelectedItem();	
				info[4]=(String)turn.getSelectedItem();
				try {
				pst = cn.prepareStatement("update doctores set Nombre= ?,Turno=?,Genero=?,Especialidad=? where IDDoctor =?");
				pst.setString(1, info[0]);
				            pst.setString(2, info[4]);
				            pst.setString(3, info[1]);
				            pst.setString(4, info[2]);
				            pst.setString(5, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "actualizado");
				            table_load();
				          
				            nom.setText("");
				            nom.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnEliminarTodo.setBounds(586, 445, 149, 25);
		add(btnEliminarTodo);
	}
	public void table_load()
    {
     try
     {
    pst = cn.prepareStatement("select * from doctores");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	public void busqueda ()
	{
		int index = -1;
		try {
	          
            String id = textField.getText();
 
                pst = cn.prepareStatement("select Nombre,Turno,Genero,Especialidad from doctores where IDDoctor = ?");
                pst.setString(1, id);
                
                ResultSet rs = pst.executeQuery();
 
            if(rs.next()==true)
            {	
                String name = rs.getString(1);
                String turno = rs.getString(2);
                String  genero= rs.getString(3);
                String  especi= rs.getString(4);
                nom.setText(name);
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) espe.getModel();
                model.addElement(especi);
                espe.setSelectedItem(especi);
        		// Crear un HashSet para almacenar los elementos únicos
        		HashSet<Object> hashSet = new HashSet<Object>();

        		// Iterar a través de los elementos del modelo del JComboBox y agregarlos al HashSet
        		for (int i = 0; i < model.getSize(); i++) {
        		    hashSet.add(model.getElementAt(i));
        		}

        		// Limpiar el modelo del JComboBox
        		model.removeAllElements();

        		// Agregar los elementos únicos al modelo del JComboBox
        		for (Object elemento : hashSet) {

        		    model.addElement((String) elemento);
        		    
        		}
        		for (int i = 0; i < espe.getItemCount(); i++) {
        		    if (especi.equals(espe.getItemAt(i))) {
        		        index = i;
        		        break; 
        		    }
        		}
        
        		espe.setSelectedIndex(index);
        		
                DefaultComboBoxModel<String> model1 = (DefaultComboBoxModel<String>) turn.getModel();
                model1.addElement(turno);
                turn.setSelectedItem(turno);
        		HashSet<Object> hashSet1 = new HashSet<Object>();
        		for (int i = 0; i < model1.getSize(); i++) {
        		    hashSet1.add(model1.getElementAt(i));
        		}
        		model1.removeAllElements();
        		for (Object elemento : hashSet1) {

        		    model1.addElement((String) elemento);
        		    
        		}
        		for (int i = 0; i < turn.getItemCount(); i++) {
        		    if (turno.equals(turn.getItemAt(i))) {
        		        index = i; 
        		        break; 
        		    }
        		}
        		
        		turn.setSelectedIndex(index);
        		
                DefaultComboBoxModel<String> model2 = (DefaultComboBoxModel<String>) gen.getModel();
                model2.addElement(genero);
                gen.setSelectedItem(genero);
                HashSet<Object> hashSet11 = new HashSet<Object>();
        		for (int i = 0; i < model2.getSize(); i++) {
        		    hashSet11.add(model2.getElementAt(i));
        		}
        		model2.removeAllElements();
        		for (Object elemento : hashSet11) {

        		    model2.addElement((String) elemento);
        		    
        		}
        		for (int i = 0; i < gen.getItemCount(); i++) {
        		    if (genero.equals(gen.getItemAt(i))) {
        		        index = i; 
        		        break; 
        		    }
        		}
        		gen.setSelectedIndex(index);
            }  
            else
            {
            	nom.setText("");
            	
            }
            
 
 
        }
catch (SQLException ex) {
          
        }
	}
}
