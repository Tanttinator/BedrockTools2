package com.tanttinator.bedrocktools2.capabilities;

import com.tanttinator.bedrocktools2.BedrockTools2;

public interface IRunes {

    public void add(BedrockTools2.Element element);
    public Boolean hasRune(BedrockTools2.Element element);

    public int[] getRuneIds();
    public void setRunesById(int[] runes);
}