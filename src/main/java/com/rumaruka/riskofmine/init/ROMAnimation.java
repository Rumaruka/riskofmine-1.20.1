package com.rumaruka.riskofmine.init;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ROMAnimation {
    public static Animation lunarOpen;

    @SubscribeEvent
    public static void registerAnimation(FMLCommonSetupEvent event) {
        //  lunarOpen = AnimationAPI.loadAndRegisterAnimation(rl("animations/lunar_open.json"));
    }
}
