import java.util.NoSuchElementException;

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
  
  public void addCandidate(Candidate candidate) {
    // checks if I can add candidate
    if(!(getNumCandidates()<=candidates.length-1)) {
      return;
    }
    for(int i = 0; i<candidates.length;i++) {
      if(candidates[i].equals(candidate)) {
        throw new IllegalArgumentException("Candidate is present already");
      }
    }
    
    candidates[getNumCandidates()] = candidate; // appends to the very end
  }
  
  public void removeCandidate(Candidate candidate) {
    int numCands = getNumCandidates();
    if(numCands==0) {
      throw new IllegalStateException("Remove from empty candidates list");
    }
    int voteIndex = -1;
    for(int i = 0; i<candidates.length;i++) {
      if(candidates[i].equals(candidate)) {
        voteIndex = i;
        break;
      }
    }
    if(voteIndex==-1) {
      throw new NoSuchElementException("Invalid Candidate");
    }
    // now that voteIndex exists, we must shift everything
    for(int i = voteIndex; i<numCands; i++) {
      candidates[i] = candidates[i+1];
    }
    candidates[numCands] = null;
    
  }
  
  public Candidate findWinner() {
    //candidates.length >=1
    Candidate temp = candidates[0]; // could be null so returning null is fine
    
    return temp;
  }
  
  public int getNumCandidates() {
    int z = 0;
    for(int i = 0; i<candidates.length;i++) {
      if(candidates[i]==null) {
        break;
      }
      z++;
    }
    return z;
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
