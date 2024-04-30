import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class conector {
	
	Connection conectar=null;
	public Connection conexion()
	{
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			conectar=DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");
			System.out.println ("conexion aceptada...");
		}catch(Exception e){
			System.out.println ("mensaje de error "+e);
			JOptionPane.showMessageDialog(null,"no se pudo conectar");
		}
		return conectar;
		
	}
}
