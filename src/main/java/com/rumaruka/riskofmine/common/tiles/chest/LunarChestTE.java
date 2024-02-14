package com.rumaruka.riskofmine.common.tiles.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.init.ROMAnimation;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.AnimationConstants;
import ru.timeconqueror.timecore.api.animation.AnimationStarter;

public class LunarChestTE extends GenericChestTE implements AnimatedObject<LunarChestTE> {
    private AnimationSystem<LunarChestTE> animationSystem;

    public LunarChestTE(BlockPos blockPos, BlockState blockState) {
        super(ROMTiles.LUNAR_CHEST, blockPos, blockState, Chest.LUNAR, ROMBlocks.LUNAR_CHEST);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestInventory.createLunarContainer(pContainerId, pInventory, this);
    }

    @Override
    public @NotNull AnimationSystem<LunarChestTE> getSystem() {
        return animationSystem;
    }

    public void clientTick(Level level_, BlockPos pos_, BlockState state_) {
        // getAnimationSystemApi().startAnimation(AnimationStarter.of(ROMAnimation.lunarOpen).ignorable(true), AnimationConstants.MAIN_LAYER_NAME);
    }
}
