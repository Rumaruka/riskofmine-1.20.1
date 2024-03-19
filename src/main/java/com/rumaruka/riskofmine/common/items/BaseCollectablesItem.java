package com.rumaruka.riskofmine.common.items;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.checkerframework.checker.optional.qual.OptionalBottom;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Optional;

public abstract class BaseCollectablesItem extends Item implements ICurioItem {
    private final Types type;
    private final Category categoryEnum;

    public static int size;


    public BaseCollectablesItem(Types type, Category category) {
        super(new Properties());
        this.type = type;
        this.categoryEnum = category;

        setSizeStack(size);


    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return getSizeStack();
    }


    public int getSizeStack() {

        return size;
    }

    public void setSizeStack(int sizeS) {
        size = sizeS;
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
