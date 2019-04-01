package com.spring.chanba.dialog.basedialog;

import com.spring.chanba.dialog.basedialog.effects.BaseEffects;
import com.spring.chanba.dialog.basedialog.effects.FadeIn;
import com.spring.chanba.dialog.basedialog.effects.SlideTop;

public enum Effectstype {
    Fadein(FadeIn.class),
    Slidetop(SlideTop.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
