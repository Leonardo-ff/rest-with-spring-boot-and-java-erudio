package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private static AtomicLong counter = new AtomicLong();
//	o AtomicLong servirá aqui para mockar um id.
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method= RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sum(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/minus/{numberOne}/{numberTwo}",
			method= RequestMethod.GET)
	public Double minus(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.minus(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/multip/{numberOne}/{numberTwo}",
			method= RequestMethod.GET)
	public Double multip(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.multip(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}",
			method= RequestMethod.GET)
	public Double division(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/avg/{numberOne}/{numberTwo}",
			method= RequestMethod.GET)
	public Double avg(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.avg(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sqrt/{number}",
			method= RequestMethod.GET)
	public Double sqrt(
			@PathVariable(value = "number")String number
			) throws Exception {
		
		if(!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sqrt(NumberConverter.convertToDouble(number));
	}


}
