package structures;
/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class is a dimension for a neural network
 *
 */

public class NetworkDimension
{
	public int nbLayers;
	
	public int[] nbNeuronsPerLayer;
	
	public NetworkDimension()
	{
		
	}
	
	public NetworkDimension(int nbLayers, int[] nbNeuronsPerLayer)
	{
		this.nbLayers = nbLayers;
		this.nbNeuronsPerLayer = nbNeuronsPerLayer;
	}
}
