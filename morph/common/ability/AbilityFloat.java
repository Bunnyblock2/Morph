package morph.common.ability;

import morph.api.Ability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;


public class AbilityFloat extends Ability {

	public double terminalVelocity;
	public boolean negateFallDistance;
	
	public AbilityFloat()
	{
		terminalVelocity = -1000D;
		negateFallDistance = false;
	}
	
	public AbilityFloat(double termVelo, boolean negateFall)
	{
		terminalVelocity = termVelo;
		negateFallDistance = negateFall;
	}
	
	@Override
	public String getType() 
	{
		return "float";
	}

	@Override
	public void tick() 
	{
		boolean flying = false;
		if(getParent() instanceof EntityPlayer)
		{
			flying = ((EntityPlayer)getParent()).capabilities.isFlying;
		}
		if(!flying && getParent().motionY < terminalVelocity)
		{
			getParent().motionY = terminalVelocity;
			if(negateFallDistance)
			{
				getParent().fallDistance = 0.0F;
			}
		}
	}

	@Override
	public void kill() 
	{
	}

	@Override
	public Ability clone() 
	{
		return new AbilityFloat(terminalVelocity, negateFallDistance);
	}

	@Override
	public void postRender() {}

	@Override
	public void save(NBTTagCompound tag) 
	{
		tag.setDouble("terminalVelocity", terminalVelocity);
		tag.setBoolean("negateFallDistance", negateFallDistance);
	}

	@Override
	public void load(NBTTagCompound tag) 
	{
		terminalVelocity = tag.getDouble("terminalVelocity");
		negateFallDistance = tag.getBoolean("negateFallDistance");
	}

}
