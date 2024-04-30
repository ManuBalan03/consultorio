import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;



public class docmodelo {
public ArrayList<doctores> getdoc()
{
	conector conn=new conector();
	Connection cn= conn.conexion();
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	ArrayList<doctores> lista= new  ArrayList<>();
	try
	{
		st= (Statement) cn.createStatement();
		 rs = st.executeQuery("select * from doctores");
		 while (rs.next()) {
			 doctores llave=new doctores();
			 llave.setIDDoctor(rs.getInt("IDDOCTOR"));
			 llave.setNombre(rs.getString("Nombre"));
			 llave.setTurno(rs.getString("Turno"));
			 llave.setGenero(rs.getString("Genero"));
			 llave.setEspecialidad(rs.getString("Especialidad"));
			 lista.add(llave);
		 }
		 
	}
	catch(SQLException ex)
	{
		Logger.getLogger(docmodelo.class.getName()).log(Level.SEVERE,null,ex);
	}
	return lista;
}
}
