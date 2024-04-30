import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class menusecre extends JFrame {

	private JPanel contentPane;
	private JPanel panel1 = new JPanel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public menusecre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(menusecre.class.getResource("/imagenes/png-transparent-imss-hd-logo.png")));
		setResizable(false);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 705);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 784, 133);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSalir.setBackground(new Color(64, 128, 128));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ligin renasss=new ligin();
				Window b= SwingUtilities.getWindowAncestor(contentPane);
				b.dispose();
			}
		});
		btnSalir.setBounds(96, 37, 238, 25);
		panel.add(btnSalir);
		
		JButton btnCita = new JButton("Cita");
		btnCita.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCita.setForeground(new Color(255, 255, 255));
		btnCita.setBackground(new Color(64, 128, 128));
		btnCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cit vent= new cit();
				nuevopane(vent);
			}
		});
		btnCita.setBounds(432, 37, 238, 25);
		panel.add(btnCita);
		
		/*
		JButton btnReceta = new JButton("receta");
		btnReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnReceta.setBounds(529, 25, 97, 25);
		panel.add(btnReceta);
		*/
		panel1.setBackground(new Color(51, 51, 51));
		panel1.setBounds(0, 129, 784, 539);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(menusecre.class.getResource("/imagenes/login image.png")));
		lblNewLabel.setBounds(212, 123, 407, 283);
		panel1.add(lblNewLabel);
	}
	private void nuevopane(JPanel Actual)
	{
		panel1.removeAll();
		panel1.add(Actual);
		panel1.repaint();
		panel1.revalidate();

	}
}
