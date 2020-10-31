package com.gildedgames.the_aether.networking.packets;

import com.gildedgames.the_aether.player.PlayerAether;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.gildedgames.the_aether.api.AetherAPI;

public class PacketSendPoison extends AetherPacket<PacketSendPoison>
{

	private int entityID;

	public PacketSendPoison()
	{
		
	}

	public PacketSendPoison(EntityPlayer thePlayer)
	{
		this.entityID = thePlayer.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(this.entityID);
	}

	@Override
	public void handleClient(PacketSendPoison message, EntityPlayer player) 
	{
		if (player != null && player.world != null)
		{
			EntityPlayer parent = (EntityPlayer) player.world.getEntityByID(message.entityID);

			if (parent != null)
			{
				((PlayerAether) AetherAPI.getInstance().get(parent)).setPoisoned();
			}
		}
	}

	@Override
	public void handleServer(PacketSendPoison message, EntityPlayer player)
	{

	}

}