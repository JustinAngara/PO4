
public class Election {
  private Candidate[] candidates;
  private int numCandidates;
  private final String SEAT_NAME;
 
  public Election(String seatName, int maxCandidates) {
    if(!(maxCandidates > 0)) {
      throw new IllegalArgumentException("Invalid maximum candidates");
    }
    SEAT_NAME = seatName;
    candidates = new Candidate[maxCandidates];
    numCandidates = 0;
  }
  public int getNumCandidates() {
    return numCandidates;
  }
  public int capacity() {
    return candidates.length;
  }
  
  
  
  
  @Override
  public boolean equals(Object anObject) {
    if(!(anObject instanceof Election)) {
      return false;
    }
    Election e = (Election) anObject;
    return this.toString().equalsIgnoreCase(e.toString());
  }
  
  public String toString() {
    String temp = SEAT_NAME+"\n";
    for(int i = 0; i<numCandidates;i++) {
      // it is a valid candidate
      if(candidates[i]!=null) {
        temp+=candidates[i].toString()+"\n";;
      }
    }
    return temp;
  }
  
}
