package Calculadora;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Calculadora.Operacoes.*;

//import java.awt.Dimension;
//Java AWT (Abstract Window Toolkit) 
import javax.swing.*;

public class CalcGUI {
 JButton[] n_Buttons = new JButton[10]; 
 JButton[] op_Buttons = new JButton[4]; 
 Character[] op_Simbolos = new Character[]{'+','-','x','/'};
 //HashMap< Integer,Character> op_MapButtons = new HashMap<Integer,Character>();
 // importar as operações
  Dividir Div = new Dividir();
  Multiplicar Mult = new Multiplicar();
  Somar Som = new Somar();
  Subtrair Sub = new Subtrair();
	
	
	public void criarGUI() {
	

		// --------- tela ---------------------- //
		JFrame frame = new JFrame("Calculadora muito doida uau que legal");	
		frame.setDefaultCloseOperation(3); // recebe ou o nome da operação de fechar ou seu index, o 3 é o exit on close
		frame.setMinimumSize(new Dimension(500,800));
		
		frame.pack();  // basicamente um flexbox (adapta o tamanho da tela de acordo com seus subcomponentes)
        frame.setVisible(true);
        JPanel p_Main = new JPanel();
		frame.add(p_Main,BorderLayout.CENTER);
		JPanel op_Main = new JPanel();
		frame.add(op_Main,BorderLayout.EAST);
       
        // --------- tela ---------------------- //
        
		//----------- botões criados ----------- //
		for (int i = 0; i<10; i++) {
			n_Buttons[i] = new JButton(""+i);
			//n_Buttons[i].setBounds(10, 10, 20, 10);
			p_Main.add(n_Buttons[i]);
		};
		
		for (int i = 0; i<4; i++) {
		//char button_Label =	op_MapButtons.put(i, op_Simbolos[i]);	
		op_Buttons[i] = new JButton(""+op_Simbolos[i]);

		op_Main.add(op_Buttons[i]);
		
		};
		
		JButton b_Ponto = new JButton(".");
	
		JButton e_Ponto = new JButton("=");
		JButton c_Ponto = new JButton("C");
		p_Main.add(b_Ponto);
		p_Main.add(e_Ponto);
		p_Main.add(c_Ponto);
		
		// --------  botões criados ------------ //
		
		//---------- layout -------------------- //
				JTextField result = new JTextField();
				result.setEditable(false);
				result.setPreferredSize(new Dimension(300, 80));
				frame.add(result, BorderLayout.NORTH);
				GridLayout main_Layout = new GridLayout(4,4,2,2);
				p_Main.setLayout(main_Layout);
				GridLayout left_Layout = new GridLayout(4,1,2,2);
		        op_Main.setLayout(left_Layout);
		        
		        //BorderLayout frame_Layout = new BorderLayout();
		      //  frame.setLayout(frame_Layout);
		 //---------- layout -------------------- //
		        
		        
		//--------- funções dos botões --------//
		  // ActionListener action_Botoes = new ActionListener();
		        
		b_Ponto.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				String numAtual = result.getText();
				result.setText(numAtual + "."); //("ola" + 8.8) = "ola 8.8"

				//System.out.println("bota ponto");
			}
			
		});
		
		
		
		 //------------ parse ------------------// 
		
		e_Ponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> celulaText = new ArrayList<String>(); // guardar os caracteres em formato de string
				StringBuilder numeros = new StringBuilder(); // separar em caracteres
				double resultadoFinal = 0;
				
				for (char c :result.getText().toCharArray()) {
					if (Character.isDigit(c) || c =='.') {
						numeros.append(c);
					}
					else {
						if(numeros.length()>0) {
						celulaText.add(numeros.toString());
						numeros.setLength(0);
						
					}
					celulaText.add(String.valueOf(c));
				}
					
					}
				
				if (numeros.length() > 0) {
				    celulaText.add(numeros.toString());
				}
				
		
			  
			/*for (int i = 0; i < celulaText.size(); i+=2) {
				
				double a = Double.parseDouble(celulaText.get(i)) ;
				String c = celulaText.get(i+1);
				double b = Double.parseDouble(celulaText.get(i+2));
				switch (c) {
				case "+": resultadoFinal = Som.Calcular(a, b);
					break;
				case "-": resultadoFinal = Sub.Calcular(a, b);
					break;
				case "x": resultadoFinal = Mult.Calcular(a, b);	
					break;
				case "/": resultadoFinal = Div.Calcular(a, b);
					break;
				
				}
				
				
			}*/														// {'44.5'   '+'    '32'    '/'  '2'}
				double a = Double.parseDouble(celulaText.get(0));  // 44.5
				String c = null;								   // +					
				
				for (String str : celulaText) {
					
					if(str.equals("+") || str.equals("-") || str.equals("x") || str.equals("/")) {
						c = str;
					}
					else {
						
						if(c !=null) {
							double b = Double.parseDouble(str);	
							switch (c) {
							case "+": resultadoFinal = Som.Calcular(a, b);
								break;
							case "-": resultadoFinal = Sub.Calcular(a, b);
								break;
							case "x": resultadoFinal = Mult.Calcular(a, b);	
								break;
							case "/": resultadoFinal = Div.Calcular(a, b);
								break;
							
							}
							c = null;
						}
						
					}
		            //System.out.println(str);
		        }
				
			result.setText(""+resultadoFinal);
		//	System.out.println("da o resultado");
			
				
			}
			
		});
		
		//------------ parse ------------------// 
		
		
		
		
		c_Ponto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				result.setText("");
				//System.out.println("da clear");
			}
			
		});
		
		
		for (JButton jButton : n_Buttons) {
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String numAtual = result.getText();
					result.setText(numAtual + jButton.getText());
					//System.out.println("bota 0");
				}
				
			});
		}
		
		for (JButton jButton : op_Buttons) {
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String numAtual = result.getText();
					
					/*String numAntigo  = result.getText();
					String numAtual = numAntigo.replaceAll("[^0-9.]","");
					
					double d_numAtual = Double.parseDouble(numAtual);
					d_numeros.add(d_numAtual);
					numAtual = "";
					switch(jButton.getText()) {
					case "+" -> Somar(d_numAtual,5);
					}*/
					result.setText(numAtual+ jButton.getText());
				}
			});
		}
		
		
		//--------- funções dos botões --------//
		
      
       
	}
 	
	

}
