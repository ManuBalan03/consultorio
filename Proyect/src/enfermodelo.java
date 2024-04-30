

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

public class enfermodelo extends Thread {
	public void run()
	{
		geten();
	}
	public ArrayList<enfer> geten()
	{
		conector conn=new conector();
		Connection cn= conn.conexion();
		PreparedStatement pst;
		ResultSet rs;
		Statement st;
		ArrayList<enfer> lista= new  ArrayList<>();
		try
		{
			st= (Statement) cn.createStatement();
			 rs = st.executeQuery("select * from enfer");
			 while (rs.next()) {
				 enfer llave1=new enfer();
				 
				 llave1.setIDenfer(rs.getInt("IDenfer"));
				 llave1.setNombre(rs.getString("Nombre"));
				 llave1.setTurno(rs.getString("Turno"));
				 llave1.setGenero(rs.getString("Genero"));
				 llave1.setEspecialidad(rs.getString("Especialidad"));
				 lista.add(llave1);
			 }
			 
		}
		catch(SQLException ex)
		{
			Logger.getLogger(docmodelo.class.getName()).log(Level.SEVERE,null,ex);
		}
		return lista;
	}
}
