package Frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
//import javax.swing.UIManager;

import Backend.Juego;

public class Interface {

	private JFrame frame;
	private Juego juego;
	
	private JLabel PLAYER;
	private JLabel TURN;
	
	private HashMap<int[], JButton> CELLS;
	private int[] C00 = {0,0};
	private int[] C01 = {0,1};
	private int[] C02 = {0,2};
	private int[] C10 = {1,0};
	private int[] C11 = {1,1};
	private int[] C12 = {1,2};
	private int[] C20 = {2,0};
	private int[] C21 = {2,1};
	private int[] C22 = {2,2};
	
	private String FONT_TEXT = "Tahoma";
	private String FONT_ITEM = "Papyrus";
	private int TYPE_FONT = Font.BOLD;
	private Color COLOR_CELL_INACTIVE = Color.LIGHT_GRAY;
	private Color COLOR_CELL_ACTIVE = Color.WHITE;

	/* Launch the application. */
	public static void main(String[] args) {		
//		try {			
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Create the application. */	
	public Interface() {
		initialize();		
	}
	
	private void initialize() {		
		juego = new Juego();
		CREATE_FRAME();
		CREATE_STATIC_TEXT();
		CREATE_CELLS();
		CREATE_PLAYER();
		CREATE_TURN();		
		ACTIONS_CELLS();		
	}
	
	
	private void CREATE_FRAME() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
	}
	private void CREATE_STATIC_TEXT() {
		JLabel TEXT_PLAYER = new JLabel("PLAYER");
		TEXT_PLAYER.setBounds(140, 11, 100, 19);
		TEXT_PLAYER.setToolTipText("");
		TEXT_PLAYER.setFont(new Font(FONT_TEXT, TYPE_FONT, 20));
		TEXT_PLAYER.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(TEXT_PLAYER);
		
		JLabel TEXT_TURN = new JLabel("Turno ");
		TEXT_TURN.setBounds(30, 430, 77, 27);
		TEXT_TURN.setFont(new Font(FONT_TEXT, TYPE_FONT, 15));
		TEXT_TURN.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(TEXT_TURN);
	}
	
	private void CREATE_CELLS() {
		CELLS = new HashMap<int[],JButton>();
		CELLS.put(C00,new JButton() );
		CELLS.put(C01,new JButton() );
		CELLS.put(C02,new JButton() );
		CELLS.put(C10,new JButton() );
		CELLS.put(C11,new JButton() );
		CELLS.put(C12,new JButton() );
		CELLS.put(C20,new JButton() );
		CELLS.put(C21,new JButton() );
		CELLS.put(C22,new JButton() );
		
		CELLS.get(C00).setBounds(30, 86, 100, 100);
		CELLS.get(C01).setBounds(140, 86, 100, 100);
		CELLS.get(C02).setBounds(250, 86, 100, 100);
		CELLS.get(C10).setBounds(30, 197, 100, 100);
		CELLS.get(C11).setBounds(140, 197, 100, 100);
		CELLS.get(C12).setBounds(250, 197, 100, 100);
		CELLS.get(C20).setBounds(30, 308, 100, 100);
		CELLS.get(C21).setBounds(140, 308, 100, 100);
		CELLS.get(C22).setBounds(250, 308, 100, 100);
	}
	

	private void CREATE_PLAYER() {
		PLAYER = new JLabel("X");
		PLAYER.setBounds(177, 41, 24, 19);
		PLAYER.setToolTipText("X");
		PLAYER.setHorizontalAlignment(SwingConstants.CENTER);
		PLAYER.setFont(new Font(FONT_TEXT, TYPE_FONT, 20));
		frame.getContentPane().add(PLAYER);		
	}
	
	private void CREATE_TURN() {
		TURN = new JLabel("1");
		TURN.setBounds(83, 430, 47, 27);
		TURN.setHorizontalAlignment(SwingConstants.CENTER);
		TURN.setFont(new Font(FONT_TEXT, TYPE_FONT, 15));
		frame.getContentPane().add(TURN);	
	}		
	
	private void ACTIONS_CELLS () {
		for (HashMap.Entry<int[] , JButton> celda : this.CELLS.entrySet()) {		
			celda.getValue().setBackground(COLOR_CELL_INACTIVE);
			celda.getValue().setFont(new Font(FONT_ITEM, TYPE_FONT, 55));
			celda.getValue().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					play(celda.getValue(),celda.getKey()[0],celda.getKey()[1]);
				}
			});
			frame.getContentPane().add(celda.getValue());
		}
	}
	
	private void endGame(String message) {
		try {
			int repeat  = JOptionPane.showConfirmDialog(frame, message +"\n Play again ", "Finish", JOptionPane.YES_NO_OPTION);
			if(repeat == 1)
				System.exit(0);
			else
				frame.dispose();
				Interface.main(null);									
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void play(JButton CELL,int x ,int y) {
		juego.play(x,y);
		CELL.setText( this.PLAYER.getToolTipText());
		CELL.setEnabled(false);
		CELL.setBackground(COLOR_CELL_ACTIVE);				
		
		if(juego.end()) 
			endGame(juego.finishMessage());
			
		this.TURN.setText(juego.viewTurn());
		this.PLAYER.setText(juego.itemPlayer());
		this.PLAYER.setToolTipText(juego.itemPlayer());
	}
	
}
