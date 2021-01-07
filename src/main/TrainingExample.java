package main;
/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class represents a training example for a neural network
 *
 */

public class TrainingExample
{
	/**
	 * Number of values in the input
	 */
	private int nbInputs = 0;
	
	/**
	 * Number of values in the ouput
	 */
	private int nbOutputs = 0;
	
	/**
	 * Inputs fed into the network
	 */
	private double[] input = null;
	
	/**
	 * Outputs the network is supposed to give
	 */
	private double[] expectedOutput = null;
	
	/**
	 * Output of the network
	 */
	private double[] output = null;
	
	/**
	 * Value of the cost function
	 */
	private double cost = 0;
	
	/**
	 * Main constructor
	 * 
	 * @param nbInputs 			Number of values in the input
	 * @param input				Input for the network
	 * @param nbOutputs			Number of values in the output
	 * @param expectedOutput	Expected output of the network
	 */
	public TrainingExample(int nbInputs, double[] input, int nbOutputs, double[] expectedOutput)
	{
		this.nbInputs = nbInputs;
		this.input = input;
		
		this.nbOutputs = nbOutputs;
		this.expectedOutput = expectedOutput;
	}
	
	/**
	 * This method sets the output given by the network, and calculates the cost from that output
	 * 
	 * @param output	Output of the network
	 */
	public void setOutput(double[] output)
	{
		this.output = output;
		calculateCost();
	}
	
	/**
	 * This method calculates the cost of the output given by the network
	 */
	private void calculateCost()
	{
		this.cost = 0;
		
		for (int i = 0; i < this.nbOutputs; i ++)
			this.cost += Math.pow(this.output[i] - this.expectedOutput[i], 2);
	}
	
	/**
	 * @return Number of values in the input
	 */
	public int getNbInputs()
	{
		return this.nbInputs;
	}
	
	/**
	 * @return Input for this training example
	 */
	public double[] getInput()
	{
		return this.input;
	}
	
	/**
	 * @return Cost of the output given by the network
	 */
	public double getCost()
	{
		return this.cost;
	}
	
	/**
	 * This method returns the cost of a specific output value
	 * 
	 * @param index	Index of the output value
	 * @return	Cost of the output value
	 */
	public double getCost(int index)
	{
		return Math.pow(this.output[index] - this.expectedOutput[index], 2);
	}
}
