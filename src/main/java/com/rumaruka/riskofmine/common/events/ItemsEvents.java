package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.init.ROMBlocks;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.init.ROMParticles;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.ItemActivationPacket;
import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMMathFormula;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static net.minecraft.world.entity.EquipmentSlot.CHEST;


@Mod.EventBusSubscriber
public class ItemsEvents {
    public static final UUID healthModifierID = UUID.fromString("208b4d4c-50ef-4b45-a097-4bed633cdbff");
    private static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEntityHurt(LivingHurtEvent event) {
        /*
         * onPlayerHurt  - for hurt entites without Player event
         */
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level level = livingEntity.level();
            if (!level.isClientSide) {
                if (ROMUtils.checkInventory(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance())) {
                    if ((event.getEntity() instanceof WitherBoss || event.getEntity() instanceof EnderDragon || !event.getEntity().canChangeDimensions())) {
                        event.getEntity().hurt(level.damageSources().magic(), ROMUtils.counting(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance()) * 2 - 1);
                    }
                }

                if (ROMUtils.checkCurios(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance())) {
                    if (event.getEntity() instanceof AmbientCreature) {
                        if ((event.getEntity() instanceof WitherBoss || event.getEntity() instanceof EnderDragon || !event.getEntity().canChangeDimensions())) {
                            event.getEntity().hurt(level.damageSources().magic(), ROMUtils.countingCurio(player, ROMItems.ARMOR_PIERCING_ROUNDS) * 2 - 1);
                        }
                    }
                }

                if (ROMUtils.checkInventory(player, ROMItems.CROWBAR.getDefaultInstance())) {
                    if (event.getEntity() instanceof AmbientCreature) {
                        if (event.getEntity().getHealth() > (event.getEntity().getMaxHealth() * 90 / 100)) {
                            event.getEntity().hurt(level.damageSources().magic(), (float) (ROMUtils.counting(player, ROMItems.CROWBAR.getDefaultInstance()) * 1.00115d));
                        }
                    }
                }

                if (ROMUtils.checkCurios(player, ROMItems.CROWBAR.getDefaultInstance())) {
                    if (event.getEntity().getHealth() > (event.getEntity().getMaxHealth() * 90 / 100)) {
                        event.getEntity().hurt(level.damageSources().magic(), (float) (ROMUtils.countingCurio(player, ROMItems.CROWBAR) * 1.00115d));
                    }
                }
                if (ROMUtils.checkInventory(player, ROMItems.GASOLINE.getDefaultInstance())) {
                    if (event.getEntity() instanceof AmbientCreature) {
                        event.getEntity().setRemainingFireTicks(ROMUtils.counting(player, ROMItems.GASOLINE.getDefaultInstance()) * 20);
                    }
                }
                if (ROMUtils.checkCurios(player, ROMItems.GASOLINE.getDefaultInstance())) {
                    if (event.getEntity() instanceof AmbientCreature) {
                        event.getEntity().setRemainingFireTicks(ROMUtils.countingCurio(player, ROMItems.GASOLINE) * 20);
                    }
                }
                if (ROMUtils.checkInventory(player, ROMItems.STICKY_BOMB.getDefaultInstance())) {
                    StickyBombEntity entityStickyBomb = new StickyBombEntity(level, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, player, event.getEntity());
                    level.addFreshEntity(entityStickyBomb);
                }
                if (ROMUtils.checkCurios(player, ROMItems.STICKY_BOMB.getDefaultInstance())) {
                    StickyBombEntity entityStickyBomb = new StickyBombEntity(level, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, player, event.getEntity());
                    level.addFreshEntity(entityStickyBomb);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerLevelUp(PlayerXpEvent.LevelChange event) {
        Player entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (ROMUtils.checkInventory(entity, ROMItems.WARBANNER.getDefaultInstance())) {
                entity.level().setBlockAndUpdate(entity.blockPosition(), ROMBlocks.WARBANNER_BLOCK.defaultBlockState());
            }
            if (ROMUtils.checkCurios(entity, ROMItems.WARBANNER.getDefaultInstance())) {
                entity.level().setBlockAndUpdate(entity.blockPosition(), ROMBlocks.WARBANNER_BLOCK.defaultBlockState());
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerHurt(LivingHurtEvent event) {
        /*
         * onPlayerHurt  - for hurt player event
         */
        if (event.getSource().getEntity() instanceof Mob && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level();
            if (!world.isClientSide) {
                if (ROMUtils.checkInventory(player, ROMItems.OLD_WAR_STEALTHKIT.getDefaultInstance())) {
                    if (player.getHealth() >= 2.5f) {
                        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1, false, false));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 1, false, false));
                    }
                }
                if (ROMUtils.checkInventory(player, ROMItems.OLD_WAR_STEALTHKIT.getDefaultInstance())) {
                    if (player.getHealth() >= 2.5f) {
                        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1, false, false));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 1, false, false));
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        /*
         Player kill Entity
         */
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level world = livingEntity.level();
            if (!world.isClientSide) {
                ItemStack monster = ROMItems.MONSTER_TOOTH.getDefaultInstance();
                if (ROMUtils.checkCurios(player, monster)) {
                    world.addFreshEntity(new HealthOrbEntity(world, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, ROMUtils.counting(player, monster)));
                    world.playSound(null, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), ROMSounds.PROC_MT_SPAWN.get(), SoundSource.MASTER, 2, 2);
                }
                if (ROMUtils.checkInventory(player, monster)) {
                    world.addFreshEntity(new HealthOrbEntity(world, livingEntity.getX() + 0.5d, livingEntity.getY() + 0.5d, livingEntity.getZ() + 0.5d, ROMUtils.counting(player, monster)));
                    world.playSound(null, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), ROMSounds.PROC_MT_SPAWN.get(), SoundSource.MASTER, 2, 2);
                }
            }
        }
        /*
         Entity kill Player
         */
        if (event.getSource().getEntity() instanceof AmbientCreature && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level();
            if (!world.isClientSide) {

                if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                } else {
                    ItemStack dio_best_friend = ROMItems.DIO_BEST_FRIEND.getDefaultInstance();
                    if (ROMUtils.checkInventory(player, dio_best_friend)) {
                        if (player.isDeadOrDying() || player.getHealth() < 2.5f) {
                            player.setHealth(player.getMaxHealth());
                            player.removeAllEffects();
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1800, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 1));
                            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 600, 1));
                            ROMNetwork.getInstance().sendTo(new ItemActivationPacket(dio_best_friend), player);
                            dio_best_friend.shrink(1);
                        }
                    }
                    if (ROMUtils.checkCurios(player, dio_best_friend)) {
                        if (player.isDeadOrDying() || player.getHealth() < 2.5f) {
                            player.setHealth(player.getMaxHealth());
                            player.removeAllEffects();
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1800, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 1));
                            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 600, 1));
                            ROMNetwork.getInstance().sendTo(new ItemActivationPacket(dio_best_friend), player);
                            dio_best_friend.shrink(1);
                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingTickEvent event) {
        Player player = ROMUtils.getPlayer();
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level();
        if (!level.isClientSide) {
            if (player != null) {
                if (ROMUtils.checkInventory(player, ROMItems.FOCUS_CRYSTAL.getDefaultInstance())) {
                    if (livingEntity instanceof Mob) {
                        float distance = player.distanceTo(livingEntity);
                        if (distance <= 3.5) {
                            livingEntity.hurt(level.damageSources().magic(), ROMMathFormula.powerIncreasing(ROMUtils.counting(player, new ItemStack(ROMItems.FOCUS_CRYSTAL)), 5.0f, 5));
                            ROMUtils.getMc().particleEngine.createTrackingEmitter(livingEntity, ROMParticles.FOCUS_CRYSTAL.get());
                        }
                    }
                }
                if (ROMUtils.checkCurios(player, ROMItems.FOCUS_CRYSTAL.getDefaultInstance())) {
                    if (livingEntity instanceof Mob) {
                        float distance = player.distanceTo(livingEntity);
                        if (distance <= 3.5) {
                            livingEntity.hurt(level.damageSources().magic(), ROMMathFormula.powerIncreasing(ROMUtils.counting(player, new ItemStack(ROMItems.FOCUS_CRYSTAL)), 5.0f, 5));
                            ROMUtils.getMc().particleEngine.createTrackingEmitter(livingEntity, ROMParticles.FOCUS_CRYSTAL.get());
                        }
                    }
                }
                if (ROMUtils.checkInventory(player, ROMItems.POWER_ELIXIR.getDefaultInstance())) {
                    if (player.getHealth() < 2) {
                        player.heal(player.getHealth());
                        ROMUtils.replaceItem(ROMItems.POWER_ELIXIR.getDefaultInstance(), ROMItems.EMPTY_ELIXIR.getDefaultInstance());
                    }
                }

                if (ROMUtils.checkCurios(player, ROMItems.POWER_ELIXIR.getDefaultInstance())) {
                    if (player.getHealth() < 3) {
                        player.heal(player.getHealth());
                        ROMUtils.replaceItem(ROMItems.POWER_ELIXIR.getDefaultInstance(), ROMItems.EMPTY_ELIXIR.getDefaultInstance());
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            if (entity instanceof Player player) {
                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {
                    ROMDoubleEffect.play(player);
                }
                if (ROMUtils.checkCurios(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {
                    ROMDoubleEffect.play(player);
                }
            }
        }
    }


}
