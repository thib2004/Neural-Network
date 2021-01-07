package main;

import structures.NeuronConfiguration;

/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class is a neuron
 *
 */

public class Neuron
{
	private NeuronConfiguration configuration;
	
	/**
	 * Previous neurons connected to this one
	 */
	private Neuron[] inputNeurons = null;
	
	/**
	 * Neurons that are connected after this one
	 */
	private Neuron[] outputNeurons = null;
	
	/**
	 * The number of output neurons
	 */
	private int nbOutputNeurons = 0;
	
	/**
	 * Number of input neurons
	 */
	private int nbInputs = 0;
	
	/**
	 * Value held by the neuron when forward-feeding
	 */
	private double activationValue = 0;
	
	/**
	 * Weighted sum of the neuron
	 */
	private double weightedSum = 0;
	
	/**
	 * Activation function used for this neuron
	 */
	private ActivationFunction activationFunction = null;
	
	/**
	 * This is the delta of the cost function, in respect to the activation value of this neuron
	 * Calculated with the gradient backpropagation, and used to calculate the deltas of the previous neurons
	 */
	private double delta = 0;
	
	/**
	 * Deltas of all the weights
	 */
	private double[] weightDeltas = null;
	
	/**
	 * Delta of the bias
	 */
	private double biasDelta = 0;
	
	/**
	 * This is the index of the neuron on its layer
	 */
	private int neuronIndex = -1;
	
	/**
	 * Default constructor used if the neuron is an input neuron
	 */
	public Neuron()
	{
		
	}
	
	/**
	 * This constructor will set the weights and the bias to random values
	 * 
	 * @param nbInputs Number of input neurons
	 * @param inputNeurons	All the input neurons of this neuron
	 */
	public Neuron(int nbInputs, Neuron[] inputNeurons, ActivationFunction activationFunction)
	{
		this.nbInputs = nbInputs;
		this.activationFunction = activationFunction;
		this.inputNeurons = inputNeurons;
		
		double[] weights = new double[this.nbInputs];
		for (int i = 0; i < this.nbInputs; i ++)
			weights[i] = Utility.randomWeight();
		
		this.configuration = new NeuronConfiguration(weights, Utility.randomBias());
	}
	
	/**
	 * Main constructor
	 * 
	 * @param nbInputs Number of input neurons
	 * @param weights	All the weights of the neuron
	 * @param bias	Bias of the neuron
	 * @param inputNeurons	Input neurons of this neuron
	 */
	public Neuron(int nbInputs, NeuronConfiguration configuration, Neuron[] inputNeurons, ActivationFunction activationFunction)
	{
		this.nbInputs = nbInputs;
		this.configuration = configuration;
		this.inputNeurons = inputNeurons;
		this.activationFunction = activationFunction;
	}
	
	/**
	 * This method is used to set the output neurons of this neuron
	 * 
	 * @param nbOutputNeurons	Number of output neurons
	 * @param outputNeurons		Output neurons
	 */
	protected void setOutputNeurons(int nbOutputNeurons, Neuron[] outputNeurons)
	{
		this.nbOutputNeurons = nbOutputNeurons;
		this.outputNeurons = outputNeurons;
	}
	
	/**
	 * This function is used to set the index of this neuron on its layer
	 * 
	 * @param index Index of the neuron
	 */
	protected void setNeuronIndex(int index)
	{
		this.neuronIndex = index;
	}
	
	/**
	 * This method feeds forward the neuron
	 * It computes the activation value based on the weights, the bias, and the activation values of the previous neurons on the network
	 */
	public void feedForward()
	{
		/*
		 * Calculates the weighted sum
		 */
		this.weightedSum = this.configuration.bias;
		
		for (int i = 0; i < this.nbInputs; i ++)
			this.weightedSum += this.inputNeurons[i].getActivationValue() * this.configuration.weights[i];
		
		//Applies the activation function to the weighted sum
		this.activationValue = Utility.activationFunction(this.weightedSum, this.activationFunction);
	}
	
	/**
	 * This method calculates the delta of the activation value, the weights and the bias of this neuron
	 */
	public void calculateDeltas()
	{
		this.delta = 0;
		
		//Calculates the delta of this neuron
		for (int i = 0; i < this.nbOutputNeurons; i ++)
			delta += this.outputNeurons[i].getDelta() *
					 Utility.activationFunctionDerivative(this.outputNeurons[i].getWeightedSum(), this.outputNeurons[i].getActivationFunction()) *
					 this.outputNeurons[i].getWeightAt(this.neuronIndex);
		
		this.weightDeltas = new double[this.nbInputs];
		
		//Calculates the deltas of each weights
		for (int i = 0; i < this.nbInputs; i ++)
			this.weightDeltas[i] = this.delta *
								   Utility.activationFunctionDerivative(this.weightedSum, this.activationFunction) *
								   this.inputNeurons[i].getActivationValue();
		
		//Calculates the delta of the bias
		this.biasDelta = this.delta * Utility.activationFunctionDerivative(this.weightedSum, this.activationFunction);
	}
	
	/**
	 * This method sets the delta of this neuron
	 * Recommended to only use if this is an output neuron, or for debugging
	 * 
	 * @param delta New delta
	 */
	protected void setDelta(double delta)
	{
		this.delta = delta;
	}
	
	/**
	 * This method allow to set the activation value
	 * Not recommended, unless if it's an input neuron or for debugging
	 * 
	 * @param activationValue New value
	 */
	protected void setActivationValue(double activationValue)
	{
		this.activationValue = activationValue;
	}
	
	/**
	 * @return The activation value
	 */
	public double getActivationValue()
	{
		return this.activationValue;
	}
	
	/**
	 * @return The weighted sum
	 */
	public double getWeightedSum()
	{
		return this.weightedSum;
	}
	
	/**
	 * @return The activation function of this neuron     
	 */
	public ActivationFunction getActivationFunction()
	{
		return this.activationFunction;
	}
	
	/**
	 * This method returns the weight corresponding to the specified index
	 * 
	 * @param index	Index of the weight
	 * @return	The weight at this index
	 */
	public double getWeightAt(int index)
	{
		return this.configuration.weights[index];
	}
	
	/**
	 * @return The delta of this neuron
	 */
	public double getDelta()
	{
		return this.delta;
	}
	
	/**
	 * @return The deltas of the weights
	 */
	public double[] getWeightDeltas()
	{
		return this.weightDeltas;
	}
	
	/**
	 * @return The delta of the bias
	 */
	public double getBiasDelta()
	{
		return this.biasDelta;
	}
}
