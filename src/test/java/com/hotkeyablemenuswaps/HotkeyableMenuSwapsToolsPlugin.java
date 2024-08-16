package com.hotkeyablemenuswaps;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.Menu;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "[Tools] Hotkeyable menu swaps",
	description = "",
	tags = {"entry", "swapper"}
)
@PluginDependency(HotkeyableMenuSwapsPlugin.class)
public class HotkeyableMenuSwapsToolsPlugin extends Plugin
{

	@Inject
	private HotkeyableMenuSwapsPlugin plugin;

	@Inject
	private ItemManager itemManager;

	int demoanim = -1;
	int demogfx = -1;

	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted) {
		String[] arguments = commandExecuted.getArguments();
		String command = commandExecuted.getCommand();
		System.out.println(arguments.length);

		if (command.equals("record")) {
			recordMenuEntries = true;
		}
	}

	private boolean recordMenuEntries = false;

	@Inject
	private Client client;

	@Subscribe public void onMenuOpened(MenuOpened e) {
		System.out.println("===menu opened===");
		for (int i = client.getMenuEntries().length - 1; i >= 0; i--)
		{
			MenuEntry menuEntry = client.getMenuEntries()[i];
			Widget widget = menuEntry.getWidget();
			int interfaceId = widget != null ? WidgetUtil.componentToInterface(widget.getId()) : -1;
			System.out.println(menuEntry.getOption() + " " + menuEntry.getTarget() + " " + menuEntry.getType() + " " + menuEntry.getIdentifier() + " " + interfaceId);
		}
	}

	@Subscribe(priority = -2) // This will run after our swaps.
	public void onClientTick(ClientTick clientTick) {
		if (false) {
			Menu menu = client.getMenu();
			MenuEntry menuEntry = menu.createMenuEntry(0).setOption("blatitle").onClick(e -> System.out.println("clicked blatitle"));
			Menu subMenu = menuEntry.createSubMenu();
			subMenu.createMenuEntry(0).setOption("bla2").onClick(e -> System.out.println("clicked bla2"));
			subMenu.createMenuEntry(0).setOption("bla3").onClick(e -> System.out.println("clicked bla3"));
			subMenu.createMenuEntry(0).setOption("bla4").onClick(e -> System.out.println("clicked bla4"));

		}
		for (MenuEntry menuEntry : client.getMenuEntries())
		{
			if (menuEntry.getWidget() != null && WidgetUtil.componentToInterface(menuEntry.getWidget().getId()) == InterfaceID.INVENTORY) {
				client.setMenuEntries(new MenuEntry[0]);
				client.createMenuEntry(-1).setOption("Cancel").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Cancel"));
				client.createMenuEntry(-1).setOption("Examine").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Examine"));
				client.createMenuEntry(-1).setOption("Drop").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Drop"));
				client.createMenuEntry(-1).setOption("Features").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Features"));
				Menu subMenu = client.createMenuEntry(-1).setOption("Teleports").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Teleports")).createSubMenu();
				subMenu.createMenuEntry(-1).setOption("Home").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Home"));
				subMenu.createMenuEntry(-1).setOption("Black chinchompas").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Black chinchompas"));
				subMenu.createMenuEntry(-1).setOption("Yanille").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Yanille"));
				client.createMenuEntry(-1).setOption("Wear").setTarget("Max Cape").setItemId(ItemID.MAX_CAPE).onClick(me -> System.out.println("clicked " + "Wear"));
				break;
			}
		}
		if (recordMenuEntries)
		{
			recordMenuEntries = false;
			if (client.isMenuOpen()) {
				System.out.println("do not do this with the menu open.");
				return;
			}
			System.out.println("printing menu:");

			for (MenuEntry menuEntry : client.getMenuEntries())
			{
//				System.out.println(
//					"\"" + menuEntry.getOption() + "\", \"" + menuEntry.getTarget() + "\", "
//					+ menuEntry.getType() + ", "
//					+ menuEntry.getIdentifier() + ", "
//					+ menuEntry.isDeprioritized() + ", "
//				);
				System.out.println("new TestMenuEntry().setOption(\"" + menuEntry.getOption() + "\").setTarget(\"" + menuEntry.getTarget() + "\").setIdentifier(" + menuEntry.getIdentifier() + ").setType(" + menuEntry.getType() + ").setParam0(" + menuEntry.getParam0() + ").setParam1(" + menuEntry.getParam1() + ").setForceLeftClick(" + menuEntry.isForceLeftClick() + ").setDeprioritized(" + menuEntry.isDeprioritized() + ").setItemOp(" + menuEntry.getItemOp() + ").setItemId(" + menuEntry.getItemId() + ").setWidget(" + menuEntry.getWidget() + ").setActor(" + menuEntry.getActor() + ")");
				if (menuEntry.getNpc() != null) {
					String[] actions = menuEntry.getNpc().getTransformedComposition().getActions();
					System.out.println("actions is " + Arrays.asList(actions).stream().map(s -> "\"" + s + "\"").collect(Collectors.toList()));
				}
			}
		}

//		for (int i = 0; i < Math.min(100, client.getLocalPlayer().getModel().getFaceColors1().length); i++)
//		for (int i = 0; i < client.getLocalPlayer().getModel().getFaceColors1().length; i++)
//		{
//			client.getLocalPlayer().getModel().getFaceColors1()[i] = 0;
//		}
//		if (demoanim != -1) {
//			client.getLocalPlayer().setAnimation(demoanim);
//			client.getLocalPlayer().setAnimationFrame(0);
//		}
		if (demogfx != -1) {
			client.getLocalPlayer().setGraphic(demogfx);
			client.getLocalPlayer().setSpotAnimFrame(0);
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked)
	{
//		if (menuOptionClicked.getMenuOption().equals("Use") && menuOptionClicked.getId() == 563) {
//			if (demoanim != -1) {
//				demoanim--;
//				for (Constants.ActorAnimation value : values())
//				{
//					value.setAnimation(client.getLocalPlayer(), demoanim);
//				}
//				System.out.println("demo anim " + demoanim);
//			}
//			if (demogfx != -1) {
//				demogfx--;
//				System.out.println("demo gfx " + demogfx);
//			}
//		} else if (menuOptionClicked.getMenuOption().equals("Use") && menuOptionClicked.getId() == 995){
//			if (demoanim != -1) {
//				demoanim++;
//				for (Constants.ActorAnimation value : values())
//				{
//					value.setAnimation(client.getLocalPlayer(), demoanim);
//				}
//				System.out.println("demo anim " + demoanim);
//			}
//			if (demogfx != -1) {
//				demogfx++;
//				System.out.println("demo gfx " + demogfx);
//			}
//		}
//		System.out.println(menuOptionClicked.getMenuOption() + " " + Text.removeTags(menuOptionClicked.getMenuTarget()));
	}

}
