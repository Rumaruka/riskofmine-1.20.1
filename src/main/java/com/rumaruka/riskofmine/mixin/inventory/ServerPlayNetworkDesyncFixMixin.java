package com.rumaruka.riskofmine.mixin.inventory;

import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.network.TickablePacketListener;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin({ServerGamePacketListenerImpl.class})
public abstract class ServerPlayNetworkDesyncFixMixin implements ServerPlayerConnection, TickablePacketListener, ServerGamePacketListener {
    @Shadow
    public ServerPlayer player;

    @Shadow
    private int tickCount;

    @Inject(method = {"handleSetCreativeModeSlot"}, at = {@At("TAIL")})
    public void onCreativeInventoryAction(ServerboundSetCreativeModeSlotPacket packet, CallbackInfo ci) {
        ItemStack itemStack = packet.getItem();
        boolean slotIsPositive = (packet.getSlotNum() < 0);
        boolean isValid = (itemStack.isEmpty() || (itemStack.getDamageValue() >= 0 && itemStack.getCount() <= itemStack.getMaxStackSize() && !itemStack.isEmpty()));
        boolean bl2 = (packet.getSlotNum() >= 1 && packet.getSlotNum() <= 45);
        boolean isOnlyROM = itemStack.getItem() instanceof BaseCollectablesItem;
        if (isValid && bl2 && isOnlyROM) {
            this.player.inventoryMenu.getSlot(packet.getSlotNum()).setByPlayer(itemStack);
            this.player.inventoryMenu.broadcastChanges();
        } else if (slotIsPositive && isValid && isOnlyROM && this.tickCount < 200) {
            this.tickCount += 20;
            this.player.drop(itemStack, true);
        }
    }

}
