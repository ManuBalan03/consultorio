
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class enferme extends JPanel {
	private JTextField nom;
	private JTable table;
	private JComboBox<String> espe;
	private JComboBox<String> gen;
	private JComboBox<String> turn;
	DefaultTableModel modelo;
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	private JTextField bid;
	/**
	 * Create the panel.
	 */
	public enferme() {
		setBackground(new Color(45, 45, 45));
		
		this.setBounds(0,0,1100,705);
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(34, 55, 67, 16);
		add(lblNombre);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblGenero.setForeground(new Color(255, 255, 255));
		lblGenero.setBounds(34, 165, 67, 16);
		add(lblGenero);
		
		JLabel lblOcupacion = new JLabel("Ocupacion:");
		lblOcupacion.setForeground(new Color(255, 255, 255));
		lblOcupacion.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblOcupacion.setBounds(34, 226, 84, 16);
		add(lblOcupacion);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTurno.setForeground(new Color(255, 255, 255));
		lblTurno.setBounds(45, 339, 56, 16);
		add(lblTurno);
		
		nom = new JTextField();
		nom.setBounds(127, 55, 116, 22);
		add(nom);
		nom.setColumns(10);
		
		gen = new JComboBox();
		gen.setBackground(new Color(122, 122, 122));
		gen.setFont(new Font("Verdana", Font.PLAIN, 13));
		gen.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		gen.setBounds(127, 164, 116, 22);
		add(gen);
		
		espe = new JComboBox();
		espe.setBackground(new Color(122, 122, 122));
		espe.setFont(new Font("Verdana", Font.PLAIN, 13));
		espe.setModel(new DefaultComboBoxModel(new String[] {"De piso"}));
		espe.setBounds(127, 225, 116, 22);
		add(espe);
		
		JButton btnAgregar = new JButton("agregar");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBackground(new Color(74, 185, 166));
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
		btnAgregar.setBounds(127, 275, 116, 25);
		add(btnAgregar);
		
		turn = new JComboBox();
		turn.setFont(new Font("Verdana", Font.PLAIN, 13));
		turn.setModel(new DefaultComboBoxModel(new String[] {"Matutino", "Vespertino", "Nocturno"}));
		turn.setBounds(127, 338, 116, 22);
		add(turn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 57, 396, 367);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Turno", "Nombre", "ID", "Genero", "Ocupacion"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		table_load();
		
		JButton btnAgregar_1 = new JButton("agregar");
		btnAgregar_1.setForeground(new Color(255, 255, 255));
		btnAgregar_1.setBackground(new Color(74, 185, 166));
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				agregar();
				
			}
		});
		btnAgregar_1.setBounds(93, 476, 126, 25);
		add(btnAgregar_1);
		
		JButton btnEiminar = new JButton("Eliminar");
		btnEiminar.setBackground(new Color(74, 185, 166));
		btnEiminar.setForeground(new Color(255, 255, 255));
		btnEiminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				eliminar();
			}
		});
		btnEiminar.setBounds(327, 476, 126, 25);
		add(btnEiminar);
		
		JButton btnEliminarTodo = new JButton("modificar");
		btnEliminarTodo.setForeground(new Color(255, 255, 255));
		btnEliminarTodo.setBackground(new Color(74, 185, 166));
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				mod();
			}
		});
		btnEliminarTodo.setBounds(555, 476, 126, 25);
		add(btnEliminarTodo);
		
		bid = new JTextField();
		bid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				buscar();
			}
		});
		bid.setBounds(127, 401, 116, 22);
		add(bid);
		bid.setColumns(10);
		
		JLabel lblBuscar = new JLabel("buscar");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblBuscar.setBounds(45, 404, 56, 16);
		add(lblBuscar);
	}
	public void agregar()
	{
		String []info=new String[5];
		info[0]=nom.getText();
		info[1]=(String)gen.getSelectedItem();
		info[2]=(String)espe.getSelectedItem();	;
		info[4]=(String)turn.getSelectedItem();	
		 try {
				pst = cn.prepareStatement("insert into enfer(Nombre,Turno,Genero,Especialidad)values(?,?,?,?)");
				pst.setString(1, info[0]);
				pst.setString(2, info[4]);
				pst.setString(3, info[1]);
				pst.setString(4, info[2]);
				
				pst.executeUpdate();
				
			 
				
				nom.setText("");
				nom.requestFocus();
				table_load();
			   }

			catch (SQLException e1) 
		        {
								
			e1.printStackTrace();
			}
	}
	public void table_load()
    {
     try
     {
    pst = cn.prepareStatement("select * from enfer");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
     
  
     }
	public void eliminar()
	{
		String bid1;
		bid1  = bid.getText();
        try {
        	pst = cn.prepareStatement("delete from enfer where IDenfer =?");
        	pst.setString(1, bid1);
        	pst.executeUpdate();
        	
        	table_load();

        	nom.setText("");
        	nom.requestFocus();
}

catch (SQLException e1) {
e1.printStackTrace();
}

	}
	public void mod()
	{
		String bid1;
		bid1  = bid.getText();
		String []info=new String[5];
		info[0]=nom.getText();
		info[1]=(String)gen.getSelectedItem();
		info[2]=(String)espe.getSelectedItem();	
		info[4]=(String)turn.getSelectedItem();
		try {
		pst = cn.prepareStatement("update enfer set Nombre= ?,Turno=?,Genero=?,Especialidad=? where IDenfer =?");
					pst.setString(1, info[0]);
		            pst.setString(2, info[4]);
		            pst.setString(3, info[1]);
		            pst.setString(4, info[2]);
		            pst.setString(5, bid1);
		            pst.executeUpdate();
		            table_load();
		          
		            nom.setText("");
		            nom.requestFocus();
		}
		 
		            catch (SQLException e1) {
		e1.printStackTrace();
		}
	}
	public void buscar()
	{
		int index = -1;
		try {
	          
            String id1 = bid.getText();
 
                pst = cn.prepareStatement("select Nombre,Turno,Genero,Especialidad from enfer where IDenfer = ?");
                pst.setString(1, id1);
                
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
        		        index = i; // Se encuentra una coincidencia, se guarda el índice
        		        break; // Se detiene el bucle
        		    }
        		}
        		//Object elemento = espe.getItemAt(index);

        		// Eliminamos el elemento de la posición 2
        		//espe.removeItemAt(index);

        		// Insertamos el elemento en la posición 0
        		//espe.insertItemAt((String) elemento, 0);

        		// Seleccionamos el elemento recién movido
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
