package View;

import javax.swing.JFrame;
import Util.Util;
import Controller.Controller;

public class InterfazApp extends JFrame{
	
	private PanelSelect pnlSelect;
	private PanelSource pnlSource;
	private PanelZip pnlZip;
	
	Controller controller;
	
	/**
	 * Constructor de la clase
	 * @param pController
	 */
	public InterfazApp( Controller pController )
	{
		this.controller = pController;
		
		pnlSelect = new PanelSelect( pController );
		pnlSource =  new PanelSource();
		pnlZip = new PanelZip();
		
		getContentPane().add( pnlSelect );
		pnlSelect.setBounds( 5, 5, 580, 100 );
		
		getContentPane().add( pnlSource );
		pnlSource.setBounds( 5, 110, 580, 100 );
		
		getContentPane().add( pnlZip );
		pnlZip.setBounds( 5, 220, 580, 100 );
		
		setTitle( "Huffman" );
		setSize( 600, 380 );
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		getContentPane().setLayout( null );
		
		controller.conectPanels( pnlSelect, pnlSource, pnlZip );
		Util.centrarVentana( this );
		
		pnlSource.isEnabledPnlSource( true );
		pnlZip.isEnabledPnlZip( false );
	}

	public static void main( String[] args ) {
		
		InterfazApp aplication = new InterfazApp( new Controller() );
		aplication.setVisible( true );

	}

}
