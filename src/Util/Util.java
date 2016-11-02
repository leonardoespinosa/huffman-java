package Util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Util {   
	
	public static void centrarVentana( JFrame ventana )
	{
		Dimension dPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dVentana = ventana.getSize();
		
		int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
		int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );
		
		ventana.setLocation( xEsquina, yEsquina );		
	}

}
