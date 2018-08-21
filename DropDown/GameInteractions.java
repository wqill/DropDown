package DropDown;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.List;
import java.util.*;

/**
 * Logic for how objects interact with one another.
 */
@SuppressWarnings("serial")
public class GameInteractions extends JPanel {

	private static final int PLAYER_VEL = 3;
	private static final int PLAYER_VEL_Y = 2;
	private static final int GRID_WIDTH = 300;
	private static final int GRID_HEIGHT = 400;	
	
	private Player player; // player being controlled by keyboard
	public boolean playing = false; // whether the game is running 
	private JLabel status; // Current status
	private JLabel scoreLabel; // current score text

	
	private int count = 0; // delay between creating platforms
	private int score = 0; // tracks score
	private List<Platform> platforms = new ArrayList<>(); // store platforms on screen
	private TreeMap<Integer, String> scoreMap; // map score to name
	private PlatformGenerator generator; // add platforms to the game
	private static final int INTERVAL = 6; // Update interval for timer, in milliseconds

	GameInteractions(JLabel status, JLabel scoreLabel) {

		setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around the playing area

		Timer timer = new Timer(INTERVAL, e -> tick());
		timer.start();

		setFocusable(true); // Enable keyboard focus on the playing area. 

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.setVelX(PLAYER_VEL);
				} else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
					player.setVelX(-PLAYER_VEL);
				}
			}
			public void keyReleased(KeyEvent k) {
				player.setVelX(0);
			}
		});

		HighScores hs = new HighScores("HighScores.txt");
		generator = new PlatformGenerator();
		scoreMap = new TreeMap<>(hs.getHS());

		this.scoreLabel = scoreLabel;
		this.status = status;
	}

	/**
	 * Initial state
	 */
	public void reset() {
		player = new Player(GRID_WIDTH, GRID_HEIGHT, Color.BLACK, true);

		platforms.clear();
		platforms.add(generator.next());
		playing = true;
		score = 0;
		count = 0;
		status.setText("Status: Processing...");
		scoreLabel.setText("Score: 0");
		requestFocusInWindow();
	}

	/**
	 * Called when timer triggers
	 */
	private void tick() {
		if (playing) {

			player.move();
			for(Platform p : platforms){
				p.moveOut();
			}
			//removes the platforms not in grid
			platforms.removeIf((Platform p) -> !p.inGrid());

			//check if player on platform. Perform the action given by that specific platform's type if on.
			boolean onP = false;
			for(Platform p : platforms){
				if (p.hasPlayer(player)){
					p.perform(player);
					onP = true;
				}
			}
			count++;

			//generate platforms
			if(count % 60 == 0){
				platforms.add(generator.next());
				count = 0;
			}

			//Speed when falling/off platforms.
			if(!onP){
				player.setVelY(PLAYER_VEL_Y);
			}

			//iterate & update score
			if (count % 10 == 0){
				score++;
				scoreLabel.setText("Score: " + score);
			}
			// check for the game end conditions
			if ( player.getPosY() == 0 || player.getPosY() >= 369 || !player.getActive()) {
				playing = false;
				status.setText("Status: You've lost!");
				String inputValue;

				if((scoreMap.size() < 3) || score > scoreMap.firstKey()){
					inputValue = JOptionPane.showInputDialog("Congratulations! You scored a High Score. \n\n" + "Enter your name:");
					if (inputValue != null) addHighScore(score, inputValue);
				}
			}
			//update display
			repaint();
		}
	}

	//adds a high score
	private void addHighScore(int score, String name){
		if(name.length() > 15){
			name = name.substring(0, 14);
		}
		scoreMap.put(score, name);
		if(scoreMap.size() > 5){
			int lowest = scoreMap.firstKey();
			scoreMap.remove(lowest);
		}
		HighScores.writeHS(scoreMap);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(GRID_WIDTH, GRID_HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.draw(g);
		for(Platform p : platforms){
			p.draw(g);
		}
	}


}