package edu.smg;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessTheNumber {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Guess the number");
		frame.setSize(600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		
		JLabel nameLabel = new JLabel("GUESS THE NUMBER");
		nameLabel.setBounds(230, 10, 140, 15);
		panel.add(nameLabel);
		
		
		JLabel rangeLabel = new JLabel("Enter a number between 1 and 100");
		rangeLabel.setBounds(185, 30, 250, 15);
		panel.add(rangeLabel);
		
		JLabel lastNumberLabel = new JLabel("Last guessed number:  ");
		lastNumberLabel.setBounds(100, 70, 160, 15);
		panel.add(lastNumberLabel);
		
		JLabel guessesLeftLabel = new JLabel("Guesses left:  ");
		guessesLeftLabel.setBounds(320, 70, 110, 15);
		panel.add(guessesLeftLabel);
		
		JLabel compareLabel = new JLabel("The actual number is higher/lower./You guessed it!");
		compareLabel.setBounds(100, 100, 300, 15);
		panel.add(compareLabel);
		
		JTextField usersGuess = new JTextField();
		usersGuess.setBounds(190, 150, 30, 20);
		panel.add(usersGuess);
		
		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(240, 150, 70, 20);
		panel.add(guessButton);
		
		JButton giveUpButton = new JButton("Give up");
		giveUpButton.setBounds(235, 180, 85, 20);
		panel.add(giveUpButton);
		
		
		frame.setVisible(true);

	}
}
