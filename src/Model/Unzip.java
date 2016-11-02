package Model;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Unzip {
	
	private String cad, binaryCode="";
	private String finalString = "";
	private Node huffmanTree;
	
	/**
	 * Constructor de la clase
	 */
	public Unzip()
	{
		
	}
	
	/**
	 * Metodo que cambia el valor de la variable cad
	 * @param pCad
	 */
	public void setCad( String pCad )
	{
		this.cad = pCad;
	}
	
	/**
	 * Metodo que cambia el valor de la variable huffmanTree
	 * @param pNode
	 */
	public void setHuffmanTree( Node pNode )
	{
		this.huffmanTree = pNode;
	}
	
	/**
	 * Metodo que retorna el valor de la variable finalString
	 * @return String
	 */
	public String getFinalString()
	{
		return this.finalString;
	}
	
	/**
	 * Metodo  que genera el codigo binario a partir de los bytes
	 */
	public void generateBinaryCode( Hashtable pByteTable )
	{
		String [] lBytes = cad.split("-");
		
		for(int i = 0; i < lBytes.length; i++ )
		{
			int lbyte = Integer.parseInt( lBytes[i] );
			binaryCode += pByteTable.get( lbyte );
			//binaryCode += Integer.toBinaryString( lbyte );
		}
	}
	
	/**
	 * Metodo que elimina los bits significativos de la cadena de bits
	 * @param pBits
	 */
	public void deleteSignificatBits( int pBits )
	{
		binaryCode = binaryCode.substring(0, binaryCode.length() - pBits);
	}
	
	/**
	 * Metodo que genera cadena recorriendo el arbol de huffman
	 * @param pHuffmanTree
	 */
	public void generateString( Node pNode )
	{			
		if( pNode.getNodeLeft() == null && pNode.getNodeRight() == null )
		{
			finalString += Character.toString( (char) pNode.getChar() );
			generateString( huffmanTree );
		}
		
		String pBit = getBit();
		
		if( !pBit.equals("") )
		{
			if( pBit.equals("1") )
			{
				generateString( pNode.getNodeLeft() );
			}
			else
			{
				generateString( pNode.getNodeRight() );
			}
		}			
	}
	
	/**
	 * Metodo que retorna el primer bit de la cadena de bits
	 * @return String
	 */
	private String getBit()
	{
		String pBit="";
		
		if( !binaryCode.isEmpty() )
		{
			pBit = Character.toString( binaryCode.charAt( 0 ) );
			binaryCode = binaryCode.substring( 1, binaryCode.length() );
		}
		else
		{
			pBit = "";
		}
		
		return pBit;
	}
}
