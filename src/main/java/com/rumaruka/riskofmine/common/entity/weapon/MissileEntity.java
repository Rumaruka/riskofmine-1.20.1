package com.rumaruka.riskofmine.common.entity.weapon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class MissileEntity extends Projectile {
    protected MissileEntity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData() {

    }
}
