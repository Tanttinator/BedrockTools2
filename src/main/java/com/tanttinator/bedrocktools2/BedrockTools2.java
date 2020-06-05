package com.tanttinator.bedrocktools2;

import com.tanttinator.bedrocktools2.blocks.BT2Blocks;
import com.tanttinator.bedrocktools2.capabilities.IRunes;
import com.tanttinator.bedrocktools2.capabilities.Runes;
import com.tanttinator.bedrocktools2.capabilities.RunesStorage;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.recipes.RuneUpgradeRecipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.util.text.TextFormatting;

@Mod(BedrockTools2.MOD_ID)
public class BedrockTools2 {

    public static final String MOD_ID = "bedrocktools2";

    public static final Logger log = LogManager.getLogger(MOD_ID);

    public static final ItemGroup group = new ItemGroup("bedrocktools2") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BT2Items.diamond_stick);
        }
    };

    public enum Element {
        AIR("Air", TextFormatting.YELLOW),
        EARTH("Earth", TextFormatting.GREEN),
        FIRE("Fire", TextFormatting.RED),
        WATER("Water", TextFormatting.BLUE);

        public String name;
        public TextFormatting color;

        private Element(String name, TextFormatting color) {
            this.name = name;
            this.color = color;
        }
    }

    public BedrockTools2() {
        //DistExecutor.safeRunForDist(() -> () -> SideProxy.Client::new, () -> () -> SideProxy.Server::new);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        IRecipeSerializer.register(RuneUpgradeRecipe.NAME.toString(), RuneUpgradeRecipe.SERIALIZER);
    }

    private void setup(final FMLCommonSetupEvent event) {
        BiomeManager.getBiomes(BiomeManager.BiomeType.COOL).forEach(this::RegisterBedrockiumOre);
        BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT).forEach(this::RegisterBedrockiumOre);
        BiomeManager.getBiomes(BiomeManager.BiomeType.ICY).forEach(this::RegisterBedrockiumOre);
        BiomeManager.getBiomes(BiomeManager.BiomeType.WARM).forEach(this::RegisterBedrockiumOre);

        CapabilityManager.INSTANCE.register(IRunes.class, new RunesStorage(), () -> new Runes());
    }

    void RegisterBedrockiumOre(final BiomeManager.BiomeEntry biomeEntry) {
        biomeEntry.biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                    new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE, BT2Blocks.bedrockium_ore.getDefaultState(), 6)
                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 10))
                )
        );
    }
}
