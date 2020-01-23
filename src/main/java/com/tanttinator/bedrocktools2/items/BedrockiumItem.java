package com.tanttinator.bedrocktools2.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public class BedrockiumItem extends Item {

    List<Element> elements;

    public BedrockiumItem(Properties properties) {
        super(properties);
        elements = new ArrayList<Element>();
    }

    public void AddElement(Element element) {
        if(!HasElement(element)) {
            elements.add(element);
        }
    }

    public Boolean HasElement(Element element) {
        return elements.contains(element);
    }

    public enum Element {
        AIR,
        EARTH,
        WATER,
        FIRE
    }
    
}