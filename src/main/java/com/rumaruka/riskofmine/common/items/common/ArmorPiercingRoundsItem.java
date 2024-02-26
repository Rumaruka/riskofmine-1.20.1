package com.rumaruka.riskofmine.common.items.common;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorPiercingRoundsItem extends BaseCollectablesItem {
    public ArmorPiercingRoundsItem() {
        super(Types.COMMON, Category.DAMAGE);
        size = getSizeStack();
    }

    @Override
    public int getSizeStack() {
        return 200;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable("ror.alt.info"));
        if (Screen.hasAltDown()) {

            tooltip.add(Component.translatable("riskofmine.rarity" + ":"));
            tooltip.add(Component.translatable((getColor() + getTypeName())));
            tooltip.add(Component.translatable("riskofmine.category" + ":"));
            tooltip.add(Component.translatable((getColors() + getCategoryName())));
        }
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.apr.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.counting(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }
}
