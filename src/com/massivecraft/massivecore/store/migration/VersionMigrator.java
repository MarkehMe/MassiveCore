package com.massivecraft.massivecore.store.migration;

import com.google.gson.JsonObject;

public interface VersionMigrator
{
	// -------------------------------------------- //
	// MIGRATION
	// -------------------------------------------- //

	public void migrate(JsonObject entity);

}
