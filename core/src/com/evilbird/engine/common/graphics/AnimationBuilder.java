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
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
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
        Map<Range<Float>, Array<TextureRegion>> frames = getFrames(regions);
        PlayMode mode = layout.getLoop() ? PlayMode.LOOP : PlayMode.NORMAL;

        return new BasicAnimation(0f, layout.getInterval(), frames, mode);
    }

    private Map<Range<Float>, Array<TextureRegion>> getFrames(Map<Range<Float>, List<Rectangle>> regions) {
        Map<Range<Float>, Array<TextureRegion>> result = new HashMap<>(regions.size());
        for (Entry<Range<Float>, List<Rectangle>> region : regions.entrySet()) {
            result.put(region.getKey(), getFrames(region.getValue()));
        }
        return result;
    }

    private Array<TextureRegion> getFrames(List<Rectangle> regions) {
        Array<TextureRegion> result = new Array<>(regions.size());
        for (Rectangle region : regions) {
            result.add(getFrame(region));
        }
        return result;
    }

    private TextureRegion getFrame(Rectangle region) {
        return new TextureRegion(
            texture,
            (int) region.getX(),
            (int) region.getY(),
            (int) region.getWidth(),
            (int) region.getHeight());
    }
}
