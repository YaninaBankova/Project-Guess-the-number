package edu.smg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessTheNumber {
	
	static int lastGuess = -1;
	static int randomNumber = 0;
	static int guessesMade = 0;
	
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
		rangeLabel.setVisible(false);
		
		JLabel lastNumberLabel = new JLabel("");
		lastNumberLabel.setBounds(100, 70, 160, 15);
		panel.add(lastNumberLabel);
		
		JLabel guessesLeftLabel = new JLabel("");
		guessesLeftLabel.setBounds(320, 70, 110, 15);
		panel.add(guessesLeftLabel);
		
		JLabel compareLabel = new JLabel("");
		compareLabel.setBounds(100, 100, 300, 15);
		panel.add(compareLabel);
		
		JLabel lostGame = new JLabel("Too many tries. you lose!");
		lostGame.setBounds(100, 120, 200, 15);
		panel.add(lostGame);
		lostGame.setVisible(false);
		
		JTextField usersGuess = new JTextField();
		usersGuess.setBounds(190, 150, 30, 20);
		panel.add(usersGuess);
		usersGuess.setVisible(false);
		
		
		
		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(240, 150, 70, 20);
		panel.add(guessButton);
		guessButton.setVisible(false);
		

		JButton playButton = new JButton("Play");
		playButton.setBounds(230, 180, 110, 20);
		panel.add(playButton);
		
		
		guessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				playButton.setText("Play again");
				int guessedNumber = Integer.parseInt(usersGuess.getText());
				usersGuess.setText(null);
				setGuessesMade();
				guessesLeftLabel.setText("Guesses left: " + (7 - guessesMade));
				guessesLeftLabel.setVisible(true);
				setLastNumber(guessedNumber);
				lastNumberLabel.setText("Guessed number:  " + lastGuess);
				compareLabel.setVisible(true);
				lastNumberLabel.setVisible(true);
				if(guessedNumber > randomNumber) {
					compareLabel.setText("The actual number is lower");
				} else if(guessedNumber < randomNumber) {
					compareLabel.setText("The actual number is higher");
				} else {
					compareLabel.setText("You guessed it!");
					guessButton.setVisible(false);
					usersGuess.setVisible(false);
					playButton.setVisible(true);
					
				}
				if(guessesMade == 7 && guessedNumber != randomNumber) {
					guessButton.setVisible(false);
					usersGuess.setVisible(false);
					playButton.setVisible(true);
					lostGame.setVisible(true);
				}
			}
		});
		
		
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateRandomNumber();
				guessesMade = 0;
				guessButton.setVisible(true);
				rangeLabel.setVisible(true);
				usersGuess.setVisible(true);
				lostGame.setVisible(false);
				lastNumberLabel.setVisible(false);
				compareLabel.setVisible(false);
				guessesLeftLabel.setVisible(false);
				playButton.setVisible(false);
			}
		});
		
		
		
		frame.setVisible(true);

	}
	public static void setGuessesMade() {
		guessesMade++;
	}
	public static void setLastNumber(int number) {
		lastGuess = number;
	}
	public static void generateRandomNumber() {
		randomNumber = (int)(Math.random() * 100);
	}
}
