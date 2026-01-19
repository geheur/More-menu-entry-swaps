package com.hotkeyablemenuswaps;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GroundItemPriceSortMode
{
	DISABLED("Disabled")
		{
			@Override
			public Integer getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return groundItem.getHaPrice(); // Just in case,
			}
		},
	GE_PRICE("Grand Exchange")
		{
			@Override
			public Integer getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return groundItem.getGePrice();
			}
		},
	MAX_GE_OR_ALCH_PRICE("max(GE, High Alch)")
		{
			@Override
			public Integer getItemPrice(GroundItemsStuff.GroundItem groundItem)
			{
				return Math.max(groundItem.getGePrice(), groundItem.getHaPrice());
			}
		};

	private final String displayName; // For combo box.

	public abstract Integer getItemPrice(GroundItemsStuff.GroundItem groundItem);

	@Override
	public String toString()
	{
		return displayName;
	}

}
