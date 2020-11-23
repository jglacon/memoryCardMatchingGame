package juegoParejasFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JuegoParejasFinal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFrame frame, frame2;
	private JLabel textopuntos;
	private int sw; // opción switch
	private int a, b, c, d; // comprobaciones de cartas
	private int pto = 0, dim;
	private ImageIcon[] imagenes; // array con las imágenes
	private JButton[][] botones; // matriz de botones	
	private int[][] M; // matriz de númeres
	private boolean[][] X; // matriz de boolean

	// EJECUTAR EL MAIN - Comenzar el juego
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuegoParejasFinal window = new JuegoParejasFinal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Declarar una matriz de enteros con la que trabajaremos las parejas
	public void matriz(int d) {
		dim = d;
		M = new int[dim][dim];
		X = new boolean[dim][dim];
	}

	// Posición en la matriz
	public int obtenerPosicion(int i, int j) {
		return M[i][j];
	}

	// Posicion de boolean para comprobar los clics
	public boolean obtenerClic(int i, int j) {
		return X[i][j];
	}

	// Comprobamos el clic y se cambia su valor para controlar las pulsaciones
	public void clic(int i, int j) {
		X[i][j] = !X[i][j];
	}

	// Obtener la dimensión
	public int obtenerDimension() {
		return dim;
	}

	// Comprobar si el juego terminó
	public boolean juegoCompleto() {
		int c = 0;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (X[i][j])
					c++;
			}
		}
		if (c == dim * dim)
			return true;
		return false;
	}

	// Crear una matriz aleatoria
	public void generarAleatorio() {
		int g = 1;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				M[i][j] = g++;
				if (dim == 4) {
					if (g == 9) // parejas de la matriz más pequeña
						g = 1;
				}else if (dim == 6) {
					if (g == 19) // parejas de la matriz mediana
						g = 1;
				}else if (dim == 8) {
					if (g == 33) // parejas de la matriz más grande
						g = 1;
				}	
			}
		}
		int x, y;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				x = (int) (Math.random() * dim);
				y = (int) (Math.random() * dim);
				int aux = M[i][j];
				M[i][j] = M[x][y];
				M[x][y] = aux;
			}
		}
	}

	// Crear la aplicación
	public JuegoParejasFinal() {
		initialize();
		sw = 1; // entrar en el switch

	}

	// Initializar los contenidos del frame
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Juego de Parejas");
		frame.getContentPane().setBackground(new Color(186, 85, 211));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoParejasFinal.class.getResource("/imagenes/icon1.png")));
		frame.setBounds(100, 100, 734, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblJuegoDeParejas = new JLabel("");
		lblJuegoDeParejas.setIcon(new ImageIcon(JuegoParejasFinal.class.getResource("/imagenes/titulo.png")));
		lblJuegoDeParejas.setBackground(new Color(0, 0, 0));
		lblJuegoDeParejas.setForeground(new Color(255, 255, 255));
		lblJuegoDeParejas.setFont(new Font("OCR A Extended", Font.BOLD, 50));
		lblJuegoDeParejas.setBounds(44, 32, 640, 92);
		frame.setResizable(false);
		frame.getContentPane().add(lblJuegoDeParejas);

		JButton btnFcil = new JButton("Fácil - 4 x 4");
		btnFcil.setForeground(new Color(34, 139, 34));
		btnFcil.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnFcil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iniciarComponentes(4);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFcil.setBounds(242, 214, 230, 45);
		frame.getContentPane().add(btnFcil);

		JButton btnMedio = new JButton("Medio - 6 x 6");
		btnMedio.setForeground(Color.BLUE);
		btnMedio.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iniciarComponentes(6);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMedio.setBounds(242, 272, 230, 45);
		frame.getContentPane().add(btnMedio);

		JButton btnDifcil = new JButton("Difícil - 8 x 8");
		btnDifcil.setForeground(Color.RED);
		btnDifcil.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnDifcil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iniciarComponentes(8);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDifcil.setBounds(242, 330, 230, 45);
		frame.getContentPane().add(btnDifcil);

		JLabel lblNombre = new JLabel("Jennifer García Lacón - DA1D1E");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblNombre.setBounds(442, 471, 261, 23);
		frame.getContentPane().add(lblNombre);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(JuegoParejasFinal.class.getResource("/imagenes/fondo.jpg")));
		lblFondo.setBounds(0, 0, 728, 507);
		frame.getContentPane().add(lblFondo);
	}

	// Método para crear JPanel y JButton según el nivel seleccionado
	public void iniciarComponentes(int dimension) throws Exception {

		int d = dimension;
		matriz(d);
		generarAleatorio();

		// Cambiamos el frame para ejecutar el juego
		// Usamos GridLayout para trabajar con cuádricula en los paneles
		frame.setVisible(false);
		JPanel A = new JPanel(new GridLayout(d, d, 3, 3));
		JPanel B = new JPanel(new GridLayout(0, 3));
		frame2 = new JFrame();

		frame2.setTitle("Juego de Parejas");
		frame2.setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoParejasFinal.class.getResource("/imagenes/icon1.png")));
		frame2.setBounds(200, 00, 1024, 900);
		frame2.setResizable(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);

		// Añadimos los paneles al nuevo frame
		frame2.add(A, BorderLayout.NORTH);
		frame2.add(new JSeparator());
		frame2.add(B, BorderLayout.SOUTH);

		// Array con nombres de las imágenes
		imagenes = new ImageIcon[d * d / 2 + 1];
		imagenes[0] = null;
		for (int i = 1; i <= d * d / 2; i++) {
			imagenes[i] = new ImageIcon(getClass().getResource("/imagenes/" + i + ".png"));
		}
		// Como el frame es GridLayout podemos añadir botones con la matriz
		botones = new JButton[d][d];
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				botones[i][j] = new JButton();
				botones[i][j].addActionListener(this);
				botones[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenes/icon0.png")));
				A.add(botones[i][j]);
			}
		}

		// Añadir un contador de puntos en el panel B
		textopuntos = new JLabel("  Puntos:    " + pto);
		textopuntos.setForeground(new Color(255, 25, 255));
		textopuntos.setFont(new Font("Bahnschrift", Font.PLAIN, 28));
		B.add(textopuntos);
		
		B.add(new JLabel()); // Label vacío en el medio (estética)

		// Añadir un botón para reiniciar en el panel B
		JButton reiniciar = new JButton();
		reiniciar.setIcon(new ImageIcon(getClass().getResource("/imagenes/reiniciar.jpg")));
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame2.setVisible(false);
					JuegoParejasFinal window = new JuegoParejasFinal();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		B.add(reiniciar);
		frame2.pack(); // Ajustar los componentes a la ventana
	}

	/* COMPROBACIÓN DE LAS PAREJAS */
	public void accion(int x, int y) {

		// Inicializado en uno
		switch (sw) {
		// Primer caso: si no está la carta visible y se hace clic, se muestra su imagen y pasamos al siguiente caso
		case 1:
			if (!obtenerClic(x, y)) {
				clic(x, y);
				botones[x][y].setIcon(imagenes[obtenerPosicion(x, y)]);
				sw = 2;
				a = x;
				b = y;
			}
			break;
		// Segundo caso: si no está la carta visible y se hace clic, se muestra su imagen y se comprueba que sea la misma carta
		// Si es la misma carta volvemos al caso uno para reiniciar el proceso. Si es distinta carta pasamos al caso tres
		case 2:
			if (!obtenerClic(x, y)) {
				clic(x, y);
				botones[x][y].setIcon(imagenes[obtenerPosicion(x, y)]);
				c = x;
				d = y;
				if (obtenerPosicion(a, b) != obtenerPosicion(c, d)) {
					pto -= 5;
					textopuntos.setText("  Puntos:    " + pto);
					sw = 3;
				} else {
					sw = 1;
					if (obtenerPosicion(a, b) == obtenerPosicion(c, d)) {
						pto += 50;
						textopuntos.setText("  Puntos:    " + pto);
					}
				}
			}
			break;
		// Tercer caso: si no son la misma carta el boton vuelve a su estado inicial y volvemos al caso uno
		case 3:
			botones[a][b].setIcon(new ImageIcon(getClass().getResource("/imagenes/icon0.png")));
			botones[c][d].setIcon(new ImageIcon(getClass().getResource("/imagenes/icon0.png")));
			clic(a, b);
			clic(c, d);
			sw = 1;
			break;
		}
	}

	// Ejecutamos accion() y comprobamos si el juego está completo y se muestra un diálogo
	public void actionPerformed(ActionEvent ae) {
		int d = obtenerDimension();
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				if (botones[i][j] == ae.getSource()) {
					accion(i, j);
					if (juegoCompleto()) {
						JOptionPane.showMessageDialog(this,
								"¡Felicidades! Has completado el juego con " + pto + " puntos", "Juego terminado",
								JOptionPane.INFORMATION_MESSAGE, null);
						System.exit(0);
					}
					return;
				}
			}
		}
	}
}
