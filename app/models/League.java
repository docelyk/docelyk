package models;

import play.Logger;

import java.util.*;


public class League {

    private int size;
    private ArrayList<String> tokens;
    private Map<String,Integer> standings;
    private ArrayList<String> draftOrder;
    private ArrayList<String> draftOrderInverse;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Integer> getStandings() {
        return standings;
    }

    public void setStandings(Map<String,Integer> standings) {
        this.standings = standings;
    }

    public ArrayList<String> getDraftOrder() {
        return draftOrder;
    }

    public void setDraftOrder(ArrayList<String> draftOrder) {
        this.draftOrder = draftOrder;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public League(int num) {
        setSize(num);
        draftOrder = new ArrayList<>();
        draftOrderInverse = new ArrayList<>(size);
        tokens = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            draftOrderInverse.add("");
        }
    }

    public void determineOrder() {
        if(!standings.isEmpty()) {
            Map<String,Integer> standingMap = new HashMap<>();
            standingMap.putAll(standings);

            if(!draftOrder.isEmpty()) draftOrder.clear();

            for(int i=0; i<size; i++) {
                if(!tokens.isEmpty()) tokens.clear();
                addTokens(standingMap);
                String player = randomSelect();
                draftOrder.add(player);
                standingMap.remove(player);
//                Logger.debug("Size map: " + standingMap.size());
            }

            for(int i=0; i<draftOrder.size(); i++) {
                Logger.debug("--" + (i+1) + "--\t" + draftOrder.get(i) );
            }
            Logger.debug("\n");

        }
    }

    public void addTokens(Map<String,Integer> standingMap) {
        for (Map.Entry<String, Integer> entry : standingMap.entrySet()) {

            for (int i = 0; i < entry.getValue(); i++) {
                tokens.add(entry.getKey());
            }

        }
        Collections.shuffle(tokens);
//        Logger.debug("Number Tokens : " + tokens.size());
    }

    public String randomSelect() {
        Random rand = new Random();
        int num = rand.nextInt(tokens.size());
        String player = tokens.get(num);

//        Logger.debug("Random " + Integer.toString(num));
//        Logger.debug("Player " + player);

        return player;
    }


    public List<String> getInverseOrder() {
        if(!standings.isEmpty()){
            double mid = size/2+0.5;
            for (Map.Entry<String, Integer> entry : standings.entrySet()) {
                if(entry.getValue() > mid) {
                    double index = entry.getValue() - mid;
                    index = mid - index;
                    draftOrderInverse.set((int)index-1, entry.getKey());
                }
                else {
                    double index = mid - entry.getValue();
                    index = index + mid;
                    draftOrderInverse.set((int)index-1, entry.getKey());
                }
            }
            return draftOrderInverse;
        } else {
            return null;
        }
    }

}
