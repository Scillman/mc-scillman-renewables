package com.github.scillman.minecraft.renewables.datagen;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

public class ModRecipeGenerator extends RecipeGenerator
{
    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter)
    {
        super(registryLookup, exporter);
    }

    @Override
    public void generate()
    {
        //RegistryWrapper.Impl<Item> itemLookup =
        registries.getOrThrow(RegistryKeys.ITEM);

        createOreRecipe4("coal_ore", Items.COAL, Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE);
        createOreRecipe4("diamond_ore", Items.DIAMOND, Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE);
        createOreRecipe4("emerald_ore", Items.EMERALD, Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE);

        createOreRecipe4("iron_ore", Items.IRON_INGOT, Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE);
        createOreRecipe4("gold_ore", Items.GOLD_INGOT, Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE);

        // createOreRecipe4("raw_iron_ore", Items.RAW_IRON, Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE);
        // createOreRecipe4("raw_gold_ore", Items.RAW_GOLD, Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE);
        
        createOreRecipe22("copper_ore", Items.COPPER_INGOT, Items.COPPER_BLOCK, Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE);

        createOreRecipe4("lapis_ore", Items.LAPIS_BLOCK, Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE);
        createOreRecipe8("redstone_ore", Items.REDSTONE, Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE);

        createNetherGoldOreRecipe();
        createNetherQuartzRecipe();
        createGildedBlackstoneRecipe();
    }

    private void createOreRecipe4_base(String group, String suffix, ItemConvertible baseItem, ItemConvertible item, ItemConvertible ore)
    {
        createShaped(RecipeCategory.MISC, ore)
            .pattern("i i")
            .pattern(" b ")
            .pattern("i i")
            .input('i', item)
            .input('b', baseItem)
            .group(group)
            .criterion(hasItem(item), conditionsFromItem(item))
            .offerTo(exporter, getItemPath(ore) + "_" + suffix);
    }

    private void createOreRecipe4(String group, ItemConvertible item, ItemConvertible ore, ItemConvertible deepslateOre)
    {
        createOreRecipe4_base(group, "from_stone", Items.STONE, item, ore);
        createOreRecipe4_base(group, "from_deepslate", Items.DEEPSLATE, item, deepslateOre);
        createOreRecipe4_base(group, "from_cobbled_deepslate", Items.COBBLED_DEEPSLATE, item, deepslateOre);
    }

    private void createOreRecipe8_base(String group, String suffix, ItemConvertible baseItem, ItemConvertible item, ItemConvertible ore)
    {
        createShaped(RecipeCategory.MISC, ore)
            .pattern("iii")
            .pattern("ibi")
            .pattern("iii")
            .input('i', item)
            .input('b', baseItem)
            .group(group)
            .criterion(hasItem(item), conditionsFromItem(item))
            .offerTo(exporter, getItemPath(ore) + "_" + suffix);    
    }

    private void createOreRecipe8(String group, ItemConvertible item, ItemConvertible ore, ItemConvertible deepslateOre)
    {
        createOreRecipe8_base(group, "from_stone", Items.STONE, item, ore);
        createOreRecipe8_base(group, "from_deepslate", Items.DEEPSLATE, item, deepslateOre);
        createOreRecipe8_base(group, "from_cobbled_deepslate", Items.COBBLED_DEEPSLATE, item, deepslateOre);
    }

    private void createOreRecipe22_base(String group, String suffix, ItemConvertible baseItem, ItemConvertible item, ItemConvertible compactItem, ItemConvertible ore)
    {
        createShaped(RecipeCategory.MISC, ore)
            .pattern("i i")
            .pattern(" b ")
            .pattern("c c")
            .input('i', item)
            .input('c', compactItem)
            .input('b', baseItem)
            .group(group)
            .criterion(hasItem(item), conditionsFromItem(item))
            .offerTo(exporter, getItemPath(ore) + "_" + suffix);
    }

    private void createOreRecipe22(String group, ItemConvertible item, ItemConvertible compactItem, ItemConvertible ore, ItemConvertible deepslateOre)
    {
        createOreRecipe22_base(group, "from_stone", Items.STONE, item, compactItem, ore);
        createOreRecipe22_base(group, "from_deepslate", Items.DEEPSLATE, item, compactItem, deepslateOre);
        createOreRecipe22_base(group, "from_cobbled_deepslate", Items.COBBLED_DEEPSLATE, item, compactItem, deepslateOre);
    }

    private void createNetherGoldOreRecipe()
    {
        createShaped(RecipeCategory.MISC, Items.NETHER_GOLD_ORE)
            .pattern("iii")
            .pattern("cbc")
            .pattern("iii")
            .input('i', Items.GOLD_NUGGET)
            .input('c', Items.GOLD_INGOT)
            .input('b', Items.NETHERRACK)
            .group("nether_gold_ore")
            .criterion(hasItem(Items.NETHER_GOLD_ORE), conditionsFromItem(Items.NETHER_GOLD_ORE))
            .offerTo(exporter);   
    }

    private void createNetherQuartzRecipe()
    {
        createShaped(RecipeCategory.MISC, Items.NETHER_QUARTZ_ORE)
            .pattern("i i")
            .pattern(" b ")
            .pattern("i i")
            .input('i', Items.QUARTZ)
            .input('b', Items.NETHERRACK)
            .group("nether_quartz_ore")
            .criterion(hasItem(Items.QUARTZ), conditionsFromItem(Items.QUARTZ))
            .offerTo(exporter);
    }

    private void createGildedBlackstoneRecipe()
    {
        createShaped(RecipeCategory.MISC, Items.GILDED_BLACKSTONE)
            .pattern("i i")
            .pattern(" b ")
            .pattern("iii")
            .input('i', Items.GOLD_NUGGET)
            .input('b', Items.BLACKSTONE)
            .group("gilded_blackstone")
            .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
            .offerTo(exporter);
    }
}

/**
 * INFODUMP - HTTP://MINECRAFT.WIKI
 *
 *   All the information listed below assumes a tool with Fortune 3.
 *
 *    4     0+4     Coal
 *    4     0+4     Diamond
 *    4     0+4     Emerald
 * 
 *    4     0+4     Iron
 *   20     2+2     Copper
 *    4     0+4     Gold
 * 
 *   24     2+6     Nether Gold (Nuggets)
 *    4     0+4     Nether Quartz
 *    5     0+5     Gilded Blackstone (Nuggets)
 * 
 *   36     4+0     Lapis Lazuli
 *    8     0+8     Redstone
 *
 */
