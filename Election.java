//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2024
//
// Author:   Justin Angara
// Email:    jangara@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    JEvan Alexander
// Partner Email:   elalexander@wisc.edu
// Partner Lecturer's Name: Hoobes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////


import java.util.NoSuchElementException;

/*
 * This class sets up the Candidates objects, where this class will be used for the ballot class
 * */

public class Election {
  // private instance variables
  private Candidate[] candidates;
  private int numCandidates;
  private final String SEAT_NAME;
 
  /**
   * constructor method of the class Election
   *@param seatName will be used to store the election name
   *@param maxCandidates will be used to store the size of the candidates array
   */
  public Election(String seatName, int maxCandidates) {
    // checks if the maxCandidates is valid
    if(!(maxCandidates > 0)) {
      throw new IllegalArgumentException("Invalid maximum candidates");
    }
    // inits the IV
    SEAT_NAME = seatName;
    candidates = new Candidate[maxCandidates];
    numCandidates = 0;
  }
  
  /*
   * Adds a candidate to the end of the array
   * @param candidate will be used to be the candidate that will be stored at the end
   * @return void
   * */
  public void addCandidate(Candidate candidate) {
    // temp variable to keep store of how many candidates running
    int numCands = getNumCandidates(); 
    
    // checks if I can add candidate
    if( !(numCands<=candidates.length - 1) ) {
      // the length is full, therefore we should return
      return;
    }
    
    // iterates through the numCands array to check if the cnadidate already exists
    for(int i = 0; i<numCands;i++) {
      // checks if its not null just in case, and uses the .equals to see if the candidates match
      if(candidates[i]!= null && candidates[i].equals(candidate)) {
        throw new IllegalArgumentException("Candidate is present already");
      }
    }
    
    candidates[getNumCandidates()] = candidate; // appends to the very end
    numCandidates++; // adds by 1
  }
  
  /*
   * Removes the given param candidate from the array
   * @param candidate, the variable we are searching for to remove
   * */
  public void removeCandidate(Candidate candidate) {
    // temp variable to see how many candidates there is
    int numCands = getNumCandidates();
    
    // there isn't any candidates here, so we just throw exception
    if(numCands==0) {
      throw new IllegalStateException("Remove from empty candidates list");
    }
    
    // temp variable to check if the candidate exists, and if so, where
    int voteIndex = -1;

    // iterates through candidates array
    for(int i = 0; i<numCands;i++) {
      // check if they equal to each other
      if(candidates[i]!=null && candidates[i].equals(candidate)) {
        // found, now break
        voteIndex = i;
        break;
      }
    }
    // since we never found the candidate, we will throw error due to voteIndex never changing
    if(voteIndex==-1) {
      throw new NoSuchElementException("Invalid Candidate");
    }
    // now that voteIndex exists, we must shift everything
    for(int i = voteIndex; i<numCands; i++) {
      // simple shifting algorithm
      candidates[i] = candidates[i+1];
    }
    
    // make the very end to be null  (that is an actual value of type candidate)
    candidates[numCands] = null;
    // decrement
    numCandidates--;
  }
  
  /**
   * given the 50% vote threshold, who wins?
   *
   * @return the winning 'Candidate'
   */
  public Candidate findWinner() {
    //empty array of candidates
    if(getNumCandidates()<=0) {
      throw new IllegalStateException("None found");
    }
    
    // gives us the requirement of # of votes to win
    double req = findTotalVoteCount();
    
    // iterates through the candidates array's numVotes
    for(int i = 0; i < getNumCandidates(); i++) {
      // check if it matches the required votes by at least 1 
      if(candidates[i].getNumVotes() > req) {
        // returns the candidates as it has more
        return candidates[i];
      }
    }
    // none hit the if statement so throw exception
    throw new NoSuchElementException("No Winner Found");
  }
  
  /*
   * Helper method for findWinner method, just calculates req. amnt of votes
   * */
  private double findTotalVoteCount() {
    int z = 0;
    for(int i = 0; i< getNumCandidates();i++) {
      if(candidates[i]!=null) {
        z += candidates[i].getNumVotes();
      }
    }
    // gives us half of the votes
    return z/2;
  }
  
  /*
   * Accessor method for grabbing the candidates that actively partcipates in the election
   * @return the number of elements that are not null, or how many candidate objects are in the array
   * */
  public int getNumCandidates() {
    // temp variable to count
    int z = 0;
    for(int i = 0; i<candidates.length;i++) {
      // since its a compact array, we just return null as any variable past i will be null
      if(candidates[i]==null) {
        break;
      }
      // increment
      z++;
    }
    
    // return and set numCandidates
    numCandidates = z;
    return z;
  }
  
  /*
   * Accessor method for the capacity/max candidates allowed to participate
   * @return an int of the max candidates
   * */
  public int capacity() {
    // grabs the length of the maxCandidates
    return candidates.length;
  }
 
  
  
  /*
   * An overriden method of the Object's method 'equals', checks if this.election is the same as the obj 
   * @param anObject is just used to see if it is an object is an election and ahs the same contents as our election
   * @return if the given object is the same 
   * */
  @Override
  public boolean equals(Object anObject) {
    if(!(anObject instanceof Election)) {
      return false;
    }
    // temp variables
    Election e = (Election) anObject;
    String ts = e.toString();
    
    // grabs the seatname through substring and the tostring
    String temp = ts.substring(0, ts.indexOf("\n"));
    return SEAT_NAME.equalsIgnoreCase(temp);
    
  }
  
  /*
   * An overriden method of type object, but will display the candidates and seatname
   * @return a string of the seatname, and the tostrings of candidates
   * */
  public String toString() {
    int numCands = getNumCandidates();
    String temp = SEAT_NAME+"\n";
    for(int i = 0; i<numCands;i++) {
      // it is a valid candidate
      if(candidates[i]!=null) {
        // appends the candidates with one another
        temp+=candidates[i].toString()+"\n";;
      }
    }
    // removes the extra \n at the very end
    return temp.substring(0, temp.length()-1);
  }
  
  /*
   * votes for a specefic candidate in the candidates array
   * @param candidate will be the candidate we want to vote for
   * */
  public void vote(Candidate candidate) {
    // basecase to check if it is null
    if(candidate==null) {return;}
   // iterates through candidates arary
   for(int i = 0; i<getNumCandidates(); i++) {
     // is the same
     if(candidate.equals(candidates[i])) {
       // add votes
       candidates[i].addVote();
       return;
     }
   }
   // throw exception as candidate isn't present in array
   throw new NoSuchElementException("Candidate does not exist");
  }
  
  
  
}
