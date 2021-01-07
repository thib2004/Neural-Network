package main;
/**
 * @author Thibault Blossier
 * @version 06/01/2021
 * 
 * This class contains functions and constants used in the network
 *
 */

public class Utility
{
	/**
	 * Minimum and maximum for the values of the weights
	 */
	public static final double WEIGHT_MIN_VALUE = -1;
	public static final double WEIGHT_MAX_VALUE = 1;
	
	/**
	 * Minimum and maximum for the values of the bias
	 */
	public static final double BIAS_MIN_VALUE = -10;
	public static final double BIAS_MAX_VALUE = 10;
	
	/**
	 * @return A random weight
	 */
	public static double randomWeight()
	{
		return randomNumber(WEIGHT_MIN_VALUE, WEIGHT_MAX_VALUE);
	}
	
	/**
	 * @return A random bias
	 */
	public static double randomBias()
	{
		return randomNumber(BIAS_MIN_VALUE, BIAS_MAX_VALUE);
	}
	
	/**
	 * @param min Minimum
	 * @param max	Maximum
	 * @return Random number between the minimum and the maximum
	 */
	private static double randomNumber(double min, double max)
	{
		return (Math.random() * (max - min)) + min;
	}
	
	/**
	 * This method  gives the output of an activation function
	 * 
	 * @param z	Input of the function
	 * @param activationFunction Activation function to apply
	 * @return	Output of the non-linear function
	 */
	public static double activationFunction(double z, ActivationFunction activationFunction)
	{
		switch(activationFunction)
		{
			case RELU:
				return ReLU(z);
				
			case LOGISTIC_FUNCTION:
				return sigmoidFunction(z);
				
			default:
				return 0;
		}
	}
	
	/**
	 * This method gives the output of the derivative of an activation function
	 * 
	 * @param z	Input to the function
	 * @param activationFunction The derivative of this function will be used
	 * @return	The output of the derivative an activation function
	 */
	public static double activationFunctionDerivative(double z, ActivationFunction activationFunction)
	{
		switch (activationFunction)
		{
			case RELU:
				return ReLUDerivative(z);
				
			case LOGISTIC_FUNCTION:
				return sigmoidFunctionDerivative(z);
				
			default:
				return 0;
		}
	}
	
	/**
	 * This is the Rectified Linear Unit function (ReLU)
	 * If the input is lower than 0, it outputs 0
	 * Otherwise, it outputs the input
	 * 
	 * @param z	Input
	 * @return	Output of the ReLU function
	 */
	private static double ReLU(double z)
	{
		if (z <= 0)
			return 0;
		return z;
	}
	
	/**
	 * This the derivative of the Rectified Linear Unit function (ReLU)
	 * If the input is lower than or equal to 0, the output is 0
	 * If the input is greater than 0, the output is 1
	 * 
	 * @param z	Input
	 * @return	Output
	 */
	private static double ReLUDerivative(double z)
	{
		if (z <= 0)
			return 0;
		return 1;
	}
	
	/**
	 * This is the logistic function
	 * Outputs are contained between 0 and 1
	 * 
	 * @param z Input of the function
	 * @return Output of the function
	 */
	private static double sigmoidFunction(double z)
	{
		return 1.0 / (1.0 + Math.exp(-z));
	}
	
	/**
	 * This is the derivative of the logistic function
	 * Outputs are contained between 0 and 1
	 * 
	 * @param z Input of the derivative
	 * @return	Output of the derivative
	 */
	private static double sigmoidFunctionDerivative(double z)
	{
		return sigmoidFunction(z) * sigmoidFunction(-z);
	}
}
