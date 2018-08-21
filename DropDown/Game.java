package DropDown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Game implements Runnable{

	public void run() {
		final JFrame frame = new JFrame("DropDown");
		frame.setLocation(300, 100);

		// Reset button
		final JPanel Control_Panel = new JPanel();
		frame.add(Control_Panel, BorderLayout.NORTH);

		// Score Label
		final JLabel Score_Label = new JLabel("Score: 0");
		Control_Panel.add(Score_Label); 

		// Status panel
		final JPanel Status_Panel = new JPanel();
		frame.add(Status_Panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Status: Processing...");
		Status_Panel.add(status);

		// Instructions panel
		final JPanel Instruction_Panel = new JPanel();
		frame.add(Instruction_Panel, BorderLayout.EAST);

		// High Scores panel
		final JPanel Score_Panel = new JPanel();
		Score_Panel.setPreferredSize(new Dimension(130,300));
		frame.add(Score_Panel, BorderLayout.WEST);

		// Playing area
		final GameInteractions grid = new GameInteractions(status, Score_Label);
		frame.add(grid, BorderLayout.CENTER);

		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.reset();
			}
		});
		Control_Panel.add(reset);

		//Instructions button
		final JButton instructionButton = new JButton("Instructions");
		instructionButton.setPreferredSize(new Dimension(120,60));
		Instruction_Panel.add(instructionButton);
		instructionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean wasPlaying = grid.playing;
				grid.playing = false;
				JOptionPane.showMessageDialog(frame,
						"Don't let the penguin touch the top or bottom of the screen!.\n" +
								"Be careful! The spikes are dangerous and the ice platforms disappears. \n\n" +
								"Controls: Move the penguin left and right using the arrow keys.\n" +
								"Your score increases over time. How long will you last?",
								"Instructions", JOptionPane.PLAIN_MESSAGE);
				if(wasPlaying) grid.playing = true;
				grid.requestFocusInWindow();
			}
		});
		
		//High scores button
		final JButton scoreButton = new JButton("High Scores");
		scoreButton.setPreferredSize(new Dimension(120,60));
		scoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean wasPlaying = grid.playing;
				grid.playing = false;
				HighScores h = new HighScores("HighScores.txt");
				Map<Integer, String> highScores = h.getHS();
				List<Integer> scoreList = new ArrayList<Integer>(highScores.keySet());
				Collections.sort(scoreList);
				Collections.reverse(scoreList);
				String highScoreText = "";

				if (highScores.size() > 0) {
					for (int i : scoreList) {
						highScoreText = highScoreText + "\t" + highScores.get(i) + ":\t" + Integer.toString(i) + "\n";
					}
					JOptionPane.showMessageDialog(frame, highScoreText, "High Scores", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "There are currently no high scores!",
							"High Scores", JOptionPane.PLAIN_MESSAGE);
				}
				grid.requestFocusInWindow();
				if (wasPlaying) grid.playing = true;
			}
		});
		Score_Panel.add(scoreButton);

		// frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// Start game
		grid.reset();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
