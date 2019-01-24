package superlord.prehistoricfauna.init;

import superlord.prehistoricfauna.blocks.BlockBase;
import superlord.prehistoricfauna.blocks.BlockCretaceousFossil;
import superlord.prehistoricfauna.blocks.BlockDNAExtractor2;
import superlord.prehistoricfauna.blocks.BlockEggIncubator;
import superlord.prehistoricfauna.blocks.BlockJurassicFossil;
import superlord.prehistoricfauna.blocks.BlockModWall;
import superlord.prehistoricfauna.blocks.TileEntityDNAExtractor;
import superlord.prehistoricfauna.blocks.tile.TileEntityEggIncubator;
import superlord.prehistoricfauna.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static superlord.prehistoricfauna.util.Reference.MOD_ID;

@Mod.EventBusSubscriber
public class ModBlocks {
    public static final Block CRETACEOUS_FOSSIL = new BlockCretaceousFossil("cretaceous_fossil", Material.ROCK);
    public static final Block EGG_INCUBATOR = new BlockEggIncubator("egg_incubator");
    public static final Block JURASSIC_FOSSIL = new BlockJurassicFossil("jurassic_fossil", Material.ROCK);
    public static final Block DNA_EXTRACTOR2 = new BlockDNAExtractor2("dna_extractor2");
    public static final Block GRAVEL_PATH = new BlockBase("gravel_path", Material.GROUND);
    public static final Block RED_GRAVEL_PATH = new BlockBase("red_gravel_path", Material.SAND);
    public static final Block HERRINGBONE_PATH = new BlockBase("herringbone", Material.GROUND);
    public static final Block RED_HERRINGBONE_PATH = new BlockBase("red_herringbone", Material.GROUND);
    public static final Block COBBLESTONE_BRICKS = new BlockBase("cobble_bricks", Material.GROUND);
    public static final Block RED_COBBLESTONE_BRICKS = new BlockBase("red_cobble_bricks", Material.GROUND);
    public static final Block BRICK_WALL = new BlockModWall("brick_wall", Material.GROUND);
    public static final Block STONE_BRICK_WALL = new BlockModWall("stone_brick_wall", Material.GROUND);
    

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                CRETACEOUS_FOSSIL,
                EGG_INCUBATOR,
                JURASSIC_FOSSIL,
                DNA_EXTRACTOR2,
                GRAVEL_PATH,
                RED_GRAVEL_PATH,
                HERRINGBONE_PATH,
                RED_HERRINGBONE_PATH,
                COBBLESTONE_BRICKS,
                RED_COBBLESTONE_BRICKS,
                BRICK_WALL,
                STONE_BRICK_WALL
        );
        GameRegistry.registerTileEntity(TileEntityEggIncubator.class, new ResourceLocation(MOD_ID, "tile_egg_incubator"));
        GameRegistry.registerTileEntity(TileEntityDNAExtractor.class, new ResourceLocation(MOD_ID, "tile_dna_extractor"));
    }

    @SubscribeEvent
    public static void onItemBlockRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new ItemBlock(CRETACEOUS_FOSSIL).setRegistryName(CRETACEOUS_FOSSIL.getRegistryName()),
                new ItemBlock(EGG_INCUBATOR).setRegistryName(EGG_INCUBATOR.getRegistryName()),
                new ItemBlock(JURASSIC_FOSSIL).setRegistryName(JURASSIC_FOSSIL.getRegistryName()),
                new ItemBlock(DNA_EXTRACTOR2).setRegistryName(DNA_EXTRACTOR2.getRegistryName()),
                new ItemBlock(GRAVEL_PATH).setRegistryName(GRAVEL_PATH.getRegistryName()),
                new ItemBlock(RED_GRAVEL_PATH).setRegistryName(RED_GRAVEL_PATH.getRegistryName()),
                new ItemBlock(HERRINGBONE_PATH).setRegistryName(HERRINGBONE_PATH.getRegistryName()),
                new ItemBlock(RED_HERRINGBONE_PATH).setRegistryName(RED_HERRINGBONE_PATH.getRegistryName()),
                new ItemBlock(COBBLESTONE_BRICKS).setRegistryName(COBBLESTONE_BRICKS.getRegistryName()),
                new ItemBlock(RED_COBBLESTONE_BRICKS).setRegistryName(RED_COBBLESTONE_BRICKS.getRegistryName()),
                new ItemBlock(BRICK_WALL).setRegistryName(BRICK_WALL.getRegistryName()),
                new ItemBlock(STONE_BRICK_WALL).setRegistryName(STONE_BRICK_WALL.getRegistryName())
        );
    }
    

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        registerModel(CRETACEOUS_FOSSIL);
        registerModel(EGG_INCUBATOR);
        registerModel(JURASSIC_FOSSIL);
        registerModel(DNA_EXTRACTOR2);
        registerModel(GRAVEL_PATH);
        registerModel(RED_GRAVEL_PATH);
        registerModel(HERRINGBONE_PATH);
        registerModel(RED_HERRINGBONE_PATH);
        registerModel(COBBLESTONE_BRICKS);
        registerModel(RED_COBBLESTONE_BRICKS);
        registerModel(BRICK_WALL);
        registerModel(STONE_BRICK_WALL);
    }

    private static void registerModel(Block block) {
        if (block instanceof IHasModel) {
            ((IHasModel) block).registerModels();
        }
    }
}