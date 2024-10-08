import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Ballot {
    private static boolean ballotsCreated = false;  
    private static ArrayList<Election> elections = new ArrayList<>();
    private boolean[] hasVoted;

    
    public Ballot() {
        if (elections.isEmpty()) {
            throw new IllegalStateException("No elections yet nerd.");
        }
        
        this.hasVoted = new boolean[elections.size()];
        ballotsCreated = true;  
    }

  
    public static void addElection(Election election) {
        if (ballotsCreated) {
            throw new IllegalStateException("cant add ballots created nerd.");
        }
        if (elections.contains(election)) {
            throw new IllegalArgumentException("This election is already present nerd.");
        }
        elections.add(election);
    }

    
    public boolean hasVoted(String seatName) {
        for (int i = 0; i < elections.size(); i++) {
            String seat = elections.get(i).toString().split("\n")[0];
            if (seat.equals(seatName)) {
                return hasVoted[i];  
            }
        }
        throw new NoSuchElementException("No such election for the seat name: " + seatName + " nerd");
    }

    
    public void vote(String seatName, Candidate candidate) {
        for (int i = 0; i < elections.size(); i++) {
            String seat = elections.get(i).toString().split("\n")[0];
            if (seat.equals(seatName)) {
                if (hasVoted[i]) {
                    throw new IllegalStateException("This ballot has already voted in this election nerd.");
                }
                candidate.addVote();  
                hasVoted[i] = true; 
                return;
            }
        }
        throw new NoSuchElementException("No such election for the seat name: " + seatName + " nerd");
    }

    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < elections.size(); i++) {
            result.append(elections.get(i).toString().split("\n")[0])  
                  .append(": ")
                  .append(hasVoted[i])  
                  .append("\n");
        }
        return result.toString().trim();  
    }
}