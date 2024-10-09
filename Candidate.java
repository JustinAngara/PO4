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

/*
 * A class Candidate will be the individual candidates for the election class, and will be used to fill attributes for each candidate
 * */
public class Candidate {
  // store instance variables
  private String name;
  private String party;
  private int numVotes;
  /*
   * Constructor method for the class Candidate
   * @param n -> name
   * @param p -> party
   * */
  public Candidate(String n, String p) {
    // ensures that strings like -> "   " doesn't exist as it is blank
    if(n==null || n.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid name");
    }
    if(p==null || p.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid party");
    }
    // inits variables
    name = n;
    party = p;
    numVotes = 0;
  }
  
  
  /*
   * equals will be used to check if a candidate is the same compared to another
   * @return t/f depending if it is the same or not based on contents of type Candidate
   * */
  @Override
  public boolean equals(Object o) {
    // ensures that object o is a candidate
    if(!(o instanceof Candidate)) {
      return false;
    }
    // temp conversion for object o. upcasts the object to the appropriate class type 'Candidate'
    Candidate t = (Candidate) o;
    return this.toString().equals(t.toString()); // checks for same name, party, and votes
    
  }
  
  /*
   * Will add a vote to this candidate obj
   * */
  public void addVote() {
    numVotes++;
  }
  
  /*
   * Accessor method to see name
   * @return string of name
   * */
  public String getName() {
    return name;
  }
  
  /*
   * Accessor method to see party
   * @return string of party
   * */
  public String getParty() {
    return party;
  }
  
  /*
   * Accessor method to see numVotes
   * @return int of votes
   * */
  public int getNumVotes() {
    return numVotes;
  }
  
  /*
   * overrident toString method from the class Object
   * @return string of the format "name (party): numVotes"
   * */
  public String toString() {
    return name+" ("+party+"): "+numVotes;
  }
}
