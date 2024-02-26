package com.rumaruka.riskofmine.mixin.inventory;

import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Container.class)
public interface InventoryStackMixin extends Clearable {

    /**
     * @author
     * @reason
     */
    @Overwrite
    default int getMaxStackSize() {
        return BaseCollectablesItem.size;
    }

}
