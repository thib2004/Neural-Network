package structures;

/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class is a gradient for a neural network
 */

public class Gradient
{
	public NetworkDimension networkDimension;
	
	public LayerGradient[] layerGradients;
	
	public Gradient(NetworkDimension networkDimension, LayerGradient[] layerGradients)
	{
		this.networkDimension = networkDimension;
		this.layerGradients = layerGradients;
	}
}
