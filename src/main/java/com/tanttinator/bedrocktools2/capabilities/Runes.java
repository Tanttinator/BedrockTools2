package com.tanttinator.bedrocktools2.capabilities;

import java.util.List;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;

public class Runes implements IRunes {

    private List<BedrockTools2.Element> elements = Lists.newArrayList();

    @Override
    public void add(Element element) {
        if(hasRune(element))
            return;

        elements.add(element);
    }

    @Override
    public Boolean hasRune(Element element) {
        return elements.contains(element);
    }

    @Override
    public int[] getRuneIds() {
        int[] runes = new int[elements.size()];
        for(int i = 0; i < elements.size(); i++) {
            runes[i] = elements.get(i).ordinal();
        }
        return runes;
    }

    @Override
    public void setRunesById(int[] runes) {
        elements = Lists.newArrayList();
        for (int i : runes) {
            elements.add(BedrockTools2.Element.values()[i]);
        }
    }
}