package main;

import structures.NetworkConfiguration;
import structures.NetworkDimension;

/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class is a fully-connected neural network
 * It uses gradient backpropagation for training
 * By default all layers use ReLU as the activation function, except last layer using the logistic function
 *
 */


public class Network
{
	/**
	 * Layers
	 */
	private Layer[] layers = null;
	
	/**
	 * Dimensions of the network
	 */
	private NetworkDimension dimension = null;
	
	/**
	 * The weights and bias will be set to random numbers
	 * 
	 * @param nbLayers	Number of layers in the network
	 * @param nbNeurons	Number of neurons on each layer
	 */
	public Network(NetworkDimension dimension)
	{
		this.dimension = dimension;
		this.layers = new Layer[this.dimension.nbLayers];
		
		this.layers[0] = new Layer(dimension.nbNeuronsPerLayer[0], ActivationFunction.RELU);
		for (int i = 1; i < this.dimension.nbLayers; i ++)
			this.layers[i] = new Layer(dimension.nbNeuronsPerLayer[i], this.layers[i - 1], (i == this.dimension.nbLayers - 1) ? ActivationFunction.LOGISTIC_FUNCTION : ActivationFunction.RELU);
		
		for (int i = 0; i < this.dimension.nbLayers - 1; i ++)
			this.layers[i].setOutputLayer(this.layers[i + 1]);
	}
	
	/**
	 * Main constructor
	 * 
	 * @param nbLayers	Number of layers in the network
	 * @param nbNeurons	Number of neuron on each layer
	 * @param weights	All the weights for all the neurons on each layer
	 * @param bias		All the bias for all the neurons on each layer
	 */
	public Network(NetworkDimension dimension, NetworkConfiguration configuration)
	{
		this.dimension = dimension;
		this.layers = new Layer[this.dimension.nbLayers];
		
		this.layers[0] = new Layer(dimension.nbNeuronsPerLayer[0], ActivationFunction.RELU);
		for (int i = 0; i < this.dimension.nbLayers - 1; i ++)
			this.layers[i + 1] = new Layer(dimension.nbNeuronsPerLayer[i + 1], this.layers[i], configuration.layerConfigurations[i],
										   (i == this.dimension.nbLayers - 1) ? ActivationFunction.LOGISTIC_FUNCTION : ActivationFunction.RELU);
		
		for (int i = 0; i < this.dimension.nbLayers - 1; i ++)
			this.layers[i].setOutputLayer(this.layers[i + 1]);
	}
	
	/**
	 * This method feeds-forward a set of inputs into the network
	 * 
	 * @param inputs	Inputs of the network
	 */
	public void feedForward(double[] inputs)
	{
		for (int i = 0; i < this.dimension.nbLayers; i ++)
			this.layers[i].feedForward(inputs);
	}
	
	/**
	 * This method returns the outputs of the network, after feeding-forward
	 * 
	 * @return	Outputs of the network
	 */
	public double[] getOutputs()
	{
		return this.layers[this.dimension.nbLayers - 1].getActivationValues();
	}
}
