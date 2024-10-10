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
import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests
 * to check the correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      //   1) the constructor didn't intialize a data field correctly OR
      //   2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0")) return false;
      
      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0) return false;

    } catch (Exception e) { 
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) 
   * where an exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    // this will be temp variable to check
    boolean hasHitError = false;
    
    Candidate c;
    // test for null name
    try {
      c = new Candidate(null, "party");
    } catch (IllegalArgumentException e) {
      hasHitError = true; // exception hit so we good
    }
    if(!hasHitError) {
      return false;
    }
    hasHitError = false;
    
    // test for empty name
    try {
      c = new Candidate("", "party");
    } catch (IllegalArgumentException e) {
      hasHitError = true; // exception hit so we good
    }
    if(!hasHitError) {
      return false;
    }
    hasHitError = false;
    
    // test for null party
    try {
      c = new Candidate("wasd", null); 
    } catch (IllegalArgumentException e) {
      hasHitError = true; // exception hit so we good
    }
    if(!hasHitError) {
      return false;
    }
    hasHitError = false;
    
    // test for empty party
    // test for empty name
    try {
      c = new Candidate("awfh", "");
    } catch (IllegalArgumentException e) {
      hasHitError = true;
    }
    if(!hasHitError) {
      return false;
    }
    
    return true;
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.)
   * This test accounts for the fact a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {
    Election election = new Election("these tester methods are killing me", 20);
    election.addCandidate(new Candidate("mid","method"));
    election.addCandidate(new Candidate("don't make us do this again","method"));
    if(!(election.capacity()==20)) {
      return false;
    }
    if(!(election.getNumCandidates()==2)) {
      return false;
    }
    if(!(election.SEAT_NAME.equals("these tester methods are killing me"))) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    int size = 0;
    boolean temp = false;
    try {
      Election em = new Election("better work",-10);
    } catch (IllegalArgumentException e) {
      // negative value therefore will hit the illegal catch statement
      temp = true;
    }
    return temp; // TODO
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    Election election = new Election("these tester methods are killing me", 20);
    election.addCandidate(new Candidate("mid","method"));
    election.addCandidate(new Candidate("don't make us do this again","method"));
    if( !(election.toString().equalsIgnoreCase(
        "these tester methods are killing me"
        + "\nmid (method): 0"
        + "\ndon't make us do this again (method): 0"))) {
      return false;
    }
    
    return true; // TODO
  }
  
  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    Election election = new Election("these tester methods are killing me", 20);
    election.addCandidate(new Candidate("mid","method"));
    election.addCandidate(new Candidate("don't make us do this again","method"));
    try {
      election.addCandidate(new Candidate("mid","method"));
    }catch(IllegalArgumentException e) {
      // this is good cuz its already in the dang candidates array
      return true;
      
    }
    return false; // TODO
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    Election election = new Election("these tester methods are killing me", 20);
    Candidate c = new Candidate("mid","method");
    election.addCandidate(c);
    election.vote(c);
    if(!(election.toString().equalsIgnoreCase(
        "these tester methods are killing me"
        + "\nmid (method): 1"))) {
      return false;
    }
    return true; // TODO
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.
    
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1); election.addCandidate(c2);
    } catch (Exception e) {
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch(NoSuchElementException e) {
      // this is correct
    } catch(Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }
  
  /**
   * Tests the Election's removeCandidate() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    Election election = new Election("Sportsball", 10);
    Candidate c1 = new Candidate("lebron james", "basketball");
    Candidate c2 = new Candidate("messi", "soccer");
    Candidate c3 = new Candidate("smh", "soccer");
    election.addCandidate(c1); election.addCandidate(c2);
    election.addCandidate(c3);
    
    // calling remove method
    election.removeCandidate(c2);
    
    if(! (election.toString().equals(
        "Sportsball"
        + "\nlebron james (basketball): 0"
        + "\nsmh (soccer): 0")) ) {
      return false;
    }
    
    
    return true; 
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    boolean temp = false;
    Election election = new Election("Sportsball", 10);
    Candidate c1 = new Candidate("lebron james", "basketball");
    Candidate c2 = new Candidate("messi", "soccer");
    Candidate c3 = new Candidate("smh", "soccer");
    election.addCandidate(c1); election.addCandidate(c2);
    try {
      election.removeCandidate(c3);
    } catch(NoSuchElementException e){
      temp = true;
    }
    
    boolean temp1 = false;
    election = new Election("Sports", 10);
    try {
      election.removeCandidate(c1);
    } catch (IllegalStateException e) {
      temp1 = true;
    }
    
    return temp && temp1; // TODO
  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    Ballot.clearElections();
    try {
        // Create two elections and candidates
        Election election = new Election("Sportsball", 10);
        Candidate c1 = new Candidate("lebron james", "basketball");
        Candidate c2 = new Candidate("messi", "soccer");
        election.addCandidate(c1);
        election.addCandidate(c2);
    
        Election election1 = new Election("Sportsball1", 10);
        Candidate c3 = new Candidate("lebron james", "basketball");
        Candidate c4 = new Candidate("messi", "soccer");
        election1.addCandidate(c3);
        election1.addCandidate(c4);
    
        // Add elections to the ballot
        Ballot.addElection(election);
        Ballot.addElection(election1);
    
        // Create a ballot
        Ballot ballot = new Ballot();
    
        // Check if no votes have been made
        if (ballot.hasVoted("Sportsball") || ballot.hasVoted("Sportsball1")) {
            return false;
        }
    
        // Cast a vote for "Sportsball"
        ballot.vote("Sportsball", c1);
        if (!ballot.hasVoted("Sportsball")) {
            return false;
        }
    
        // Test voting twice in the same election, which should throw an exception
        try {
            ballot.vote("Sportsball", c2);
            
            return false;
        } catch (IllegalStateException e) {
            // Expected behavior
//          System.out.println("caught exception");
        }
    
        // Cast a vote for "Sportsball1"
        ballot.vote("Sportsball1", c4);
        if (!ballot.hasVoted("Sportsball1")) {
            return false;
        }
    
        return true; // All tests passed
    } catch (Exception e) {
//        e.printStackTrace();
        return false;
    }


  }
  

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    Ballot.clearElections();
    try {
      Ballot b = new Ballot(); // hit the catch statement
      return false;
    }catch(IllegalStateException e) {

    }
    
    Election e1 = new Election("Best Pokemon", 3);
    Ballot.addElection(e1);

    try {
        Ballot.addElection(e1); /// you can't add duplicate election
        return false; // Should not reach here
    } catch (IllegalArgumentException e) {
        // good
    }
    
    Ballot b = new Ballot();//officially creates the ballot, therefore, shouldn't allow any more ballots to be added
    try {
      
      Ballot.addElection(new Election("Worst Football Team", 4));
      return false; // Should not reach here
    } catch (IllegalStateException e) {
        // Expected exception
    }
    return true;
  }



  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    Ballot.clearElections();
    try {
        // Step 1: Create two elections and add them before creating any ballots
        Election election1 = new Election("President", 5);
        Election election2 = new Election("Senator", 5);
        Ballot.addElection(election1);
        Ballot.addElection(election2);
        
        // Step 2: Create a new ballot
        Ballot ballot = new Ballot();
  
        // Step 3: Create candidates
        Candidate candidate1 = new Candidate("John Doe", "president");
        Candidate candidate2 = new Candidate("Jane Smith", "senator");
        
        election1.addCandidate(candidate1);
        election2.addCandidate(candidate2);
        // Step 4: Ensure both elections have not been voted on
        if (ballot.hasVoted("President") || ballot.hasVoted("Senator")) {
            return false;
        }
     
        // Step 5: Vote in both elections
        ballot.vote("President", candidate1);
        ballot.vote("Senator", candidate2);
  
        // Step 6: Ensure both elections have been voted on
        if (!ballot.hasVoted("President") || !ballot.hasVoted("Senator")) {

            return false;
        }
  
        return true;
  
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
  }


  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of
   * exception(s) in situations where an exception is expected. See the Ballot documentation
   * for details.
   * 
   * @return true if all tests pass, false otherwise 
   */
  public static boolean testBallotVoteExceptions() {
    Ballot.clearElections();
    Election e = new Election("test", 10);
    
    Ballot.addElection(e);

    try {
      Ballot b = new Ballot();
      // random name
      b.hasVoted("1");
      return false;
    } catch(NoSuchElementException e1) {
     
    }
    
    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {


    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));
    
    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));
    
    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call 
   * them directly in the main method rather than adding them to the previous method.
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());


  }


}