package View;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelZip extends JPanel{
	
private JTextField txtInfo;
	
	/**
	 * Constructor de la clase
	 */
	public PanelZip()
	{
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( " Zip " ) ) );
		setLayout( null );
		
		txtInfo = new JTextField();
		add( txtInfo );
		txtInfo.setBounds( 21, 20, 540, 70);
	}
	
	/**
	 * Metodo que cambia el texto en el campo
	 * @param pInformation
	 */
	public void setInfo ( String pInformation )
	{
		txtInfo.setText( pInformation );
	}
	
	/**
	 * Metodo que retorna la informacion del campo
	 * @return String
	 */
	public String getInfo()
	{
		return txtInfo.getText();
	}
	
	public void isEnabledPnlZip(boolean enablePnlZip)
	{
		txtInfo.enable(enablePnlZip);
	}

}
