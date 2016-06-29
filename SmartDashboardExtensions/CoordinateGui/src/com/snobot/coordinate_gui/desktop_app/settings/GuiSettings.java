package com.snobot.coordinate_gui.desktop_app.settings;

import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;

public class GuiSettings
{
    public static class FrameSettings
    {
        private int frameX;
        private int frameY;
        private int frameWidth;
        private int frameHeight;

        public int getFrameX()
        {
            return frameX;
        }

        public void setFrameX(int frameX)
        {
            this.frameX = frameX;
        }

        public int getFrameY()
        {
            return frameY;
        }

        public void setFrameY(int frameY)
        {
            this.frameY = frameY;
        }

        public int getFrameWidth()
        {
            return frameWidth;
        }

        public void setFrameWidth(int frameWidth)
        {
            this.frameWidth = frameWidth;
        }

        public int getFrameHeight()
        {
            return frameHeight;
        }

        public void setFrameHeight(int frameHeight)
        {
            this.frameHeight = frameHeight;
        }

    }

    public static class CreatePointsLayerSettings
    {
        private String mDefaultProfileDirectory = ".";
        private String mDefaultConfigDirectory = ".";

        public String getDefaultProfileDirectory()
        {
            return mDefaultProfileDirectory;
        }

        public void setDefaultProfileDirectory(String mDefaultProfileDirectory)
        {
            this.mDefaultProfileDirectory = mDefaultProfileDirectory;
        }

        public String getDefaultConfigDirectory()
        {
            return mDefaultConfigDirectory;
        }

        public void setDefaultConfigDirectory(String mDefaultConfigDirectory)
        {
            this.mDefaultConfigDirectory = mDefaultConfigDirectory;
        }

    }

    private FrameSettings frameSettings;
    private CoordinateLayerRenderProps coordinateLayerRenderProperties;
    private CreatePointsLayerSettings createPointsLayerSettings;

    public GuiSettings()
    {
        frameSettings = new FrameSettings();
        coordinateLayerRenderProperties = new CoordinateLayerRenderProps();
        createPointsLayerSettings = new CreatePointsLayerSettings();
    }

    public FrameSettings getFrameSettings()
    {
        return frameSettings;
    }

    public void setFrameSettings(FrameSettings frameSettings)
    {
        this.frameSettings = frameSettings;
    }

    public CoordinateLayerRenderProps getCoordinateLayerRenderProperties()
    {
        return coordinateLayerRenderProperties;
    }

    public void setCoordinateLayerRenderProperties(CoordinateLayerRenderProps mCoordinateLayerRenderProperties)
    {
        this.coordinateLayerRenderProperties = mCoordinateLayerRenderProperties;
    }

    public void setCreatePointsLayerSettings(CreatePointsLayerSettings mCreatePointsLayerSettings)
    {
        this.createPointsLayerSettings = mCreatePointsLayerSettings;
    }

    public CreatePointsLayerSettings getCreatePointsLayerSettings()
    {
        return createPointsLayerSettings;
    }

}
