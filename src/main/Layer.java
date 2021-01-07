package main;

import structures.LayerConfiguration;

/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class is a layer in a neural network
 *
 */


public class Layer
{
	/**
	 * Number of neurons in the layer
	 */
	private int nbNeurons = 0;
	
	/**
	 * Neurons of the layer
	 */
	private Neuron[] neurons = null;
	
	/**
	 * Previous layer
	 */
	private Layer inputLayer = null;
	
	/**
	 * Output layer
	 */
	private Layer outputLayer = null;
	
	/**
	 * If true, this layer is the input layer
	 */
	private boolean isInputLayer = false;
	
	/**
	 * Activation function used on this layer
	 */
	private ActivationFunction activationFunction = null;
	
	/**
	 * This constructor is used for the input layer
	 * 
	 * @param nbNeurons Number of neurons in the layer
	 */
	public Layer(int nbNeurons, ActivationFunction activationFunction)
	{
		this.nbNeurons = nbNeurons;
		this.activationFunction = activationFunction;
		
		this.isInputLayer = true;
		
		this.neurons = new Neuron[this.nbNeurons];
		for(int i = 0; i < this.nbNeurons; i ++)
		{
			this.neurons[i] = new Neuron();
			this.neurons[i].setNeuronIndex(i);
		}
	}
	
	/**
	 * The weights and bias will be set to random values
	 * 
	 * @param nbNeurons	Number of neurons
	 * @param inputLayer	Previous layer in the network
	 */
	public Layer(int nbNeurons, Layer inputLayer, ActivationFunction activationFunction)
	{
		this.nbNeurons = nbNeurons;
		this.activationFunction = activationFunction;
		
		this.inputLayer = inputLayer;
		
		this.neurons = new Neuron[this.nbNeurons];
		for (int i = 0; i < this.nbNeurons; i ++)
		{
			this.neurons[i] = new Neuron(this.inputLayer.getNbNeurons(), this.inputLayer.getNeurons(), this.activationFunction);
			this.neurons[i].setNeuronIndex(i);
		}
	}
	
	/**
	 * Main constructor
	 * 
	 * @param nbNeurons Number of neurons
	 * @param inputLayer Previous layer in the network
	 * @param weights	Weights of the neurons
	 * @param bias	Bias of the neurons 
	 */
	public Layer(int nbNeurons, Layer inputLayer, LayerConfiguration configuration, ActivationFunction activationFunction)
	{
		this.nbNeurons = nbNeurons;
		this.activationFunction = activationFunction;
		
		this.inputLayer = inputLayer;
		
		this.neurons = new Neuron[this.nbNeurons];
		for (int i = 0; i < this.nbNeurons; i ++)
		{
			this.neurons[i] = new Neuron(this.inputLayer.getNbNeurons(), configuration.neuronConfigurations[i],
										 this.inputLayer.getNeurons(), this.activationFunction);
			this.neurons[i].setNeuronIndex(i);
		}
	}
	
	/**
	 * This method is used to set the output layer of this layer
	 * 
	 * @param outputLayer	Output layer
	 */
	public void setOutputLayer(Layer outputLayer)
	{
		this.outputLayer = outputLayer;
		
		for (int i = 0; i < this.nbNeurons; i ++)
			this.neurons[i].setOutputNeurons(this.outputLayer.getNbNeurons(), this.outputLayer.getNeurons());
	}
	
	/**
	 * This method feeds-forward the layer
	 * @param inputs Inputs of the network, only used if this is the input layer
	 */
	public void feedForward(double[] inputs)
	{
		if (this.isInputLayer)
			for (int i = 0; i < this.nbNeurons; i ++)
				this.neurons[i].setActivationValue(inputs[i]);
		else
			for (int i = 0; i < this.nbNeurons; i ++)
				this.neurons[i].feedForward();
	}
	
	/**
	 * This method makes each neurons of the layer calculate its deltas
	 */
	public void calculateDeltas()
	{
		for (int i = 0; i < this.nbNeurons; i ++)
			this.neurons[i].calculateDeltas();
	}
	
	/**
	 * This method sets the deltas of its neurons to the specified values
	 * 
	 * @param deltas	New deltas for the neurons
	 */
	
	public void setDeltas(double[] deltas)
	{
		for (int i = 0; i < this.nbNeurons; i ++)
			this.neurons[i].setDelta(deltas[i]);
	}

	/**
	 * @return The activation values of all the neurons on the layer
	 */
	public double[] getActivationValues()
	{
		double[] values = new double[this.nbNeurons];
		
		for (int i = 0; i < this.nbNeurons; i ++)
			values[i] = this.neurons[i].getActivationValue();
		
		return values;
	}
	
	/**
	 * This method returns a 2-dimensional array containing all the weight deltas for all the neurons on this layer
	 * 
	 * @return The weight deltas for the neurons on this layer
	 */
	public double[][] getWeightDeltas()
	{
		double[][] deltas = new double[this.nbNeurons][];
		
		for (int i = 0; i < this.nbNeurons; i ++)
			deltas[i] = this.neurons[i].getWeightDeltas();
		
		return deltas;
	}
	
	/**
	 * This method returns the bias deltas for all the neurons on this layer
	 * 
	 * @return	Bias deltas for all the neurons on this layer
	 */
	public double[] getBiasDeltas()
	{
		double[] deltas = new double[this.nbNeurons];
		
		for (int i = 0; i < this.nbNeurons; i ++)
			deltas[i] = this.neurons[i].getBiasDelta();
		
		return deltas;
	}
	
	/**
	 * @return Number of neurons on the layer
	 */
	public int getNbNeurons()
	{
		return this.nbNeurons;
	}
	
	/**
	 * @return An array containing all the neurons on the layer
	 */
	public Neuron[] getNeurons()
	{
		return this.neurons;
	}
}
