

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class pacientes extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTextField nom;
	private JTextField id;
	private JTable table;
	private JComboBox<String> gen;
	private JComboBox<String> doc;
	DefaultTableModel modelo;
	private JTextField dia;
	private JTextField consul;
	private JTextField bid;
	String []info=new String[6];
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	

	
	public pacientes() {
		setBackground(new Color(45, 45, 45));
		//datos1=tablas.getInstance();
		this.setBounds(0,0,1100,705);
		setLayout(null);

		iniciarcomponentes();
		
	}

	public void iniciarcomponentes()
	{
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(12, 34, 67, 16);
		add(lblNombre);
		
		JLabel lblId = new JLabel("Numero de afilacion");
		lblId.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(12, 74, 168, 20);
		add(lblId);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblGenero.setForeground(new Color(255, 255, 255));
		lblGenero.setBounds(12, 120, 67, 16);
		add(lblGenero);
		
		nom = new JTextField();
		nom.setBounds(191, 32, 116, 22);
		add(nom);
		nom.setColumns(10);
		
		id = new JTextField();
		id.setBounds(192, 74, 116, 22);
		add(id);
		id.setColumns(10);
		
		gen = new JComboBox();
		gen.setBackground(new Color(122, 122, 122));
		gen.setFont(new Font("Verdana", Font.PLAIN, 13));
		gen.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		gen.setBounds(191, 117, 116, 22);
		add(gen);
		
		doc = new JComboBox();
		doc.setFont(new Font("Verdana", Font.PLAIN, 13));
		doc.setBackground(new Color(122, 122, 122));
		doc.setBounds(191, 302, 116, 22);
		add(doc);
		
		llenar();
		
		dia = new JTextField();
		dia.setBounds(12, 206, 185, 22);
		add(dia);
		dia.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setForeground(Color.WHITE);
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblFechaDeNacimiento.setBounds(12, 163, 168, 16);
		add(lblFechaDeNacimiento);
		
		consul = new JTextField();
		consul.setBounds(191, 257, 116, 22);
		add(consul);
		consul.setColumns(10);
		
		JLabel lblConsultorio = new JLabel("Consultorio");
		lblConsultorio.setForeground(Color.WHITE);
		lblConsultorio.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblConsultorio.setBounds(12, 259, 84, 16);
		add(lblConsultorio);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setForeground(Color.WHITE);
		lblDoctor.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDoctor.setBounds(12, 302, 84, 16);
		add(lblDoctor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 58, 721, 367);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Genero", "fecha de nacimiento", "consultorio", "Doctor"
			}
		));
		scrollPane.setViewportView(table);
		table_load();
		hacer();
		
	}

	public void hacer()
	{
		
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
				String bid1;
				bid1  = bid.getText();
                try {
                	pst = cn.prepareStatement("delete from pacientes where IDpaciente =?");
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
		});
		btnEiminar.setBounds(444, 476, 126, 25);
		add(btnEiminar);
		
		JButton actu = new JButton("actualizar");
		actu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String d=dia.getText();
				String bid1;
				bid1  = bid.getText();
				String []info=new String[6];
				info[0]=id.getText();
				info[1]=nom.getText();
				info[2]=(String)gen.getSelectedItem();
				info[3]=d;
				info[4]=consul.getText();
				info[5]=(String)doc.getSelectedItem();
				try {
				pst = cn.prepareStatement("update pacientes set Nombre= ?,Genero=?,Fecha=?,Consultorio=?,Doctor=? where IDpaciente =?");
				pst.setString(1, info[1]);
				pst.setString(2, info[2]);
				pst.setString(3, info[3]);
				pst.setString(4, info[4]);
				pst.setString(5, info[5]);
				pst.setString(6, info[0]);
				            pst.executeUpdate();
				           
				            table_load();
				          
				            nom.setText("");
				            id.setText("");				
				            consul.setText("");
				            dia.setText("");
				            nom.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		actu.setForeground(Color.WHITE);
		actu.setBackground(new Color(74, 185, 166));
		actu.setBounds(777, 476, 126, 25);
		add(actu);
		
		bid = new JTextField();
		bid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				busqueda();
			}
		});
		bid.setBounds(191, 424, 116, 22);
		add(bid);
		bid.setColumns(10);
		
		JLabel lblBusqueda = new JLabel("Busqueda");
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblBusqueda.setBounds(12, 427, 84, 16);
		add(lblBusqueda);
		
		
	}
	public void llenar()
	{
		docmodelo obj=new docmodelo();
		ArrayList<doctores> llave=obj.getdoc();
		doc.removeAllItems();
		for(int i=0;i<llave.size();i++)
		{
			doc.addItem(llave.get(i).getNombre());
		}
		//info[5]=(String)doc.getSelectedItem();
	}
	public void agregar()
	{
		
		String d=dia.getText();
	
		
		//System.out.println(info[5]);
		info[0]=id.getText();
		info[1]=nom.getText();
		info[2]=(String)gen.getSelectedItem();
		info[3]=d;
		info[4]=consul.getText();
		info[5]=(String)doc.getSelectedItem();
		
	
		 try {
				pst = cn.prepareStatement("insert into pacientes(Nombre,Genero,Fecha,Consultorio,Doctor,N_afilacion)values(?,?,?,?,?,?)");
				
				pst.setString(1, info[1]);
				pst.setString(2, info[2]);
				pst.setString(3, info[3]);
				pst.setString(4, info[4]);
				pst.setString(5, info[5]);
				pst.setString(6, info[0]);
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
    pst = cn.prepareStatement("select * from pacientes");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
     
  
     }
	public void busqueda()
	{
		
		int index = -1;
		try {
	          
            String id1 = bid.getText();
 
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
                
             
                id.setText(id1);
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) gen.getModel();
                model.addElement(genero);
                gen.setSelectedItem(genero);
        		HashSet<Object> hashSet = new HashSet<Object>();
        		for (int i = 0; i < model.getSize(); i++) {
        		    hashSet.add(model.getElementAt(i));
        		}
        		model.removeAllElements();
        		for (Object elemento : hashSet) {
        		    model.addElement((String) elemento);
        		}
        		for (int i = 0; i < gen.getItemCount(); i++) {
        		    if (genero.equals(gen.getItemAt(i))) {
        		        index = i; 
        		        break;
        		    }
        		}
        		gen.setSelectedIndex(index);
        		
        		DefaultComboBoxModel<String>  model1 = (DefaultComboBoxModel<String>) doc.getModel();
                model1.addElement(doctor);
                doc.setSelectedItem(doctor);
        		HashSet<Object> hashSet1 = new HashSet<Object>();
        		for (int i = 0; i < model1.getSize(); i++) {
        		    hashSet1.add(model1.getElementAt(i));
        		}
        		model1.removeAllElements();
        		for (Object elemento : hashSet1) {

        		    model1.addElement((String) elemento);
        		    
        		}
        		for (int i = 0; i < doc.getItemCount(); i++) {
        		    if (doctor.equals(doc.getItemAt(i))) {
        		        index = i; 
        		        break; 
        		    }
        		}
        		
        		doc.setSelectedIndex(index);
        		nom.setText(name);
                consul.setText(consu);
                dia.setText(fecha);
            }  
            else
            {
            	nom.setText("");
            	dia.setText("");
            	consul.setText("");
            	id.setText("");
            	
            }
            
 
 
        }
catch (SQLException ex) {
          
        }
        
	}
    
}
