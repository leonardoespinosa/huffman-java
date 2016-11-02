package Controller;

import javax.swing.JOptionPane;

import Model.Unzip;
import Model.Zip;
import View.PanelSelect;
import View.PanelSource;
import View.PanelZip;

public class Controller {
	
	private PanelSelect pnlSelect;
	private PanelSource pnlSource;
	private PanelZip pnlZip;
	private Zip zip;
	private Unzip unzip;
	
	/**
	 * Constructor de la clase
	 */
	public Controller()
	{
		zip = new Zip();
		unzip = new Unzip();
	}
	
	/**
	 * Metodo para conectar la vista con el controlador
	 * @param pPnlSelect
	 * @param pPnlSource
	 * @param pPnlZip
	 */
	public void conectPanels ( PanelSelect pPnlSelect, PanelSource pPnlSource, PanelZip pPnlZip )
	{
		this.pnlSelect = pPnlSelect;
		this.pnlSource = pPnlSource;
		this.pnlZip = pPnlZip;
	}
	
	/**
	 * Metodo que valida que la cadena no se encuentre vacia
	 * @param pCad
	 * @return boolean
	 */
	private boolean validateString( String pCad )
	{
		if( pCad.isEmpty() )
		{
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Metodo para habilitar los paneles si se selecciona la opcion Zip
	 */
	public void zipIsSelected()
	{
		pnlSource.isEnabledPnlSource( true );
		pnlZip.isEnabledPnlZip( false );
	}
	
	/**
	 * Metodo para habilitar los paneles si se selecciona la opcion unzip
	 */
	public void unZipIsSelected()
	{
		pnlSource.isEnabledPnlSource( false );
		pnlZip.isEnabledPnlZip( true );
		pnlZip.setInfo( "" );
	}
	
	/**
	 * Metodo que ejecuta la solicitud enviada: Zip - Unzip
	 */
	public void ejectRequest()
	{
		if( pnlSelect.getOperacion() == 0 )
		{			
			String lCad = pnlSource.getInfo();
			String finalBytes = "";
			boolean lRes = validateString( lCad );
			
			if( lRes )
			{
				zip.setCad( lCad );
				zip.buildFrecuencyTable();
				zip.sortData( zip.getFrecuencyTable() );
				zip.createHuffmanTree();
				finalBytes = zip.getFinalBytes();
				pnlSource.setInfo( "" );
				pnlZip.setInfo( finalBytes );
			}
			else
			{
				JOptionPane.showMessageDialog(null, "La cadena de entrada se encuentra vacia.");
			}		
		}
		
		if( pnlSelect.getOperacion() == 1 )
		{
			String lCad = pnlZip.getInfo();
			String lFinalString;
			boolean lRes = validateString( lCad );
			
			if( lRes )
			{
				unzip.setCad( lCad );
				unzip.generateBinaryCode( zip.getBytesTable() );
				unzip.deleteSignificatBits( zip.getSignificatBits() );
				unzip.setHuffmanTree( zip.getHuffmanTree() );
				unzip.generateString( zip.getHuffmanTree() );
				lFinalString = unzip.getFinalString();
				pnlSource.setInfo( lFinalString );
			}
			else
			{
				JOptionPane.showMessageDialog(null, "La cadena de entrada se encuentra vacia.");
			}
		}
	}

}
