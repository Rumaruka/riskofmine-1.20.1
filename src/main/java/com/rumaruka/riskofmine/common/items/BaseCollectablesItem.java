package com.rumaruka.riskofmine.common.items;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BaseCollectablesItem extends Item implements ICurioItem {
    private final Types type;
    private final Category categoryEnum;


    public BaseCollectablesItem(Types type, Category category) {
        super(new Properties());
        this.type = type;
        this.categoryEnum = category;

    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return getSizeStack();
    }


    public static int getSizeStack() {
        return 127;
    }

    public Types getType() {
        return type;
    }

    public String getTypeName() {
        return type.getName();
    }

    public ChatFormatting getColor() {
        return type.getChatColor();
    }

    public ChatFormatting getColors() {
        return categoryEnum.getChatColor();
    }

    public String getCategoryName() {
        return categoryEnum.getName();
    }


}
