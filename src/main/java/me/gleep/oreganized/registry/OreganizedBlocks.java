package me.gleep.oreganized.registry;

import me.gleep.oreganized.Oreganized;
import me.gleep.oreganized.blocks.ModCauldron;
import me.gleep.oreganized.blocks.MoltenLeadBlock;
import me.gleep.oreganized.blocks.SilverBlock;
import me.gleep.oreganized.blocks.SpottedGlance;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class OreganizedBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Oreganized.MOD_ID);

    public static final RegistryObject<Block> MOLTEN_LEAD_BLOCK = registerHidden("molten_lead_block", MoltenLeadBlock::new);
    public static final RegistryObject<Block> LEAD_CAULDRON = registerHidden("cauldron", ModCauldron::new);

    // Glance
    public static final RegistryObject<Block> GLANCE = register("glance", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIORITE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<SlabBlock> GLANCE_SLAB = BLOCKS.register("glance_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<SlabBlock> GLANCE_BRICKS_SLAB = BLOCKS.register("glance_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<StairBlock> GLANCE_STAIRS = BLOCKS.register("glance_stairs", () -> new StairBlock(GLANCE.get()::defaultBlockState,
            BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<StairBlock> GLANCE_BRICKS_STAIRS = BLOCKS.register("glance_bricks_stairs", () -> new StairBlock(GLANCE.get()::defaultBlockState,
            BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<WallBlock> GLANCE_WALL = BLOCKS.register("glance_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<WallBlock> GLANCE_BRICKS_WALL = BLOCKS.register("glance_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<Block> POLISHED_GLANCE = BLOCKS.register("polished_glance", () -> new Block(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<Block> GLANCE_BRICKS = BLOCKS.register("glance_bricks", () -> new Block(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<Block> CHISELED_GLANCE = BLOCKS.register("chiseled_glance", () -> new Block(BlockBehaviour.Properties.copy(GLANCE.get())));
    public static final RegistryObject<Block> SPOTTED_GLANCE = BLOCKS.register("spotted_glance", SpottedGlance::new);
    public static final RegistryObject<Block> WAXED_SPOTTED_GLANCE = BLOCKS.register("waxed_spotted_glance", () -> new Block(BlockBehaviour.Properties.copy(SPOTTED_GLANCE.get())));
    // Ores
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)));

    public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL)
            .strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE), UniformInt.of(0, 3))
    );
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = BLOCKS.register("deepslate_lead_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)));

    // Storage Blocks
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = BLOCKS.register("raw_silver_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = BLOCKS.register("raw_lead_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block", SilverBlock::new);
    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL))
    );
    public static final RegistryObject<Block> ELECTRUM_BLOCK = BLOCKS.register("electrum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));


    // Crystal Glass

    // Waxed Concrete Powder

    // Other Blocks



    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends B> block, CreativeModeTab tab) {
        RegistryObject<B> blocks = BLOCKS.register(name, block);
        OreganizedItems.ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties().tab(tab)));
        return blocks;
    }

    public static <B extends Block> RegistryObject<B> registerHidden(String name, Supplier<? extends B> block) {
        return BLOCKS.register(name, block);
    }

    public static <B extends Block> RegistryObject<B> registerCompat(String modid, String name, Supplier<? extends B> block, CreativeModeTab tab) {
        return register(name, block, ModList.get().isLoaded(modid) ? tab : null);
    }
}
