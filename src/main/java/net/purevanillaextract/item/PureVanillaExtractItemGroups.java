package net.purevanillaextract.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.block.PureVanillaExtractBlocks;

import static net.purevanillaextract.item.PureVanillaExtractItems.*;


public class PureVanillaExtractItemGroups {


    public static final ItemGroup PURE_VANILLA_EXTRACT  =
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.purevanillaextract"))
                    .icon(() -> new ItemStack(PureVanillaExtractItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(BLUEFISH_BUCKET);
                        entries.add(MUD_BUCKET);
                        entries.add(PIG_BUCKET);
                        entries.add(PureVanillaExtractBlocks.WOLF_COCOON);
                        entries.add(SKELETON_WOLF_SPAWN_EGG);
                        entries.add(BLUEFISH_SPAWN_EGG);
                        entries.add(WOOLY_COW_SPAWN_EGG);
                        entries.add(CLUCKSHROOM_SPAWN_EGG);
                        entries.add(FANCY_CHICKEN_SPAWN_EGG);
                        entries.add(BLUEFISH);
                        entries.add(COOKED_BLUEFISH);
                        entries.add(HAM);
                        entries.add(COOKED_HAM);
                        entries.add(WOLF_COLLAR);
                        entries.add(YARN);
                        entries.add(FANCY_FEATHER);
                        entries.add(ICE_BOMB);
                        entries.add(WITHERED_BONE);
                        entries.add(RUBY);
                        entries.add(PureVanillaExtractBlocks.SALT);
                        entries.add(PureVanillaExtractBlocks.RUBY_BLOCK);
                        entries.add(PureVanillaExtractBlocks.RUBY_ORE);
                    }).build();


    public static void registerPveItemGroups() {
        PureVanillaExtract.LOGGER.info("Registering Mod Item Groups for " + PureVanillaExtract.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, new Identifier(PureVanillaExtract.MOD_ID, "purevanillaextract"), PURE_VANILLA_EXTRACT);

    }
}
