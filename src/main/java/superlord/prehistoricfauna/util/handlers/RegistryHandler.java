package superlord.prehistoricfauna.util.handlers;

import superlord.prehistoricfauna.Main;
import superlord.prehistoricfauna.entity.EntityAllosaurus;
import superlord.prehistoricfauna.entity.EntityAnkylosaurus;
import superlord.prehistoricfauna.entity.EntityBaryonyx;
import superlord.prehistoricfauna.entity.EntityCamarasaurus;
import superlord.prehistoricfauna.entity.EntityCladocyclus;
import superlord.prehistoricfauna.entity.EntityDinosaurEgg;
import superlord.prehistoricfauna.entity.EntityDryosaurus;
import superlord.prehistoricfauna.entity.EntityEusthenopteron;
import superlord.prehistoricfauna.entity.EntityGallimimus;
import superlord.prehistoricfauna.entity.EntityParadoxides;
import superlord.prehistoricfauna.entity.EntityParasaurolophus;
import superlord.prehistoricfauna.entity.EntityPrenocephale;
import superlord.prehistoricfauna.entity.EntityStegosaurus;
import superlord.prehistoricfauna.entity.EntityTriceratops;
import superlord.prehistoricfauna.entity.EntityTyrannosaurus;
import superlord.prehistoricfauna.entity.EntityVelociraptor;
import superlord.prehistoricfauna.init.ModItems;
import superlord.prehistoricfauna.util.IHasModel;
import superlord.prehistoricfauna.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@EventBusSubscriber
public class RegistryHandler {
    private static int entityId = 0;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "triceratops"), EntityTriceratops.class, "triceratops", entityId++, Main.instance, 64, 3, true, 12871971, 10507067);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "velociraptor"), EntityVelociraptor.class, "velociraptor", entityId++, Main.instance, 64, 3, true, 0x404040, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "gallimimus"), EntityGallimimus.class, "gallimimus", entityId++, Main.instance, 64, 3, true, 0x808080, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "tyrannosaurus"), EntityTyrannosaurus.class, "tyrannosaurus", entityId++, Main.instance, 64, 3, true, 0x3A1E0C, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "camarasaurus"), EntityCamarasaurus.class, "camarasaurus", entityId++, Main.instance, 64, 3, true, 0x3C3825, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "stegosaurus"), EntityStegosaurus.class, "stegosaurus", entityId++, Main.instance, 64, 3, true, 0x5C4823, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "dryosaurus"), EntityDryosaurus.class, "dryosaurus", entityId++, Main.instance, 64, 3, true, 0x412E23, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "allosaurus"), EntityAllosaurus.class, "allosaurus", entityId++, Main.instance, 64, 3, true, 0x302726, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "ankylosaurus"), EntityAnkylosaurus.class, "ankylosaurus", entityId++, Main.instance, 64, 3, true, 0x5E2801, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "prenocephale"), EntityPrenocephale.class, "prenocephale", entityId++, Main.instance, 64, 3, true, 0xAA9A8D, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "parasaurolophus"), EntityParasaurolophus.class, "parasaurolophus", entityId++, Main.instance, 64, 3, true, 0x1B2A26, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "baryonyx"), EntityBaryonyx.class, "baryonyx", entityId++, Main.instance, 64, 3, true, 0xB0AF93, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "dinoegg"), EntityDinosaurEgg.class, "dinoegg", entityId++, Main.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "paradoxides"), EntityParadoxides.class, "paradoxides", entityId++, Main.instance, 64, 3, true, 0x7E3200, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "eusthenopteron"), EntityEusthenopteron.class, "eusthenopteron", entityId++, Main.instance, 64, 3, true, 0x515352, 0x000000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "cladocyclus"), EntityCladocyclus.class, "cladocyclus", entityId++, Main.instance, 64, 3, true, 0x002844, 0x000000);

    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }
    
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
    }
}
