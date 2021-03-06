package superlord.prehistoricfauna.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    void preInit(FMLPreInitializationEvent event);
    void registerItemRenderer(Item item, int meta, String id);
	void init(FMLInitializationEvent event);
}
