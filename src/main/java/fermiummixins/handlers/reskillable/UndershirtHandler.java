package fermiummixins.handlers.reskillable;

import codersafterdark.reskillable.api.ReskillableRegistries;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.lib.LibMisc;
import fermiummixins.wrapper.FirstAidWrapper;
import ichttt.mods.firstaid.FirstAid;
import ichttt.mods.firstaid.api.damagesystem.AbstractDamageablePart;
import ichttt.mods.firstaid.api.event.FirstAidLivingDamageEvent;
import ichttt.mods.firstaid.common.network.MessageUpdatePart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class UndershirtHandler {
    
    private static final ResourceLocation defenseLocation = new ResourceLocation(LibMisc.MOD_ID, "defense");
    private static final ResourceLocation undershirtLocation = new ResourceLocation(LibMisc.MOD_ID, "undershirt");

    /**
     * Reimplement Reskillable's UnderShirt trait to work with FirstAid
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onFirstAidLivingDamageHigh(FirstAidLivingDamageEvent event) {
        if(event.getEntityPlayer() == null || event.getEntityPlayer().world.isRemote) return;
        if(event.getUndistributedDamage() > 1000) return; //Don't protect against attacks meant to instakill
        if(FirstAidWrapper.getNonAttackDamageSources().contains(event.getSource())) return;//Don't protect against certain non-attack damage sources

        List<AbstractDamageablePart> parts = new ArrayList<>();
        for(AbstractDamageablePart part : event.getAfterDamage()) {//Check if they are going to die first before bothering with trait check
            if(part.canCauseDeath && part.currentHealth <= 0) {
                parts.add(part);
            }
        }

        if(!parts.isEmpty() && PlayerDataHandler.get(event.getEntityPlayer()).getSkillInfo(
                ReskillableRegistries.SKILLS.getValue(defenseLocation)).isUnlocked(
                ReskillableRegistries.UNLOCKABLES.getValue(undershirtLocation))) {
            if(event.getEntityPlayer().getEntityData().getInteger("skillable:UndershirtCD") <= 0) {
                boolean saved = false;
                for(AbstractDamageablePart part : parts) {//Iterate parts
                    if(event.getBeforeDamage().getFromEnum(part.part).currentHealth >= 4.0) {//Only proc undershirt if the part had atleast 2 hearts
                        part.heal(1.0F, null, false);
                        if(event.getEntityPlayer() instanceof EntityPlayerMP) FirstAid.NETWORKING.sendTo(new MessageUpdatePart(part), (EntityPlayerMP) event.getEntityPlayer());
                        saved = true;
                    }
                }
                if(saved) {
                    event.getEntityPlayer().getEntityData().setInteger("skillable:UndershirtCD", 1200);
                    event.getEntityPlayer().world.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.8F, (event.getEntityPlayer().world.rand.nextFloat()-event.getEntityPlayer().world.rand.nextFloat())*0.1F+0.8F);
                }
            }
        }
    }
}