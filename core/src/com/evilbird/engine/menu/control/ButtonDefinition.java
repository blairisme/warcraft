package com.evilbird.engine.menu.control;

public class ButtonDefinition
{
    private String text;
    private String disabledTexture;
    private String enabledTexture;
    private String hoverTexture;
    private String activatedTexture;
    private String selectedTexture;

    public String getActivatedTexture()
    {
        return activatedTexture;
    }

    public String getDisabledTexture()
    {
        return disabledTexture;
    }

    public String getEnabledTexture()
    {
        return enabledTexture;
    }

    public String getHoverTexture()
    {
        return hoverTexture;
    }

    public String getSelectedTexture()
    {
        return selectedTexture;
    }

    public String getText()
    {
        return text;
    }
}
