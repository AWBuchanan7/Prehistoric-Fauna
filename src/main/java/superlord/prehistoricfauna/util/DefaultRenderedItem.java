package superlord.prehistoricfauna.util;

import net.minecraft.util.ResourceLocation;

public interface DefaultRenderedItem {
	default String getResource(ResourceLocation registryName) {
		return registryName.getPath();
	}
}
