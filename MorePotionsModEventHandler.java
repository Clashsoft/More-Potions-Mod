package clashsoft.mods.morepotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class MorePotionsModEventHandler
{
	@ForgeSubscribe(priority = EventPriority.LOW)
	public void onEntityDamaged(LivingAttackEvent event)
	{
		if (event.entityLiving.isPotionActive(MorePotionsMod.doubleLife))
		{
			if (event.entityLiving.func_110143_aJ() - event.ammount <= 0)
			{
				event.setCanceled(true);
				event.entityLiving.setEntityHealth(event.entityLiving.func_110138_aP());
				event.entityLiving.removePotionEffect(MorePotionsMod.doubleLife.id);
				if (event.entityLiving instanceof EntityPlayer)
				{
					((EntityPlayer) event.entityLiving).addChatMessage("<\u00a7kCLASHSOFT\u00a7r>: \u00a7bYour life has just been saved by a magical power. Be careful next time, it wont help you again.");
				}
			}
		}
		if (event.entityLiving.isPotionActive(MorePotionsMod.ironSkin))
		{
			event.entityLiving.addPotionEffect(new PotionEffect(Potion.resistance.id, event.entityLiving.getActivePotionEffect(MorePotionsMod.ironSkin).getDuration(), 2));
			if (event.source == DamageSource.inFire || event.source == DamageSource.onFire)
			{
				event.entityLiving.extinguish();
				event.setCanceled(true);
			}
		}
		if (event.entityLiving.isPotionActive(MorePotionsMod.obsidianSkin))
		{
			event.entityLiving.addPotionEffect(new PotionEffect(Potion.resistance.id, event.entityLiving.getActivePotionEffect(MorePotionsMod.obsidianSkin).getDuration(), 2));
			if (event.source == DamageSource.lava || event.source == DamageSource.inFire || event.source == DamageSource.onFire)
			{
				event.entityLiving.extinguish();
				event.setCanceled(true);
			}
		}
	}
}