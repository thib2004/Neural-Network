package main;
/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This programm is the structure for a neural network and it's training process
 *
 */

public class NeuralNetwork
{
	public static ActivationFunction ACTIVATION_FUNCTION = ActivationFunction.RELU;
	
	public static void main(String[] args)
	{
		int[] nn = {2, 2, 1};
		double[][][] w = {{{1, 0.5}, {1, 1}}, {{0.5, 1}}};
		double[][] b = {{2, 1}, {-3}};
		
		/*Network network = new Network(3, nn, w, b);
		
		double[] inputs = {1, 1};
		network.feedForward(inputs);
		System.out.println(network.getOutputs()[0]);*/
	}

}
