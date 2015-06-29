package com.hyperfresh.mchyperchat;

import java.util.HashMap;
import java.util.Map;

public class ThemeManager
{
	private final Map<String, Theme> themes = new HashMap<>();

	private final Map<Class<? extends Theme>, Theme> themesByClass = new HashMap<>();

	/**
	 * The theme to use as the default.
	 */
	private final Theme defaultTheme = new VanillaTheme();

	public ThemeManager()
	{
		add("vanilla", defaultTheme);
	}

	public void add(String ID, Theme theme)
	{
		themes.put(ID, theme);
		themesByClass.put(theme.getClass(), theme);
	}

	public void addAll(Map<String, Theme> themes)
	{
		this.themes.putAll(themes);
		themes.values().stream().forEach(theme -> this.themesByClass.put(theme.getClass(), theme));
	}

	public Theme get(String id)
	{
		return themes.get(id);
	}

	public Map<String, Theme> getThemes()
	{
		return new HashMap<>(themes);
	}

	public Theme getDefaultTheme()
	{
		return defaultTheme;
	}

	public <E extends Theme> E get(Class<E> themeClass)
	{
		return (E)themesByClass.get(themeClass);
	}

	public static class VanillaTheme implements Theme
	{
		private Map<ThemeElement, String> elements = new HashMap<>();

		protected VanillaTheme()
		{
			elements.put(ThemeElement.CHAT_MESSAGE_FORMAT, "<${name}> ${message}");
		}

		@Override
		public String getID()
		{
			return "vanilla";
		}

		@Override
		public String getName()
		{
			return "Vanilla Classic";
		}

		@Override
		public String getAuthor()
		{
			return "Mojang";
		}

		@Override
		public String getElement(ThemeElement element)
		{
			return elements.get(element);
		}
	}
}
