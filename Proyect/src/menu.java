import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import java.awt.Font;

public class menu extends JFrame {

	private JPanel contentPane;//es mejor crearlo como atributo
	private JPanel panel1 = new JPanel();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public menu() {
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 705);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(74, 185, 166));
		panel.setBounds(0, 0, 1100, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDoctores = new JButton("Doctores");
		btnDoctores.setForeground(new Color(255, 255, 255));
		btnDoctores.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnDoctores.setBackground(new Color(122, 122, 122));
		btnDoctores.setBounds(208, 23, 134, 25);
		panel.add(btnDoctores);
		
		JButton btnMedicina = new JButton("Medicina");
		btnMedicina.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnMedicina.setForeground(new Color(255, 255, 255));
		btnMedicina.setBackground(new Color(122, 122, 122));
		btnMedicina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				medicinas vent=new medicinas();
				nuevopane(vent);
			}
		});
		btnMedicina.setBounds(418, 23, 134, 25);
		panel.add(btnMedicina);
		
		JButton btnEnfermeros = new JButton("Enfermeros");
		btnEnfermeros.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEnfermeros.setForeground(new Color(255, 255, 255));
		btnEnfermeros.setBackground(new Color(122, 122, 122));
		btnEnfermeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				enferme ve=new enferme();
				nuevopane(ve);
			}
		});
		btnEnfermeros.setBounds(610, 23, 134, 25);
		panel.add(btnEnfermeros);
		
		JButton btnEnfermeros1 = new JButton("Pacientes");
		btnEnfermeros1.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEnfermeros1.setForeground(new Color(255, 255, 255));
		btnEnfermeros1.setBackground(new Color(122, 122, 122));
		btnEnfermeros1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pacientes ve=new pacientes();
				nuevopane(ve);
			}
		});
		btnEnfermeros1.setBounds(800, 23, 134, 25);
		panel.add(btnEnfermeros1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(122, 122, 122));
		btnSalir.setBounds(28, 23, 134, 25);
		panel.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ligin renasss=new ligin();
				Window b= SwingUtilities.getWindowAncestor(contentPane);
				b.dispose();
				
			}
		});
		btnDoctores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				medicin p2=new medicin();
				nuevopane(p2);
			}
		});
		panel1.setBackground(SystemColor.windowBorder);
		
		
		panel1.setBounds(0, 89, 1100, 565);
		contentPane.add(panel1);
		panel1.setLayout(null);
	}
	private void nuevopane(JPanel Actual)
	{
		panel1.removeAll();
		panel1.add(Actual);
		panel1.repaint();
		panel1.revalidate();
	}
}
