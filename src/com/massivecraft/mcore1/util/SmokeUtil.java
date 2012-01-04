package com.massivecraft.mcore1.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import net.minecraft.server.ChunkPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Packet60Explosion;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;

// http://mc.kev009.com/Protocol
// -----------------------------
// Smoke Directions 
// -----------------------------
// Direction ID    Direction
//            0    South - East
//            1    South
//            2    South - West
//            3    East
//            4    (Up or middle ?)
//            5    West
//            6    North - East
//            7    North
//            8    North - West
//-----------------------------

public class SmokeUtil
{
	public static Random random = new Random();
	
	// -------------------------------------------- //
	// Spawn once
	// -------------------------------------------- //
	
	// Single ========
	public static void spawnSingle(Location location, int direction)
	{
		if (location == null) return;
		location.getWorld().playEffect(location, Effect.SMOKE, direction);
	}
	
	public static void spawnSingle(Location location)
	{
		spawnSingle(location, 4);
	}
	
	public static void spawnSingleRandom(Location location)
	{
		spawnSingle(location, random.nextInt(9));
	}
	
	// Simple Cloud ========
	public static void spawnCloudSimple(Location location)
	{
		for (int i = 0; i <= 8; i++)
		{
			spawnSingle(location, i);
		}
	}
	
	public static void spawnCloudSimple(Collection<Location> locations)
	{
		for (Location location : locations)
		{
			spawnCloudSimple(location);
		}
	}
	
	// Random Cloud ========
	public static void spawnCloudRandom(Location location, float thickness)
	{
		int singles = (int) Math.floor(thickness*9);
		for (int i = 0; i < singles; i++)
		{
			spawnSingleRandom(location);
		}
	}
	
	public static void spawnCloudRandom(Collection<Location> locations, float thickness)
	{
		for (Location location : locations)
		{
			spawnCloudRandom(location, thickness);
		}
	}
	
	// Fake Explosion ========
	public static void fakeExplosion(Location location, int radius)
	{
		if (location == null) return;
		
		HashSet<ChunkPosition> blocks = new HashSet<ChunkPosition>();
		int squRadius = radius * radius;
		for(int x = -radius; x <= radius; x++)
		{
			for(int y = -radius; y <= radius; y++)
			{
				for(int z = -radius; z <= radius; z++)
				{
					if(x*x+y*y+z*z+x+y+z+0.75 < squRadius) // ???
					{
						blocks.add(new ChunkPosition(
						(int)(location.getX()+x+0.5),
						(int)(location.getY()+y+0.5),
						(int)(location.getZ()+z+0.5)));
					}
				}
			}
		}
		
		Packet60Explosion packet = new Packet60Explosion(location.getX(),location.getY(), location.getZ(), 0.1f, blocks);
		CraftServer craftServer = (CraftServer) Bukkit.getServer();
		MinecraftServer minecraftServer = craftServer.getServer();
		
		minecraftServer.serverConfigurationManager.sendPacketNearby(location.getX(), location.getY(), location.getZ(), 64, ((CraftWorld)location.getWorld()).getHandle().dimension, packet);
	}
	
	
	// -------------------------------------------- //
	// Attach continuous effects to or locations
	// -------------------------------------------- //
	
	// TODO
	
}
