package com.evilbird.warcraft.common;

import com.badlogic.gdx.math.Rectangle;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationLayouts
{
    public static AnimationLayout effectLayout()
    {
        List<Rectangle> region = new ArrayList<Rectangle>();
        region.add(new Rectangle(0, 96, 32, 32));
        region.add(new Rectangle(0, 64, 32, 32));
        region.add(new Rectangle(0, 32, 32, 32));
        region.add(new Rectangle(0, 0, 32, 32));

        Map<Range<Float>, List<Rectangle>> regions = new HashMap<Range<Float>, List<Rectangle>>();
        regions.put(Range.between(0.0f, 360.0f), region);

        return new AnimationLayout(regions, 0.15f);
    }
}
