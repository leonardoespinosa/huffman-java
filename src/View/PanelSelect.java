package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controller.Controller;

public class PanelSelect  extends JPanel implements ActionListener{
	
	private JRadioButton rdZip, rdUnzip;
	private JButton btnEjecutar, btnSalir;
	private ButtonGroup grp;
	
	Controller controller;
	
	/**
	 * Constructor de la clase PanelSelect	
	 * @param pController
	 */
	public PanelSelect( Controller pController )
	{
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0), new TitledBorder( " Zip / Unzip " ) ) );
		setLayout( null );
		
		this.controller = pController;
		
		grp = new ButtonGroup();
		
		btnEjecutar = new JButton( "Ejecutar" );
		btnEjecutar.addActionListener( this );
		btnEjecutar.setToolTipText( "Ejecutar" );
		
		btnSalir = new JButton( "Salir" );
		btnSalir.addActionListener( this );
		btnSalir.setToolTipText( "Salir" );
		
		rdZip = new JRadioButton( "Zip", true );
		rdZip.addActionListener( this );
		rdUnzip =  new JRadioButton( "Unzip", true );
		rdUnzip.addActionListener( this );
		
		grp.add( rdZip );
		grp.add( rdUnzip );
		grp.add( btnEjecutar ); 
		grp.add( btnSalir );
		add( rdZip );
		add( rdUnzip );
		add( btnEjecutar );
		add( btnSalir );
		
		rdZip.setBounds( 10, 20, 110, 40 );
		rdUnzip.setBounds( 130, 20, 80, 40 );
		btnEjecutar.setBounds( 250, 27, 100, 30 );
		btnSalir.setBounds( 400, 27, 100, 30 );
	}
	
	/**
	 * Metodo para retornar la opcion seleccionada: Zip - Unzip
	 * @return int
	 */
	public int getOperacion()
	{
		if( rdZip.isSelected() )
		{
			return 0;
		}
		else if( rdUnzip.isSelected() )
		{
			return 1;
		}
		else
			return -1;
	}
	
	/**
	 * Metodo para tratar los eventos
	 */
	public void actionPerformed( ActionEvent e)
	{
		if( e.getActionCommand().equals( "Ejecutar" ) )
		{
			controller.ejectRequest();
		}
		
		if( e.getSource() == btnSalir )
		{
			System.exit( 0 );
		}
		
		if( rdZip.isSelected() ==  true )
		{
			controller.zipIsSelected();
		}
		
		if( rdUnzip.isSelected() == true )
		{
			controller.unZipIsSelected();
		}
	}

}
