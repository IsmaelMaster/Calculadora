//Programa de una calculadora
//Integrantes: 
//Baizabal acosta Ismael
//Cruz Mendoza Lucero
package Calculadoranueva;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculadora {
    private static JTextField pantalla; // Campo de texto para la calculadora
    private static double numero1, numero2, resultado; //Son los nombres de mis variables
    private static String operador = ""; //

    public static void main(String[] args) {
        JFrame frame = new JFrame("CALCULADORA"); //El nombre de mi ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cuando se aprieta el voton de salir, se cierra todo
        frame.setSize(350, 450); //Medidas de mi ventana ancho y largo
        frame.setLayout(new BorderLayout()); //Este linea me acomoda mis componentes por la ventana

        // Pantalla de la calculadora
        pantalla = new JTextField(); //Pusimos un campo de texto en la ventana
        pantalla.setEditable(false); //No se pude editar lo que hay dentro del cuadro de texto
        pantalla.setHorizontalAlignment(JTextField.RIGHT); //Todo lo que se introdusca en el cuadro de texto se va a alinear a la derecha
        pantalla.setFont(new Font("Arial", Font.BOLD, 20)); //Este es el tipo de letra que va a tener dentro del campo de texto
        frame.add(pantalla, BorderLayout.NORTH); //Va a colocar el JTextFiel al norte de la ventana

        // Panel principal con GridBagLayout
        JPanel panel1 = new JPanel(new GridBagLayout()); //Cree mi panel donde se van a colocar los botones
        GridBagConstraints gbc = new GridBagConstraints(); //Esto va a definir la locacion de mis componentes por toda la ventana
        gbc.fill = GridBagConstraints.BOTH; //Esto sirve para que los componentes ocupen lo maximo de espacio en horizontal y vertical.
        gbc.insets = new Insets(5, 5, 5, 5); //Esro define que tanto espacio va a ocupar (superior, izquierdo, inferior y derecho)

        // Array con los botones de la calculadora
        String[] botones = {
            "C", "/", "*", "-",
            "7", "8", "9", "+",
            "4", "5", "6", "=",
            "1", "2", "3",
            "0", ".", "%"
        };
        

        // Crear e implementar botones
        int fila = 0, columna = 0; //Aqui se inicializa los valores que van desde arrriba en la izquierda de la ventana
        for (String texto : botones) { //Esto hace que cadainterracion tome diferentes valores la variable texto
            JButton boton = new JButton(texto); //Se crearon los botones que al prcionarlos va a dar un texto
            boton.setFont(new Font("Arial", Font.BOLD, 18)); //Este es el estilo de letra que van a tener los botones
            
            //Esta es la accion que se va a realizar cuando se presiona un boton
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    procesarEntrada(e.getActionCommand());
                }
            });
            
            //Aqui dice que los valores de columna y fila son iguales que gbc.gridx y gbc.gridy.
            gbc.gridx = columna;
            gbc.gridy = fila;
            
            // Ajustes especiales para botones grandes
            if (texto.equals("=")) {
                gbc.gridheight = 2;
                panel1.add(boton, gbc);
                gbc.gridheight = 1;
            } else if (texto.equals("0")) {
                gbc.gridwidth = 2; //Que tenga 2 columnas de ancho
                gbc.gridx = 0; // Lo posicionamos en la primera columna de la fila 0
                gbc.gridy = 4; // Lo colocamos en la fila 4
                panel1.add(boton, gbc);
                gbc.gridwidth = 1;
            } else if (texto.equals(".")) {
                gbc.gridwidth = 1; //Que tenga 1 columnas de ancho
                gbc.gridx = 2; // Lo posicionamos en la primera columna de la fila 3
                gbc.gridy = 4; // Lo colocamos en la fila 3
                panel1.add(boton, gbc);
            } else if (texto.equals("%")) {
                gbc.gridwidth = 1; //Que tenga 1 columnas de ancho
                gbc.gridx = 3; // Lo posicionamos en la primera columna de la fila 3
                gbc.gridy = 4; // Lo colocamos en la fila 4
                panel1.add(boton, gbc);
            } else {
                panel1.add(boton, gbc);
            }
            
            //Que cada columna tiene un minimo de 0 y un maximo de 3
            columna++;
            if (columna > 3) {
                columna = 0;
                fila++;
            }
        }

        frame.add(panel1, BorderLayout.CENTER); //Esto hace que todos los componentes se pongan en medio del JPanel
        frame.setVisible(true); //Esto hace que la ventana se visible
    }

    private static void procesarEntrada(String comando) {
        switch (comando) {
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de borrar
            case "C":
                pantalla.setText("");
                operador = "";
                numero1 = numero2 = resultado = 0;
                break;
                
            //Esto es lo que tiene que pasar cuando se presiona el boton de igual
            case "=":
                if (!pantalla.getText().isEmpty() && !operador.isEmpty()) {
                    numero2 = Double.parseDouble(pantalla.getText());
                    calcular();
                    pantalla.setText(String.valueOf(resultado));
                    operador = ""; // Reiniciar operador después de la operación
                }
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                
            //Esto es lo que tiene que pasar cuando se presiona el boton de porcentaje.
            case "%":
                if (!pantalla.getText().isEmpty()) {
                    numero1 = Double.parseDouble(pantalla.getText());
                    operador = comando;
                    pantalla.setText(""); // Limpiar para ingresar el segundo número
                }
                break;
            default: //Es cuando el usuario quiere poner un numero matematico
                pantalla.setText(pantalla.getText() + comando);
                break;
        }
    }

    private static void calcular() {
        switch (operador) {
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de suma.
            case "+":
                resultado = numero1 + numero2;
                break;
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de resta.
            case "-":
                resultado = numero1 - numero2;
                break;
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de multiplicacion.
            case "*":
                resultado = numero1 * numero2;
                break;
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de division.
            case "/":
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    pantalla.setText("Error");
                }
            
            //Esto es lo que tiene que pasar cuando se presiona el boton de porcentaje.
            case "%":
                resultado = (numero1 / 100) * numero2 ;
                
                break;
           
        }
    }
}

