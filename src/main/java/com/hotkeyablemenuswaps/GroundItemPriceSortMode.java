package com.hotkeyablemenuswaps;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GroundItemPriceSortMode
{
	DISABLED("Disabled")
		{
			@Override
			public long getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return groundItem.getHaPrice(); // Just in case,
			}
		},
	GE_PRICE("Grand Exchange")
		{
			@Override
			public long getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return groundItem.getGePrice();
			}
		},
	MAX_GE_OR_ALCH_PRICE("max(GE, High Alch)")
		{
			@Override
			public long getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return Math.max(groundItem.getGePrice(), groundItem.getHaPrice());
			}
		};

	private final String displayName; // For combo box.

	public abstract long getItemPrice(GroundItemsStuff.GroundItem groundItem);

	@Override
	public String toString()
	{
		return displayName;
	}

}
