package Model;

public class Node {
	
	private long frecuency;
	private int chars;
	private Node nodeLeft, nodeRight;	
	
	/**
	 * Constructor de la clase
	 * @param pChars
	 */
	public Node( int pChars )
	{
		this.frecuency = 1;
		this.chars = pChars;
	}
	
	/**
	 * Constructor de la clase
	 * @param pFrecuency
	 * @param pChars
	 * @param pLeft
	 * @param pRight
	 * @param pValorBin
	 */
	public Node( long pFrecuency, int pChars, Node pLeft, Node pRight )
	{
		this.frecuency = pFrecuency;
		this.chars = pChars;
		this.nodeLeft = pLeft;
		this.nodeRight = pRight;
	}
	
	/**
	 * Metodo que retorna el valor de la variable chars
	 * @return int
	 */
	public int getChar()
	{
		return this.chars;
	}
	
	/**
	 * Metodo que cambia el valor de la variable chars
	 * @param pChar
	 */
	public void setChar( int pChar )
	{
		this.chars = pChar;
	}
	
	/**
	 * Metodo que retorna el valor de la variable frecuency
	 * @return long
	 */
	public long getFrecuency()
	{
		return this.frecuency;
	}
	
	/**
	 * Metodo que cambia el valor de la variable frecuency
	 * @param pFrecuency
	 */
	public void setFrecuency( long pFrecuency )
	{
		this.frecuency = pFrecuency;
	}
	
	/**
	 * Metodo que retorna el valor de la variable nodeLeft
	 * @return Node
	 */
	public Node getNodeLeft()
	{
		return this.nodeLeft;
	}
	
	/**
	 * Metodo que cambia el valor de la variable nodeLeft
	 * @param pLeft
	 */
	public void setNodeLeft( Node pLeft )
	{
		this.nodeLeft = pLeft;
	}
	
	/**
	 * Metodo que retorna el valor de la variable nodeRight
	 * @return Node
	 */
	public Node getNodeRight()
	{
		return this.nodeRight;
	}
	
	/**
	 * Metodo que cambia el valor de la variable nodeRight
	 * @param pRight
	 */
	public void setNodeRight( Node pRight )
	{
		this.nodeRight = pRight;
	}
}
