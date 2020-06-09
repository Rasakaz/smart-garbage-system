package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Routes")
public class Route {

    private List<GarbageBin> LineBins; // a list of bins in the route line
    private int LineNumber; // the line number of the route.

    public Route() {}

    public Route(List<GarbageBin> lineBins, int lineNumber) {
        LineBins = lineBins;
        LineNumber = lineNumber;
    }

    // Getters
    public List<GarbageBin> getLineBins() { return LineBins; }
    public int getLineNumber() { return LineNumber; }

    // Setters
    public void setLineBins(List<GarbageBin> lineBins) { LineBins = lineBins; }
    public void setLineNumber(int lineNumber) { LineNumber = lineNumber; }

    @Override
    public String toString() {
        String S = "Route number: " + LineNumber + " ";
        for(GarbageBin gBin: LineBins){
            S += gBin.toString();
        }
        return S;
    }
}
