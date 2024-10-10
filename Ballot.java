//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2024
//
// Author:   Evan Alexander
// Email:     elalexander@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Justin Angara
// Partner Email:   jangara@wisc.edu
// Partner Lecturer's Name: Hoobes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _/_ Write-up states that pair programming is allowed for this assignment.
//   _/_ We have both read and understand the course Pair Programming Policy.
//   _/_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Ballot {
    private static boolean ballotsCreated = false;  
    private static ArrayList<Election> elections = new ArrayList<Election>();
    private boolean[] hasVoted;

    /**
     * constructor method of the class Ballot
     *
     * @return void
     */
    public Ballot() {
        if (elections.isEmpty()) {
            throw new IllegalStateException("No elections yet nerd.");
        }
        
        this.hasVoted = new boolean[elections.size()];
        ballotsCreated = true;  
    }

    /**
     * mutator to the election arraylist to add new election object
     *
     * @param Election election 
     * @return void
     **/
    public static void addElection(Election election) {
      //checking edge cases with exception throwing
        if (ballotsCreated) {
            throw new IllegalStateException("cant add ballots created nerd.");
        }

        // iterates through elections arraylist and check if it equals one another
        for(int i = 0; i < elections.size(); i++) {
          if(elections.get(i).equals(election)) {
            throw new IllegalArgumentException("This election is already present nerd.");
          }
        }
        
        //adding the new election to the array
        elections.add(election);
    }

    /**
     * mutator to the private boolean list to see 
     * if a vote has occurred for each election
     *
     * @param String seatname 
     * @return boolean true if voted false is not
     **/
    public boolean hasVoted(String seatName) {
      //looping through the elections array
        for (int i = 0; i < elections.size(); i++) {
          // just in case
          if(elections.get(i)==null) {break;}
          
          //parsing the seat to something we can compare
            String seat = elections.get(i).SEAT_NAME;
            //comparing seatname
            if (seat.equals(seatName)) {
                return hasVoted[i];  
            }
        }
        throw new NoSuchElementException("No such election for the seat name: "
        + seatName + " nerd");
    }

    /**
     * Adds a vote to the given candidate for the specified election.
     *
     * @param seatName the name of the election seat to vote for
     * @param candidate the candidate to vote for
     * @throws NoSuchElementException if the election or candidate is not found
     */
    public void vote(String seatName, Candidate candidate) {
      
        // Loop through the array of elections
        for (int i = 0; i < elections.size(); i++) {
            Election election = elections.get(i);

            // Check if the seat name matches the election's seat name
            if (election.SEAT_NAME.equals(seatName)) {
                // Ensure the vote hasn't yet occurred
                if (hasVoted[i]) {

                    throw new IllegalStateException("This ballot has already voted in this election.");
                }

                // Try to vote for the candidate in the election
                try {
                    election.vote(candidate);
                    hasVoted[i] = true; // Mark that a vote has been made
                    return;
                } catch (NoSuchElementException e) {
                    
                    throw new NoSuchElementException("Candidate not found in the election for seat: " + seatName);
                }
            }
        }

        // If no election with the given seatName is found
        throw new NoSuchElementException("No such election for the seat name: " + seatName);
    }
    public static void clearElections() { 
      // empties the elections arraylist and resets ballotsCreated, for testing purposes only 
      elections = new ArrayList<Election>();
      ballotsCreated = false;
      
    }


    /**
     * overriding the toString method in object to a more specialized one
     * that gives us all the information about each election
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        //looping through the elections
        for (int i = 0; i < elections.size(); i++) {
          //putting together the print statement
          //adding seatname
            result.append(elections.get(i).toString().split("\n")[0]) 
            //adding if the vote as  for this ballot
                  .append(": ")
                  .append(hasVoted[i])  
                  .append("\n");
        }
        //trimming any excess spaces
        return result.toString().trim();  
    }
}