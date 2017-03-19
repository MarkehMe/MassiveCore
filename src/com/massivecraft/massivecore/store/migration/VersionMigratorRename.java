package com.massivecraft.massivecore.store.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class VersionMigratorRename implements VersionMigrator
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	private final String from;
	public String getFrom() { return from; }

	private final String to;
	public String getTo() { return to; }

	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public static VersionMigratorRename get(String from, String to) { return new VersionMigratorRename(from, to); }
	public VersionMigratorRename(String from, String to)
	{
		if (from == null) throw new NullPointerException("from");
		if (to == null) throw new NullPointerException("to");
		
		this.from = from;
		this.to = to;
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void migrate(JsonObject entity)
	{
		String from = this.getFrom();
		String to = this.getTo();

		JsonElement element = entity.remove(from);
		if (element != null) entity.add(to, element);
	}

}
