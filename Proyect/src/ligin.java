import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class ligin {

	private JFrame frame;
	private JTextField t1;
	private JPasswordField t2;
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;

	public ligin() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ligin.class.getResource("/imagenes/png-transparent-imss-hd-logo.png")));
		frame.setBackground(new Color(51, 49, 49));
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(51, 49, 49));
		frame.setBounds(100, 100, 867, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 35, 35));
		panel.setBounds(241, 42, 370, 405);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLogin.setBounds(81, 323, 207, 31);
		panel.add(btnLogin);
		btnLogin.setBackground(new Color(0, 0, 160));
		
		t2 = new JPasswordField();
		t2.setBounds(151, 215, 137, 22);
		panel.add(t2);
		
		t1 = new JTextField();
		t1.setBounds(151, 155, 137, 22);
		panel.add(t1);
		t1.setColumns(10);
		
		
		
		JLabel lblLoging = new JLabel("Login");
		lblLoging.setForeground(new Color(255, 255, 255));
		lblLoging.setBackground(new Color(255, 255, 255));
		lblLoging.setBounds(129, 60, 122, 54);
		panel.add(lblLoging);
		lblLoging.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(66, 148, 73, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(231, 231, 231));
		
		JLabel lblNewLabel_1 = new JLabel("ContraseÃ±a:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(52, 217, 89, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Polygon 1 (1).png")));
		lblNewLabel_3.setBounds(680, 295, 127, 152);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Polygon 1 (1).png")));
		lblNewLabel_4.setBounds(665, 352, 127, 137);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(629, 274, 202, 215);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Polygon 1 (1).png")));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(631, 0, 222, 176);
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Subtract.png")));
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(0, 40, 171, 449);
		frame.getContentPane().add(lblNewLabel_7);
		lblNewLabel_7.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Ellipse 1.png")));
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(157, 84, 74, 61);
		frame.getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setIcon(new ImageIcon(ligin.class.getResource("/imagenes/Ellipse 2.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				hacer();//aqui inicia el metodo se hace esto para que sea mas ordenado el codigo
				
			}
		});
		
		frame.setVisible(true);
		
	}
	public void hacer()//esto es un metodo no devuelve nada solo hace un proceso
	{
		//String []cons=new String[4];
		//String []names=new String[4];
		//
		List<String> columna1List = new ArrayList<>();//lista para guardar datos es como un areglo
        List<String> columna2List = new ArrayList<>();
		
		try {
	   
				
                pst = cn.prepareStatement("SELECT * FROM usuarios");//selecionas la tabla usuarios SOLO CAMBIA USUARIOS
                rs = pst.executeQuery();
                
                while (rs.next()) {//todos los datos de la tabla 
                	
                	//pst = cn.prepareStatement("select Nombre,Genero,Fecha,Consultorio,Doctor from pacientes where IDpaciente = ?"); nombre hasta doctor son tus 
                	//columnas from pacientes(es tu tabla) where idpaciente (es el dato por el cual selecionaras la fila es u  dato unico)
                    //pst.setString(1, id1); el 1 va de ley el id1 es el dato que selecionara puede ser un string o int es una variable lo pueder extraer de un textdfield
                    
                    //rs = pst.executeQuery();//ejecuta el proceso
                	//pero si usas if (rs.next()) hagarras una fila preseleccionada 
                    // Obtener los valores de las columnas
                    String names = rs.getString(1);//el names son los nombres ponlo como quieras el rs.getString te esta indicando que obtiene los datos de la columna 1
                    String cons = rs.getString(2);//lo mismo con las 2 hasta que todas las finas se añadan
            
                    columna1List.add(names);//aqui los datos de las variables names se guardan en la lista columna1list
                    columna2List.add(cons);
                    
                 
                }
                String[] nom = columna1List.toArray(new String[4]);//loconvierto en arreglo para asi usar los datos guardados por separado
                String[] con = columna2List.toArray(new String[4]);//el new String[4] dice cuantas posiciones hay en este caso hay 3 la n 4 no existe si quieres que hayan 
                // 4 tienes que poner 5
                //apartir de aqui uso los datos de mi base de datos para comparar con mis variables de mi login 
                String name=t1.getText();//obtengo los datos de i textfield
        		String contra=t2.getText();
        		
        		if(name.equals(nom[0]))//el .equals sirve para comparar dos cadenas o string aqui le digo que compare lo que me pusieron en mi textefield con el arreglo posicion 0
        			// recuerda que el areglo se ve los datos por posiciones no puedes hacer esto nom[] es asi nom[0] es la posicion 1 siempre empieza con 0
        				{
        			if(name.equals(nom[0])&&contra.equals(con[0]))//comparo que sea lo mismo si es asi entra 
        			{
        				JOptionPane.showMessageDialog(frame,"Bienvenido (accediendo como ADMIN)");
        				menu vent=new menu();
        				frame.dispose();
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(frame,"contraseña o usuario incorectos");
        			}
        				}
        		
        		if(name.isEmpty())//si esta vacio
        		{
        			JOptionPane.showMessageDialog(frame,"campo vacio");
        		}
        		if(name.equals(nom[2]))
        		{
        			if(name.equals(nom[2])&&contra.equals(con[2]))
        			{
        				JOptionPane.showMessageDialog(frame,"Bienvenido");
        				menusecre vent=new menusecre();
        				frame.dispose();
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(frame,"contraseña o usuario incorectos");
        			}
        		}
        		if(name.equals(nom[1]))
        		{
        			if(name.equals(nom[1])&&contra.equals(con[1]))
        			{
        				JOptionPane.showMessageDialog(frame,"Bienvenido doc");
        				menudoc vent=new menudoc();
        				frame.dispose();
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(frame,"contraseña o usuario incorectos");
        			}
        		}
        		if(name.equals(nom[0])==false&&name.equals(nom[2])==false&&name.isEmpty()==false&name.equals(nom[1])==false)
        		{
        			JOptionPane.showMessageDialog(frame,"usuario no existe");
        			//lblLoging1.setVisible(true);
        			
        		}
            }
        
	
catch (SQLException ex)//el catch sirve para que hagarra el try esta clase sirve como login verificar datos un poco mas expl
		{
          
        }
		

		//
		
	}
}
