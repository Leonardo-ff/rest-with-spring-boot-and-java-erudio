package br.com.erudio.math;

public class SimpleMath {


	public Double sum(Double numberOne,Double numberTwo) {
		return numberOne + numberTwo;
	}

	public Double minus(Double numberOne,Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public Double multip(Double numberOne,Double numberTwo)  {
		return numberOne * numberTwo;
	}

	public Double division(Double numberOne,Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double avg(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2.0;
	}
	
	public Double sqrt(Double number) {
		return Math.sqrt(number);
	}
}
