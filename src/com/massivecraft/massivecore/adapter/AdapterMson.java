package com.massivecraft.massivecore.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.massivecraft.massivecore.mson.Mson;

public class AdapterMson implements JsonDeserializer<Mson>, JsonSerializer<Mson>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //

	private static final AdapterMson i = new AdapterMson();
	public static AdapterMson get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public JsonElement serialize(Mson src, Type typeOfSrc, JsonSerializationContext context)
	{
		return Mson.toJson(src);
	}

	@Override
	public Mson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return Mson.fromJson(json);
	}

}
