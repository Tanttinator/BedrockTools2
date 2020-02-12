package com.tanttinator.bedrocktools2.items;

import com.tanttinator.bedrocktools2.BedrockTools2;

import net.minecraft.item.Item;

public class RuneItem extends Item {

    private BedrockTools2.Element element;

    public RuneItem(BedrockTools2.Element element) {
        super();
        this.element = element;
    }

    public BedrockTools2.Element getElement() {
        return element;
    }
}