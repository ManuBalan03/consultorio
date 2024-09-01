import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class medicinas extends JPanel {
	private JTable table;
	private JTextField nom;
	private JTextField cant;
	DefaultTableModel modelo;
	private JTextField bid;
	
	String []info=new String[6];
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	/**
	 * Create the panel.
	 */
	public medicinas() {
		setBackground(UIManager.getColor("CheckBox.darkShadow"));
		
		
		setLayout(null);
		this.setBounds(0,0,798,705);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 29, 479, 371);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "ID", "Cantidad"
			}
		));
		
		scrollPane.setViewportView(table);
		
		table_load();
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(45, 96, 56, 16);
		add(lblNombre);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(45, 224, 56, 16);
		add(lblCantidad);
		
		nom = new JTextField();
		nom.setBounds(125, 93, 116, 22);
		add(nom);
		nom.setColumns(10);
		
		JLabel lblBusqueda = new JLabel("Busqueda");
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblBusqueda.setBounds(12, 427, 84, 16);
		add(lblBusqueda);
		
		cant = new JTextField();
		cant.setBounds(125, 221, 116, 22);
		add(cant);
		cant.setColumns(10);
		
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
		
		JButton btnAgregar = new JButton("agregar");
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				agregar();
			}
		});
		btnAgregar.setBounds(76, 450, 97, 25);
		add(btnAgregar);
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				eliminar();
			}
		});
		btnEliminar.setBounds(342, 450, 97, 25);
		add(btnEliminar);
		
		JButton btnEliminarTodo = new JButton("actualizar");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			actualizar();
			}
		});
		btnEliminarTodo.setBounds(559, 450, 123, 25);
		add(btnEliminarTodo);

	}
	public void agregar()
	{
		info[0]=nom.getText();
		info[1]=cant.getText();
		
	
		 try {
				pst = cn.prepareStatement("insert into medicina(Nombre,Cantidad) values(?,?)");
				pst.setString(1, info[0]);
				pst.setString(2, info[1]);
				pst.executeUpdate();
				
			 
				
				nom.setText("");
				cant.setText("");
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
    pst = cn.prepareStatement("select * from medicina");
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
        	pst = cn.prepareStatement("delete from medicina where IDmedicina =?");
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
	public void busqueda()
	{
		
		int index = -1;
		try {
	          
            String id1 = bid.getText();
 
                pst = cn.prepareStatement("select Nombre,Cantidad from medicina where IDmedicina = ?");
                pst.setString(1, id1);
                
                rs = pst.executeQuery();
 
            if(rs.next()==true)
            {	
                String name = rs.getString(1);
                String canti = rs.getString(2);
     
                cant.setText(canti);
                nom.setText(name);
                
            }  
            else
            {
            	nom.setText("");
            	cant.setText("");
            	
            }
            
 
 
        }
catch (SQLException ex) {
          
        }
	}
	public void actualizar()
	{
		String bid1;
		
		bid1  = bid.getText();
		info[0]=nom.getText();
		info[1]=cant.getText();
		try {
		pst = cn.prepareStatement("update medicina set Nombre= ?,Cantidad=? where IDmedicina =?");
		pst.setString(1, info[0]);
		pst.setString(2, info[1]);
		pst.setString(3, bid1);
		           // pst.setString(4, bid1);
		            pst.executeUpdate();
		           
		            table_load();
		          
		            nom.setText("");
		           cant.setText("");
		            nom.requestFocus();
		}
		 
		            catch (SQLException e1) {
		e1.printStackTrace();
		}
	}
}
