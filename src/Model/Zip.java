package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Zip {
	
	private Hashtable frecuencyTable;
	private Hashtable mapHT;
	private Hashtable bytesTable;
	private ArrayList sort = new ArrayList();
	private ArrayList bytes = new ArrayList();
	private Node huffmanTree =  new Node(0, 256, null, null );
	private String cad, huffmanCode = "", finalBytes = "";
	private int significatBits = 0;
	
	/**
	 * Constructor de la clase
	 */
	public Zip()
	{
		frecuencyTable = new Hashtable();
		mapHT = new Hashtable();
		bytesTable = new Hashtable();
	}
	
	/**
	 * Metodo que retorna el valor de la variable frecuencyTable
	 * @return Hashtable
	 */
	public Hashtable getFrecuencyTable()
	{
		return this.frecuencyTable;
	}
	
	/**
	 * Metodo que retorna el arbol de huffman
	 * @return Node
	 */
	public Node getHuffmanTree()
	{
		return this.huffmanTree;
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
	 * Metodo que retorna el valor de la variale finalBytes
	 * @return String
	 */
	public String getFinalBytes()
	{
		return this.finalBytes;
	}
	
	/**
	 * Metodo que retorna los bits significativos
	 * @return int
	 */
	public int getSignificatBits()
	{
		return this.significatBits;
	}
	
	/**
	 * Metodo que retorna tabla de bytes
	 * @return Hashtable
	 */
	public Hashtable getBytesTable()
	{
		return this.bytesTable;
	}
	
	/**
	 * Metodo que construye la tabla de frecuencias de la cadena ingresada
	 * @param pCad
	 */
	public void buildFrecuencyTable()
	{
		Node node;
		int lchars = 0;
		
		for( int i = 0; i < cad.length(); i++ )
		{
			lchars = (int) cad.charAt( i );
			node = ( Node )frecuencyTable.get( "" + lchars );
			
			if( node == null )
			{
				frecuencyTable.put( "" + lchars, new Node ( lchars ) );
			}
			else
			{
				node.setFrecuency( node.getFrecuency() + 1 );
			}
		}
	}
	
	/**
	 * Metodo que organiza los valores obtenidos en la tabla de frecuencias
	 * @param pFrecTable
	 */
	public void sortData( Hashtable pFrecTable )
	{
		Node lNode;
		int lFrecuency;
		int [] sortFrecuency = new int[pFrecTable.size()];
		Enumeration nodesHT = pFrecTable.elements();
		ArrayList sortTMP = new ArrayList();
		
		for( int i = 0; i <= 255; i++ )
		{
			sortTMP.add( 0, 0 );
		}
		
		for( int j = 0; j < pFrecTable.size(); j++ )
		{
			lNode = (Node) nodesHT.nextElement();
			sortFrecuency[j] = (int) lNode.getFrecuency();
			sortTMP.set( lNode.getChar(), (int)lNode.getFrecuency() );			
		}
		
		Arrays.sort( sortFrecuency );
		
		for( int k = 0; k < sortFrecuency.length; k++ )
		{
			int tmp = sortTMP.indexOf( sortFrecuency[k] );
			sortTMP.set(tmp, 0);
			lFrecuency = sortFrecuency[k];
			Node lnewNode = new Node(lFrecuency, tmp, null, null );
			sort.add(k , lnewNode);
		}
	}
	
	/**
	 * Metodo que crea el arbol de Huffman
	 */
	public void createHuffmanTree()
	{
		Node pTempNodeleft, pTempNodeRight, pTempNewNode = new Node(0, 256, null, null );
		
		while(sort.size() != 1)
		{
			pTempNodeleft = (Node)sort.get(0);
			sort.remove(0);
			
			pTempNodeRight = (Node)sort.get(0);
			sort.remove(0);
			
			pTempNewNode = createNewNode( pTempNodeleft, pTempNodeRight );
			sort = insertNewNode( pTempNewNode, sort );
		}
		huffmanTree = (Node) sort.get(0);
		preOrder( huffmanTree, "");
		codeHuffmanTree();
		printBytes();
	}
	
	/**
	 * Metodo que crea un nuevo nodo
	 * @param pNodeLeft
	 * @param pNodeRight
	 * @return Node
	 */
	private Node createNewNode( Node pNodeLeft, Node pNodeRight )
	{
		Node lNewNode;
		long lFrecuencyNewNode;
		
		lFrecuencyNewNode = ( pNodeLeft.getFrecuency() + pNodeRight.getFrecuency() );
		lNewNode = new Node(0, 256, null, null );
		
		lNewNode.setFrecuency( lFrecuencyNewNode );
		
		lNewNode.setNodeLeft( pNodeLeft );
		lNewNode.setNodeRight( pNodeRight );
		
		return lNewNode;
	}
	
	/**
	 * Metodo para insertar nuevo nodo en laa lista
	 * @param pNewNode
	 * @param pSort
	 * @return ArrayList
	 */
	private ArrayList insertNewNode( Node pNewNode, ArrayList pSort )
	{
		Node lFirstNode, lSecondNode = new Node(0, 256, null, null );
		pSort.add( 0, pNewNode );
		
		for(int i = 0; i < pSort.size()-1; i++ )
		{
			lFirstNode = (Node) pSort.get( i );
			lSecondNode = (Node) pSort.get( i + 1 );
			if( lFirstNode.getFrecuency() >= lSecondNode.getFrecuency() )
			{
				pSort.set( (i + 1) , lFirstNode );
				pSort.set( i, lSecondNode );
			}
		}
		return pSort;
	}
	
	/**
	 * Metodo que recorre el arbol en pre orden y genera los codigos en binario
	 * @param pNode
	 */	
	private void preOrder( Node node, String pVal ) 
	{
        if (node.getNodeLeft() == null && node.getNodeRight() == null) 
        {
        	mapHT.put( "" + node.getChar(), pVal );
            return;
        }    
        preOrder( node.getNodeLeft(), pVal + "1" );
        preOrder( node.getNodeRight(), pVal + "0" );
    }
	
	/**
	 * Metodo que genera bytes a partir del codigo de huffman
	 */
	private void codeHuffmanTree()
	{
		int lChar, lByte;
		boolean lEnd = true;
		String lCodeBytes = "";
		
		for( int i = 0; i < cad.length(); i++ )
		{
			lChar = (int) cad.charAt( i );
			huffmanCode += mapHT.get( "" + lChar );
		}
		
		while( lEnd )
		{
			for( int j = 0; j < 8; j++ )
			{
				lCodeBytes += Character.toString( huffmanCode.charAt(j) );
			}
			
			lByte = Integer.parseInt( lCodeBytes, 2 );
			bytes.add(lByte);
			bytesTable.put(lByte, lCodeBytes);
			lCodeBytes = "";
			huffmanCode = huffmanCode.substring( 8, huffmanCode.length() );
			
			if( huffmanCode.length() == 0 )
			{
				lEnd = false;
				break;
			}
			
			if( huffmanCode.length() < 8 )
			{
				huffmanCode = addSignificativeBits( huffmanCode );
			}
		}
	}
	
	/**
	 * Metodo que agrega bits significativos a la cadena de bits
	 * @param pHufCode
	 * @return String
	 */
	private String addSignificativeBits( String pHufCode )
	{
		while( huffmanCode.length() < 8 )
		{
			huffmanCode += "0";
			significatBits += 1;
		}
		return huffmanCode;
	}
	
	/**
	 * Metodo que imprime los bytes generados en una cadena de texto
	 */
	private void printBytes()
	{
		for(int i = 0; i < bytes.size(); i++ )
		{
			finalBytes += bytes.get(i);
			finalBytes += "-";
		}
	}
}
