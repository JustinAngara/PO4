
public class Candidate {
  private String name;
  private String party;
  private int numVotes;
  public Candidate(String n, String p) {
    // ensures that strings like -> "   " doesn't exist as it is blank
    if(n==null || n.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid name");
    }
    if(p==null || p.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid party");
    }
    name = n;
    party = p;
    numVotes = 0;
  }
  
  
  
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
  
  public void addVote() {
    numVotes++;
  }
  
  public String getName() {
    return name;
  }
  
  public String getParty() {
    return party;
  }
  
  public int getNumVotes() {
    return numVotes;
  }
  
  public String toString() {
    return name+" ("+party+"): "+numVotes;
  }
}
