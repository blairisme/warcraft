/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Instances of this class build {@link Animation Animations}. Animations are
 * built using a given {@link Texture} and a {@link AnimationLayout} which
 * describes the layout of frames with the texture.
 *
 * @author Blair Butterworth
 */
public class AnimationBuilder
{
    private Texture texture;
    private AnimationLayout layout;

    @Inject
    public AnimationBuilder() {
        texture = null;
        layout = null;
    }

    public void setTexture(Texture texture) {
        Validate.notNull(texture);
        this.texture = texture;
    }

    public void setLayout(AnimationLayout layout) {
        Validate.notNull(layout);
        this.layout = layout;
    }

    public BasicAnimation build() {
        Validate.validState(layout != null, "Layout required");
        Validate.validState(texture != null, "Texture required");

        Map<Range<Float>, List<Rectangle>> regions = layout.getFrameRegions();
        Map<Range<Float>, Array<AnimationFrame>> frames = getFrames(regions);

        return new BasicAnimation(frames, layout.getInterval(), layout.getLoop());
    }

    private Map<Range<Float>, Array<AnimationFrame>> getFrames(Map<Range<Float>, List<Rectangle>> regions) {
        Map<Range<Float>, Array<AnimationFrame>> result = new HashMap<>(regions.size());
        for (Entry<Range<Float>, List<Rectangle>> region : regions.entrySet()) {
            result.put(region.getKey(), getFrames(region.getValue()));
        }
        return result;
    }

    private Array<AnimationFrame> getFrames(List<Rectangle> regions) {
        Array<AnimationFrame> result = new Array<>(regions.size());
        for (Rectangle region : regions) {
            result.add(getFrame(region));
        }
        return result;
    }

    private AnimationFrame getFrame(Rectangle bounds) {
        TextureRegion region = new TextureRegion(
            texture,
            (int) bounds.getX(),
            (int) bounds.getY(),
            (int) bounds.getWidth(),
            (int) bounds.getHeight());
        return new AnimationFrame(region);
    }
}
