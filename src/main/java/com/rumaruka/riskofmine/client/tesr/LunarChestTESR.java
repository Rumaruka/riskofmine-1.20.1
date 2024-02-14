package com.rumaruka.riskofmine.client.tesr;

import com.rumaruka.riskofmine.common.tiles.chest.LunarChestTE;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.AnimatedTileEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

public class LunarChestTESR extends AnimatedTileEntityRenderer<LunarChestTE> {
    public LunarChestTESR(TimeModel model) {
        super(model);
    }

    @Override
    protected ResourceLocation getTexture(LunarChestTE tileEntityIn) {
        return null;
    }
}
