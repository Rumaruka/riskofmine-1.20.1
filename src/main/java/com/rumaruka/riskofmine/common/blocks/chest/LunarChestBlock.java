package com.rumaruka.riskofmine.common.blocks.chest;

import com.rumaruka.riskofmine.api.Chest;
import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.common.tiles.chest.GenericChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LunarChestTE;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class LunarChestBlock extends GenericChestBlock {

    public LunarChestBlock() {
        super(Properties.of().strength(5, 5), ROMTiles.SMALL_CHEST, Chest.LUNAR);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Lunar lunar = Lunar.of(player);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GenericChestTE) {
                if (lunar != null && lunar.getCurrentLunar() >= lunar.getCurrentLunar()) {
                    player.openMenu((GenericChestTE) blockEntity);
                    lunar.consumeLunar(player, 1);
                    lunar.detectAndSendChanges();
                } else if (lunar != null) {
                    level.playSound(null, blockPos, ROMSounds.ROM_CHEST_NOT_MONEY.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
                    player.displayClientMessage(Component.translatable("riskofmine.not_lunar"), true);

                }


            }
        }
        return InteractionResult.CONSUME;
    }


    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, BlockState state_, BlockEntityType<T> blockEntityType_) {
        return !level_.isClientSide ? null : createTickerHelper(blockEntityType_, ROMTiles.LUNAR_CHEST, (level, pos, state, blockEntity_) -> blockEntity_.clientTick(level_, pos, state));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LunarChestTE(pPos, pState);
    }
}
