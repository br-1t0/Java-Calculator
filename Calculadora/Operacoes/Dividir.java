package Calculadora.Operacoes;
import Calculadora.Operacao;

public class Dividir implements Operacao {

	public double Calcular(double a, double b) {
		
	if (b==0) {
		throw new ArithmeticException("Operação Inválida");
	}
		return (a/b);
	}
	
	
	
}
