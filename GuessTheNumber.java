package edu.smg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessTheNumber {
	
	static int lastGuess = -1;
	static int level = 1;
	static int upperLimit;
	static int maxGuesses;
	static int randomNumber = 0;
	static int guessesMade = 0;
	static int points = 0;
	static int highestScore = 0;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Guess the number");
		frame.setSize(600, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel bestScoreLabel = new JLabel("");
		panel.add(bestScoreLabel);
		bestScoreLabel.setBounds(30, 45, 200, 15);
		
		JLabel newBestScore = new JLabel("");
		panel.add(newBestScore);
		newBestScore.setBounds(100, 130, 200, 15);
		
		
		JLabel nameLabel = new JLabel("GUESS THE NUMBER");
		panel.add(nameLabel);
		//nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setBounds(230, 10, 140, 15);
		
		JLabel levelLabel = new JLabel("");
		levelLabel.setBounds(30, 30, 55, 15);
		panel.add(levelLabel);
		
		JLabel rangeLabel = new JLabel("");
		rangeLabel.setBounds(185, 30, 250, 15);
		panel.add(rangeLabel);
		
		JLabel lastNumberLabel = new JLabel();
		lastNumberLabel.setBounds(100, 70, 160, 15);
		panel.add(lastNumberLabel);
		
		JLabel guessesLeftLabel = new JLabel();
		guessesLeftLabel.setBounds(320, 70, 110, 15);
		panel.add(guessesLeftLabel);
		guessesLeftLabel.setVisible(false);
		
		JLabel compareLabel = new JLabel();
		compareLabel.setBounds(100, 100, 300, 15);
		panel.add(compareLabel);
		
		JLabel lostGame = new JLabel("Too many tries. you lose!");
		lostGame.setBounds(100, 120, 200, 15);
		panel.add(lostGame);
		lostGame.setVisible(false);
		
		JLabel pointsLabel = new JLabel();
		pointsLabel.setBounds(20, 140, 80, 15);
		panel.add(pointsLabel);
		pointsLabel.setVisible(false);
		
		JTextField usersGuess = new JTextField();
		usersGuess.setBounds(160, 150, 50, 20);
		panel.add(usersGuess);
		usersGuess.setVisible(false);
		
		
		
		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(240, 150, 70, 20);
		panel.add(guessButton);
		guessButton.setVisible(false);
		

		JButton playButton = new JButton("Play");
		playButton.setBounds(230, 180, 110, 20);
		panel.add(playButton);
		
		JButton giveUpButton = new JButton("Give up");
		giveUpButton.setBounds(230, 201, 119, 20);
		panel.add(giveUpButton);
		giveUpButton.setVisible(false);
		
		
		guessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int guessedNumber;
				try{
					guessedNumber = Integer.parseInt(usersGuess.getText());
				} catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Invalid input. Try again!", "Error", 0);
					usersGuess.setText(null);
					return;
				}
				guessedNumber = Integer.parseInt(usersGuess.getText());
				usersGuess.setText(null);
				guessesMade++;
				guessesLeftLabel.setText("Guesses left: " + (maxGuesses - guessesMade));
				guessesLeftLabel.setVisible(true);
				lastGuess = guessedNumber;
				lastNumberLabel.setText("Guessed number:  " + lastGuess);
				compareLabel.setVisible(true);
				lastNumberLabel.setVisible(true);
				if(guessedNumber > randomNumber) {
					compareLabel.setText("The actual number is lower");
				} else if(guessedNumber < randomNumber) {
					compareLabel.setText("The actual number is higher");
				} else {
					compareLabel.setText("You guessed it!");
					level++;
					points = (int)(upperLimit / guessesMade);
					pointsLabel.setText("Points: " + points);
					guessButton.setVisible(false);
					giveUpButton.setVisible(false);
					usersGuess.setVisible(false);
					playButton.setText("Next level");
					playButton.setVisible(true);
					
				}
				if(guessesMade == maxGuesses && guessedNumber != randomNumber) {
					
					
					File myGame = new File("guessinggame.txt");
					try {
						Scanner myReader = new Scanner(myGame);
						while(myReader.hasNextLine()) {
							highestScore = myReader.nextInt();
						}
						myReader.close();
					} catch (FileNotFoundException e1) {
						try {
							myGame.createNewFile();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}
					
					if(highestScore <= points) {
						newBestScore.setText("Congrats! That's a new record!");
						newBestScore.setVisible(true);
						try {
							FileWriter myWriter = new FileWriter("guessinggame.txt", true);
							myWriter.write(points + "\n");
							myWriter.close();
						} catch (IOException e3) {
							System.out.println("An error occurred.");
							e3.printStackTrace();
						}
					}
					
					
					points = 0;
					level = 1;
					playButton.setText("Play again");
					guessButton.setVisible(false);
					usersGuess.setVisible(false);
					playButton.setVisible(true);
					lostGame.setVisible(true);
					giveUpButton.setVisible(false);
				}
			}
		});
		
		
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(level) {
					case 1: upperLimit = 10; break;
					case 2: upperLimit = 50; break;
					case 3: upperLimit = 100; break;
					case 4: upperLimit = 250; break;
					case 5: upperLimit = 500; break;
					case 6: upperLimit = 1000; break;
					case 7: upperLimit = 5000; break;
					case 8: upperLimit = 10_000; break;
					case 9: upperLimit = 50_000; break;
					default: upperLimit = 100_000;
				}
				randomNumber = (int)(Math.random() * upperLimit);
				maxGuesses = (int)(Math.log(upperLimit) / Math.log(2)) + 1;
				if(level > 11) {
					maxGuesses -= (int)(level / 10);
					if (maxGuesses < 3) {
						maxGuesses = 2;
					}
				}
				guessesMade = 0;
				pointsLabel.setText("Points: " + points);
				levelLabel.setText("Level: " + level);
				rangeLabel.setText("Enter a number between 0 and " + upperLimit);
				guessesLeftLabel.setText("Guesses left: " + (maxGuesses - guessesMade));
				guessesLeftLabel.setVisible(true);
				guessButton.setVisible(true);
				
				newBestScore.setVisible(false);
				usersGuess.setVisible(true);
				lostGame.setVisible(false);
				lastNumberLabel.setVisible(false);
				compareLabel.setVisible(false);
				playButton.setVisible(false);
				giveUpButton.setVisible(true);
				pointsLabel.setVisible(true);
				
				
				File myGame = new File("guessinggame.txt");
				try {
					Scanner myReader = new Scanner(myGame);
					while(myReader.hasNextLine()) {
						highestScore = myReader.nextInt();
					}
					myReader.close();
				} catch (FileNotFoundException e1) {
					try {
						myGame.createNewFile();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				bestScoreLabel.setText("Highest score: " + highestScore);
			}
		});
		
		giveUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButton.setText("Play again");
				guessButton.setVisible(false);
				usersGuess.setVisible(false);
				playButton.setVisible(true);
				giveUpButton.setVisible(false);
				
				File myGame = new File("guessinggame.txt");
				try {
					Scanner myReader = new Scanner(myGame);
					while(myReader.hasNextLine()) {
						highestScore = myReader.nextInt();
					}
					myReader.close();
				} catch (FileNotFoundException e1) {
					try {
						myGame.createNewFile();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				
				if(highestScore <= points) {
					newBestScore.setText("Congrats! That's a new record!");
					newBestScore.setVisible(true);
					try {
						FileWriter myWriter = new FileWriter("guessinggame.txt", true);
						myWriter.write(points + "\n");
						myWriter.close();
					} catch (IOException e3) {
						System.out.println("An error occurred.");
						e3.printStackTrace();
					}
				}
				
				points = 0;
				level = 1;
			}
		});
		
		
		frame.setVisible(true);

	}
}
