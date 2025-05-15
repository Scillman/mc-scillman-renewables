package com.github.scillman.minecraft.renewables.datagen;

import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

public class ModRecipeGenerator extends RecipeGenerator
{
    private static final String MOD_ID = "renewables";

    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter)
    {
        super(registryLookup, exporter);
    }

    private String getRecipePath(ItemConvertible item)
    {
        return MOD_ID + ":" + getItemPath(item);
    }

    private String getRecipePath(ItemConvertible item, String suffix)
    {
        return MOD_ID + ":" + getItemPath(item) + "_" + suffix;
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

        // offerSmelting( IRON_ORES,   RecipeCategory.MISC, Items.IRON_INGOT,   0.7F, 200, "iron_ingot"   );
        // offerSmelting( COPPER_ORES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, 200, "copper_ingot" );
        // offerSmelting( GOLD_ORES,   RecipeCategory.MISC, Items.GOLD_INGOT,   1.0F, 200, "gold_ingot"   );

        // offerBlasting(IRON_ORES,   RecipeCategory.MISC, Items.IRON_INGOT,   0.7F, 100, "iron_ingot");
        // offerBlasting(COPPER_ORES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, 100, "copper_ingot");
        // offerBlasting(GOLD_ORES,   RecipeCategory.MISC, Items.GOLD_INGOT,   1.0F, 100, "gold_ingot");

        createBlockSmeltingRecipe(Items.RAW_IRON_BLOCK,   RecipeCategory.MISC, Items.IRON_BLOCK,   (9 * 0.7f), (9 * 200), (9 * 100), "iron_block");
        createBlockSmeltingRecipe(Items.RAW_GOLD_BLOCK,   RecipeCategory.MISC, Items.GOLD_BLOCK,   (9 * 1.0f), (9 * 200), (9 * 100), "gold_block");
        createBlockSmeltingRecipe(Items.RAW_COPPER_BLOCK, RecipeCategory.MISC, Items.COPPER_BLOCK, (9 * 0.7f), (9 * 200), (9 * 100), "copper_block");

        createCompactCoralBlockRecipe("tube_coral_block", Items.TUBE_CORAL_FAN, Items.TUBE_CORAL, Items.TUBE_CORAL_BLOCK);
        createCompactCoralBlockRecipe("brain_coral_block", Items.BRAIN_CORAL_FAN, Items.BRAIN_CORAL, Items.BRAIN_CORAL_BLOCK);
        createCompactCoralBlockRecipe("bubble_coral_block", Items.BUBBLE_CORAL_FAN, Items.BUBBLE_CORAL, Items.BUBBLE_CORAL_BLOCK);
        createCompactCoralBlockRecipe("fire_coral_block", Items.FIRE_CORAL_FAN, Items.FIRE_CORAL, Items.FIRE_CORAL_BLOCK);
        createCompactCoralBlockRecipe("horn_coral_block", Items.HORN_CORAL_FAN, Items.HORN_CORAL, Items.HORN_CORAL_BLOCK);
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
            .offerTo(exporter, getRecipePath(ore, suffix));
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
            .offerTo(exporter, getRecipePath(ore, suffix));
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
            .offerTo(exporter, getRecipePath(ore, suffix));
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
            .offerTo(exporter, getRecipePath(Items.NETHER_GOLD_ORE));
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
            .offerTo(exporter, getRecipePath(Items.NETHER_QUARTZ_ORE));
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
            .offerTo(exporter, getRecipePath(Items.GILDED_BLACKSTONE));
    }

    private void createBlockSmeltingRecipe(ItemConvertible input, RecipeCategory category, ItemConvertible output, float experience, int smeltingTime, int blastingTime, String group)
    {
        CookingRecipeJsonBuilder.create(Ingredient.ofItem(input),
            category, output, experience, smeltingTime, RecipeSerializer.SMELTING, SmeltingRecipe::new)
            .group(group)
            .criterion(hasItem(input), this.conditionsFromItem(input))
            .offerTo(this.exporter, getRecipePath(output, ("from_smelting_" + getItemPath(input))));

        CookingRecipeJsonBuilder.create(Ingredient.ofItem(input),
            category, output, experience, blastingTime, RecipeSerializer.BLASTING, BlastingRecipe::new)
            .group(group)
            .criterion(hasItem(input), this.conditionsFromItem(input))
            .offerTo(this.exporter, getRecipePath(output, ("from_blasting_" + getItemPath(input))));
    }

    private void createCompactCoralBlockRecipe(String group, ItemConvertible fanItem, ItemConvertible item, ItemConvertible compactItem)
    {
        createShaped(RecipeCategory.MISC, compactItem)
            .pattern("iii")
            .pattern("iii")
            .pattern("iii")
            .input('i', fanItem)
            .group(group)
            .criterion(hasItem(fanItem), conditionsFromItem(fanItem))
            .offerTo(exporter, getRecipePath(compactItem, "from_fan"));

        createShaped(RecipeCategory.MISC, compactItem)
            .pattern("iii")
            .pattern("iii")
            .pattern("iii")
            .input('i', item)
            .group(group)
            .criterion(hasItem(item), conditionsFromItem(item))
            .offerTo(exporter, getRecipePath(compactItem));
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
